<template>
  <!-- 添加和修改视频表单 -->
  <el-dialog
    :close-on-press-escape="closeOnPressEscape"
    :visible="dialogVisible"
    :show-close="showClose"
    :close-on-click-modal="closeOnClickModal"
    :title="video.id ? '更新集':'添加集'"
    @close="close()">
    <el-form :model="video" label-width="120px">
      <el-form-item label="标题">
        <el-input v-model="video.title"/>
      </el-form-item>
      <el-form-item label="是否免费观看">
        <el-radio-group v-model="video.free">
          <el-radio :label="true">是</el-radio>
          <el-radio :label="false">否</el-radio>
        </el-radio-group>
      </el-form-item>
      <!-- 上传视频 -->
      <el-form-item label="上传视频">
        <el-upload
          ref="upload"
          :auto-upload="false"
          :on-success="handleUploadSuccess"
          :on-error="handleUploadError"
          :on-exceed="handleUploadExceed"
          :before-remove="handleBeforeRemove"
          :on-change="handleUploadChange"
          :on-remove="handleOnRemove"
          :file-list="fileList"
          :limit="1"
          action="http://127.0.0.1:8170/admin/vod/file/upload"
        >
          <el-button slot="trigger" size="small" type="primary">选择视频</el-button>
          <el-button
            :disabled="uploadBtnDisabled"
            style="margin-left: 10px;"
            size="small"
            type="success"
            @click="submitUpload()">上传</el-button>
        </el-upload>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button :disabled="cancelBtnDisabled" @click="close()">取 消</el-button>
      <el-button type="primary" @click="saveOrUpdate()">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import videoApi from '@/api/video'
import vodApi from '@/api/vod'

export default {
  data() {
    return {
      dialogVisible: false, // 是否显示对话框
      video: {
        sort: 0,
        free: false
      },
      fileList: [],
      uploadBtnDisabled: true,
      versionUpdate: false,
      file: {},
      cancelBtnDisabled: false,
      closeOnPressEscape: true,
      closeOnClickModal: true,
      showClose: true
    }
  },

  methods: {
    handleUploadChange(file) {
      if (this.file !== null) {
        this.uploadBtnDisabled = false
        this.file = file
      }
    },

    // 上传多于一个视频
    handleUploadExceed(files, fileList) {
      this.$message.warning('想要重新上传视频，请先删除已上传的视频')
    },

    // 上传
    submitUpload() {
      this.uploadBtnDisabled = true// 禁用按钮
      this.$refs.upload.submit() // 提交上传请求
    },

    // 视频上传成功的回调
    handleUploadSuccess(response, file, fileList) {
      // 视频上传成功后必须进行保存。避免 vod 中的脏数据
      this.uploadBtnDisabled = false
      this.cancelBtnDisabled = true
      this.closeOnPressEscape = false
      this.closeOnClickModal = false
      this.showClose = false
      // 为 video 对象属性进行赋值，指定文件名和视频源 id
      if (response.success) {
        this.video.videoSourceId = response.data.videoId
        this.video.videoOriginalName = file.name
      } else {
        this.$message.error('上传失败，后端错误')
      }
    },

    // 失败回调
    handleUploadError() {
      this.uploadBtnDisabled = false
      this.$message.error('上传失败，http 错误')
    },

    // 删除视频文件确认
    handleBeforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name} 吗?`)
    },

    // 删除视频
    handleOnRemove(file, fileList) {
      if (!this.video.videoSourceId) {
        return
      }

      vodApi.deleteById(this.video.videoSourceId).then(response => {
        this.video.videoSourceId = ''
        this.video.videoOriginalName = ''
        this.$message.success(response.message)
      })
    },

    // 显示对话框
    open(chapterId, videoId) {
      this.dialogVisible = true
      this.video.chapterId = chapterId
      if (videoId) {
        videoApi.getById(videoId).then(response => {
          this.video = response.data.item
          // 回显
          if (this.video.videoSourceId) {
            this.fileList = [{ 'name': this.video.videoOriginalName }]
          }
        })
      }
    },

    // 非保存按钮关闭表单页面时，删除vod文件
    close() {
      this.dialogVisible = false
      // 重置表单
      this.resetForm()
    },

    closeBySave() {
      this.dialogVisible = false
      this.cancelBtnDisabled = false
      this.closeOnPressEscape = true
      this.closeOnClickModal = true
      this.showClose = true
      // 重置表单
      this.resetForm()
    },

    resetForm() {
      this.video = {
        sort: 0,
        free: false
      }
      this.fileList = []
      this.file = {}
    },

    // 保存和更新
    saveOrUpdate() {
      if (!this.video.id) {
        this.save()
      } else {
        this.update()
      }
    },

    save() {
      this.video.animeId = this.$parent.$parent.animeId
      videoApi.addVideo(this.video).then(response => {
        this.$message.success(response.message)
        // 关闭组件
        this.closeBySave()
        this.file = {}
        // 刷新列表
        this.$parent.fetchTreeList()
      })
    },

    update() {
      videoApi.updateById(this.video).then(response => {
        this.$message.success(response.message)
        // 关闭组件
        this.closeBySave()
        this.file = {}
        // 刷新列表
        this.$parent.fetchTreeList()
      })
    }
  }
}
</script>


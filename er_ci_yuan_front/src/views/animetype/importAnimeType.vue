<template>
  <div class="app-container">
    <el-form label-width="120px">

      <el-form-item label="信息描述">
        <el-button round type="info" @click="downloadTemplatInfo">模版说明</el-button>
        <el-button type="success" style="margin-left: 10px;">
          <i class="el-icon-download"/>
          <a :href="defaultExcelTemplate">下载模版</a>
        </el-button>
      </el-form-item>
      <!-- accept 指定 MIME 类型，这样在选取时只能看见指定类型的文件-->
      <el-form-item label="选择Excel">
        <el-upload
          ref="upload"
          :auto-upload="false"
          :on-exceed="fileUploadExceed"
          :on-success="fileUploadSuccess"
          :on-error="fileUploadError"
          :limit="1"
          :action="BASE_API+'/admin/manager/subject/import'"
          name="file"
          accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
          <el-button
            slot="trigger"
            round
            type="primary">选取文件</el-button>
          <el-button
            :disabled="importBtnDisabled"
            style="margin-left: 10px;"
            type="success"
            @click="submitUpload">导入数据库</el-button>
        </el-upload>

      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      defaultExcelTemplate: process.env.OSS_PATH + '/excel/动漫分类列表模板.xlsx',
      importBtnDisabled: false,
      BASE_API: process.env.BASE_API
    }
  },

  methods: {

    // 执行上传
    submitUpload() {
      this.importBtnDisabled = true // 禁用按钮
      this.$refs.upload.submit() // 手动表单提交
    },

    // 当选择文件超出约定数量时触发
    fileUploadExceed() {
      this.$message.warning('只能选取一个文件')
    },

    // 上传成功的回调
    fileUploadSuccess(response) {
      if (response.success) {
        this.importBtnDisabled = false // 启用按钮
        this.$message.success(response.message)
        this.$refs.upload.clearFiles() // 清空文件列表
      } else {
        this.$message.error('上传失败! （非20000）')
      }
    },

    // 上传失败的回调
    fileUploadError() {
      this.importBtnDisabled = false // 启用按钮
      this.$message.error('上传失败! （http失败）')
      this.$refs.upload.clearFiles() // 清空文件列表
    },

    // 信息提示
    downloadTemplatInfo() {
      this.$alert('后面的模板文件规定了你要上传的格式喔亲，请先下载下来看看', '模板文件说明', {
        confirmButtonText: '确定',
        callback: action => {
          this.$message({
            type: 'success',
            message: `去下载吧！`
          })
        }
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">

</style>

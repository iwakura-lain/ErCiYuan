<template>
  <div class="app-container">

    <!-- 动漫信息表单 -->
    <el-form ref="animeInfo" :model="animeInfo" :rules="rules" status-icon label-width="120px" class="demo-ruleForm">

      <el-form-item label="动漫名称" prop="title">
        <el-input v-model="animeInfo.title" style="width: 70%" placeholder=" 示例：《总之就是非常 gay》"/>
      </el-form-item>

      <!-- 动漫制作方 -->
      <el-form-item label="制作方" prop="adminId">
        <el-select v-model="animeInfo.adminId" placeholder="请选择">
          <el-option
            v-for="admin in adminList"
            :key="admin.id"
            :value="admin.id"
            :label="admin.name" />
        </el-select>
      </el-form-item>

      <!-- 所属分类 -->
      <el-form-item label="动漫分类" prop="subjectParentId">
        <!-- 一级分类 -->
        <el-select
          v-model="animeInfo.subjectParentId"
          placeholder="请选择"
          @change="parentSubjectChanged">
          <el-option
            v-for="subject in subjectList"
            :key="subject.id"
            :value="subject.id"
            :label="subject.title" />
        </el-select>

        <!-- 二级分类 -->
        <el-select
          v-model="animeInfo.subjectId"
          placeholder="请选择">
          <el-option
            v-for="subject in subjectLevelTwoList"
            :key="subject.id"
            :value="subject.id"
            :label="subject.title" />
        </el-select>
      </el-form-item>

      <el-form-item label="总集数">
        <el-input-number :min="0" v-model="animeInfo.animeNum" controls-position="right" placeholder="请填写动漫的总集数"/>
      </el-form-item>

      <!-- 动漫简介-->
      <el-form-item label="动漫简介" prop="description">
        <tinymce :height="300" v-model="animeInfo.description"/>
      </el-form-item>

      <!--动漫封面 -->
      <el-form-item label="动漫封面">
        <el-upload
          :show-file-list="false"
          :on-success="handleCoverSuccess"
          :before-upload="beforeCoverUpload"
          :on-error="handleCoverError"
          :action="BASE_API+'/admin/oss/file/upload?moduleName=cover'"
          class="cover-uploader">
          <img v-if="animeInfo.cover" :src="animeInfo.cover">
          <i v-else class="el-icon-plus avatar-uploader-icon"/>
        </el-upload>
      </el-form-item>

      <el-form-item label="动漫价格">
        <el-input-number :min="0" v-model="animeInfo.price" controls-position="right" placeholder="免费动漫请设置为0元"/> 元
      </el-form-item>
    </el-form>

    <div style="text-align:center">
      <el-button :disabled="saveBtnDisabled" type="primary" @click="saveAndNext('animeInfo')">保存并下一步</el-button>
    </div>
  </div>
</template>

<script>
import animeApi from '@/api/anime'
import adminApi from '@/api/admin'
import subjectApi from '@/api/subject'
// 富文本编辑器
import Tinymce from '@/components/Tinymce'

export default {

  components: { Tinymce },

  data() {
    return {
      saveBtnDisabled: false, // 按钮是否禁用
      animeInfo: {// 表单数据
        price: 0,
        lessonNum: 0,
        // 以下解决表单数据不全时数据库字段要求非空而导致的报错
        subjectId: '',
        adminId: '',
        subjectParentId: '',
        cover: '',
        description: ''
      },
      adminList: [], // 制作人(雾)列表
      subjectList: [], // 一级分类
      subjectLevelTwoList: [], // 二级分类
      rules: {
        title: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '请输入简介', trigger: 'blur' }
        ],
        adminId: [
          { required: true, message: '请选择制作方', trigger: 'change' }
        ],
        subjectParentId: [
          { required: true, message: '请选择一级类别和二级类别', trigger: 'change' }
        ]
      },
      BASE_API: process.env.BASE_API
    }
  },

  created() {
    if (this.$parent.animeId) { // 回显
      this.fetchAnimeInfoById(this.$parent.animeId)
    } else if (this.$route.params.id) { // 只渲染一级类别
      this.fetchAnimeInfoById(this.$route.params.id)
    } else {
      this.initSubjectList()
    }
    this.initadminList()
  },

  methods: {

    // 根据id获取anime基本信息
    fetchAnimeInfoById(id) {
      animeApi.getAnimeInfoById(id).then(response => {
        this.animeInfo = response.data.item

        // 动漫类别渲染
        subjectApi.getTreeList().then(response => {
          this.subjectList = response.data.items
          // 在回显时，根据回显的一级类目信息来获得对应的子类目列表
          // 不然的话二级类目在回显时会因为没有列表数据
          // 而无法直接渲染获得对应的值，只会是后端返回的一串 id
          // 判断 this.subjectList下哪个一级类别是当前绑定的一级类别
          this.subjectList.forEach(subject => {
            if (subject.id === this.animeInfo.subjectParentId) {
              // 找到对应一级类别的二级类别列表
              this.subjectLevelTwoList = subject.childrenSubject
            }
          })
        })
      })
    },

    // 获取制作方列表
    initadminList() {
      adminApi.list().then(response => {
        this.adminList = response.data.items
      })
    },

    // 获取动漫一级分类列表，其中含有其子列表
    initSubjectList() {
      subjectApi.getTreeList().then(response => {
        this.subjectList = response.data.items
      })
    },

    // 切换一级类别的下拉列表时调用该函数，
    // value 是回调参数，是 el-options 里面的 :value 绑定的值
    parentSubjectChanged(value) {
      // 遍历一级类别列表
      this.subjectList.forEach(subject => {
        // 找到当前选中的值对应的一级类别，并取出其二级类别进行渲染
        if (subject.id === value) {
          // 清空当前值
          this.animeInfo.subjectId = ''
          // 则将当前的一级类别的子类别绑定在页面的二级类别列表中
          this.subjectLevelTwoList = subject.childrenSubject
        }
      })
    },

    //   保存并下一步
    saveAndNext(animeInfo) {
      this.saveBtnDisabled = true
      this.$refs[animeInfo].validate((valid) => {
        if (valid) {
          if (!this.$parent.animeId && !this.$route.params.id) {
            this.saveData()
          } else {
            this.updateData()
          }
        } else {
          this.$message({
            message: '请补全信息',
            type: 'error'
          })
          this.saveBtnDisabled = false
          return false
        }
      })
    },

    // 保存动漫信息
    saveData() {
      animeApi.addAnimeInfo(this.animeInfo).then(response => {
        this.$message.success(response.message)
        this.$parent.animeId = response.data.animeId // 获取animeId
        this.$parent.active = 1 // 下一步 访问父组件的成员 $parent
      })
    },

    // 更新动漫信息
    updateData() {
      animeApi.updateAnimeInfoById(this.animeInfo).then(response => {
        this.$message.success(response.message)
        this.$parent.animeId = response.data.animeId
        this.$parent.active = 1
      })
    },

    // 上传成功回调
    handleCoverSuccess(res, file) {
      if (res.success) {
        // console.log(res)
        this.animeInfo.cover = res.data.url
        // 强制重新渲染
        this.$forceUpdate()
      } else {
        this.$message.error('上传失败1（非20000）')
      }
    },

    // 上传校验
    beforeCoverUpload(file) {
      const isJPG = file.type === 'image/jpeg' || file.type === 'image/png'
      const isLt6M = file.size / 1024 / 1024 < 6

      if (!isJPG) {
        this.$message.error('上传封面只能是 JPG 格式!')
      }
      if (!isLt6M) {
        this.$message.error('上传封面大小不能超过 6MB!')
      }
      return isJPG && isLt6M
    },

    // 错误处理
    handleCoverError() {
      console.log('error')
      this.$message.error('上传失败（http错误）')
    }
  }
}
</script>

<style>
.tinymce-container {
  position: relative;
  line-height: normal;
}

.cover-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.cover-uploader .el-upload:hover {
  border-color: #409EFF;
}
.cover-uploader .avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 640px;
  height: 357px;
  line-height: 357px;
  text-align: center;
}
.cover-uploader img {
  width: 640px;
  height: 357px;
  display: block;
}
</style>

<template>
  <div class="app-container">
    <!-- 输入表单 -->
    <el-form label-width="120px">
      <el-form-item label="魔女名讳">
        <el-input v-model="admin.name" />
      </el-form-item>
      <el-form-item label="注册时间">
        <el-date-picker v-model="admin.joinDate" value-format="yyyy-MM-dd" />
      </el-form-item>
      <el-form-item label="rating">
        <el-input-number v-model="admin.sort" :min="0"/>
      </el-form-item>
      <el-form-item label="头衔">
        <el-select v-model="admin.level">
          <!--
            数据类型一定要和取出的json中的一致，否则没法回填
            这里value使用v-bind的值，保证其数据类型是number
            -->
          <el-option :value="1" label="见习魔女"/>
          <el-option :value="2" label="灰之魔女"/>
        </el-select>
      </el-form-item>
      <el-form-item label="战绩">
        <el-input v-model="admin.career"/>
      </el-form-item>
      <el-form-item label="人生格言">
        <el-input v-model="admin.intro" :rows="10" type="textarea"/>
      </el-form-item>

      <el-form-item label="头像上传">
        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :on-error="handleAvatarError"
          :before-upload="beforeAvatarUpload"
          class="avatar-uploader"
          action="http://127.0.0.1:8180/admin/oss/file/upload?moduleName=avatar">
          <img v-if="admin.avatar" :src="admin.avatar" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"/>
        </el-upload>
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate()">保存</el-button>
        <el-button type="warning" @click="backListPage">取消</el-button>
      </el-form-item>

    </el-form>

  </div>
</template>

<script>
import adminApi from '@/api/admin'

export default {

  data() {
    return {
      // admin 对象
      admin: {
        // 默认
        sort: 0,
        level: 1
      },
      // 默认按钮可用
      saveBtnDisabled: false
    }
  },

  created() {
    // 创建页面时，根据 url 是否有 id 路由过来来进行数据回显调用
    if (this.$route.params.id) {
      this.fetchDataById(this.$route.params.id)
    }
  },

  methods: {
    saveOrUpdate() {
      this.saveBtnDisabled = true
      if (!this.admin.id) {
        this.saveData()
      } else {
        this.updateData()
      }
    },

    // 数据保存
    saveData() {
      adminApi.save(this.admin).then(response => {
        // 弹出成功提示
        this.$message({
          message: '注册成功',
          type: 'success'
        })
        this.$router.push({ path: '/admin/list' })
      })
      this.saveBtnDisabled = false
    },

    // 数据更新
    updateData() {
      adminApi.updateById(this.admin).then(response => {
        // 弹出成功提示
        this.$message({
          message: '更新成功',
          type: 'success'
        })
      })
      this.$router.push({ path: '/admin/list' })
    },

    // 根据id查询数据，用于跳转后回显数据
    fetchDataById(id) {
      adminApi.getById(id).then(response => {
        this.admin = response.data.item
      })
    },

    // 返回
    backListPage() {
      this.$router.push({ path: '/admin/list' })
      this.$message({
        message: '已取消',
        type: 'info'
      })
    },

    // 文件上传成功的钩子函数
    handleAvatarSuccess(res) {
      if (res.success) {
        this.admin.avatar = res.data.url
        // 强制重新渲染
        this.$forceUpdate()
      } else {
        this.$message({
          message: '上传失败',
          type: 'error'
        })
      }
    },
    // 文件上传失败， http 响应码不为 200 时的处理
    handleAvatarError() {
      this.$message({
        message: '上传失败( http 请求失败)',
        type: 'error'
      })
    },
    // 上传前校验
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg' || file.type === 'image/png'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 或 PNG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    }
  }
}
</script>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>

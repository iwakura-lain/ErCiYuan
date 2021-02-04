<template>
  <article class="col-7 fl">
    <div class="u-r-cont">
      <section>
        <div class="sign-up-container">
          <el-form ref="member" :model="member">
            <el-form-item>
              <el-upload
                :show-file-list="false"
                :on-success="handleCoverSuccess"
                :before-upload="beforeCoverUpload"
                :on-error="handleCoverError"
                class="cover-uploader"
                style="margin-left: 24%;"
                action="http://localhost:8180/admin/oss/file/upload?moduleName=ucenter">
                <img v-if="member.avatar" :src="member.avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"/>
              </el-upload>
            </el-form-item>
            <el-form-item >
              <el-input
                v-model="member.nickname"
                type="text"
                class="u-input"
                placeholder="你的昵称"/>
              <i class="iconfont icon-user"/>
            </el-form-item>
            <el-form-item>
              <el-input
                v-model="member.mobile"
                type="text"
                class="u-input"
                placeholder="手机号"/>
              <i class="iconfont icon-phone"/>
            </el-form-item>
            <el-form-item >
              <el-input
                v-model="member.code"
                type="text"
                class="u-input"
                placeholder="验证码"/>
              <i class="iconfont icon-yanzhengma"/>
              <el-button
                :disabled="disable"
                size="small"
                type="primary"
                @click="getCodeFun()">
                {{ codeText }}
              </el-button>
            </el-form-item>
            <el-form-item>
              <el-input
                v-model="member.oldPassword"
                type="password"
                class="u-input"
                placeholder="older 密码"/>
              <i class="iconfont icon-password"/>
            </el-form-item>
            <el-form-item>
              <el-input
                v-model="member.newPassword"
                type="password"
                class="u-input"
                placeholder="younger 密码"/>
              <i class="iconfont icon-password"/>
            </el-form-item>
          </el-form>
          <div class="btn">
            <el-button
              type="primary"
              style="margin-left: 32%; "
              value="保存更改"
              @click="updateMember()">
              保存更改
            </el-button>
          </div>
        </div>
      </section>
    </div>
  </article>
</template>

<script>
import '~/assets/css/sign.css'
import '~/assets/css/iconfont.css'
import registerApi from '~/api/register'
import loginApi from '~/api/login'
import ucenterApi from '~/api/ucenter'
import cookie from 'js-cookie'

export default {
  layout: 'sign',
  data() {
    return {
      member: {},
      sending: false, // 是否发送验证码
      second: 60, // 倒计时间
      codeText: '获取验证码',
      disable: false
    }
  },

  created() {
    this.getUserInfo()
  },

  methods: {
    // 获取验证码
    getCodeFun() {
      // 防止多次点击
      if (this.disable) return
      this.disable = true
      registerApi.sendSms(this.member.mobile).then(response => {
        // 倒计时
        this.timeDown()
        this.$message.success(response.message)
      }).catch(() => {
        this.disable = false
      })
    },

    // 倒计时
    timeDown() {
      this.codeText = this.second
      const timer = setInterval(() => {
        this.codeText--
        if (this.codeText < 1) {
          clearInterval(timer)
          this.codeText = '获取验证码'
          this.second = 60
          this.sending = false
          this.disable = false
        }
      }, 1000)
    },

    updateMember() {
      ucenterApi.updateMember(this.member).then(response => {
        cookie.set('jwt_token', response.data.token, { domain: 'localhost' })
        // 跳转到首页
        window.location.href = '/'
      })
    },

    getUserInfo() {
      if (!cookie.get('jwt_token')) {
        return
      }
      loginApi.getLoginInfo().then(response => {
        this.member = response.data.userInfo
        this.refreshToken = response.data.token
        if (this.refreshToken) {
          cookie.set('jwt_token', this.refreshToken, { domain: 'localhost' })
        }
      })
    },

    // 上传成功回调
    handleCoverSuccess(res, file) {
      if (res.success) {
        // console.log(res)
        this.member.avatar = res.data.url
        // 强制重新渲染
        this.$forceUpdate()
      } else {
        this.$message.error('上传失败1（非20000）')
      }
    },

    // 上传校验
    beforeCoverUpload(file) {
      const isJPG = file.type === 'image/jpeg' || file.type === 'image/png'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像只能是 JPG / PNG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像大小不能超过 2MB!')
      }
      return isJPG && isLt2M
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
  .u-input{
    width:300px;
    margin-left: 20%;
  }
  .cover-uploader img{
    width: 200px;
    height: 200px;
  }
</style>

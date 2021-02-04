<template>
  <div class="main">
    <div class="title">
      <a href="/login">登录</a>
      <span>·</span>
      <a class="active" href="/register">注册</a>
    </div>

    <div class="sign-up-container">
      <form action="register">
        <div class="input-prepend restyle">
          <input
            v-model="member.nickname"
            type="text"
            placeholder="你的昵称">
          <i class="iconfont icon-user"/>
        </div>
        <div class="input-prepend restyle no-radius">
          <input
            v-model="member.mobile"
            type="text"
            placeholder="手机号">
          <i class="iconfont icon-phone"/>
        </div>
        <div class="input-prepend restyle no-radius" style="position:relative">
          <input
            v-model="member.code"
            type="text"
            placeholder="验证码">
          <i class="iconfont icon-yanzhengma"/>
          <el-button
            :disabled="disable"
            size="small"
            type="primary"
            href="javascript:"
            style="position:absolute;right: 0px;top: 15px;"
            @click="getCodeFun()">{{ codeText }}</el-button>
        </div>
        <div class="input-prepend">
          <input
            v-model="member.password"
            type="password"
            placeholder="设置密码">
          <i class="iconfont icon-password"/>
        </div>
        <div class="btn">
          <input
            type="button"
            class="sign-up-button"
            value="注册"
            @click="submitRegister()">
        </div>
        <p class="sign-up-msg">
          点击 “注册” 即表示您同意并愿意遵守
          <br>
          <a target="_blank" href="http://www.jianshu.com/p/c44d171298ce">契约</a>
          和
          <a target="_blank" href="http://www.jianshu.com/p/2ov8x3">保密条例</a> 。
        </p>
      </form>
      <!-- 更多注册方式 -->
      <div class="more-sign">
        <h6>社交帐号登录</h6>
        Tip: GitHub 可能会很慢，不建议使用
        <br>
        <br>
        <ul>
          <li>
            <a
              id="weixin"
              class="weixin"
              href="http://localhost:8140/api/vx/login/authorize">
              <img width="35" height="35" src="~/assets/img/gitee.png">
            </a>
          </li>
          <li>
            <a
              id="github"
              class="github"
              href="http://localhost:8140/api/github/login/authorize">
              <img width="30" height="30" src="~/assets/img/github.png">
            </a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import '~/assets/css/sign.css'
import '~/assets/css/iconfont.css'
import registerApi from '~/api/register'

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

    // 注册
    submitRegister() {
      registerApi.register(this.member).then((response) => {
        this.$message.success(response.message)
        this.$router.push({ path: 'login' })
      }).catch(error => {
        this.$message.error(error.message)
      })
    }
  }
}
</script>
<style scoped>

</style>

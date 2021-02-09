<template>
  <div class="main">
    <div class="title">
      <nuxt-link :to="'/login'" class="active">登录</nuxt-link>
      <span>·</span>
      <nuxt-link :to="'/register'">注册</nuxt-link>
    </div>

    <div class="sign-up-container">
      <form action="register">
        <div class="input-prepend restyle">
          <input
            v-model="user.mobile"
            type="text"
            placeholder="手机号">
          <i class="iconfont icon-phone"/>
        </div>
        <div class="input-prepend">
          <input
            v-model="user.password"
            type="password"
            placeholder="密码">
          <i class="iconfont icon-password"/>
        </div>
        <div class="btn">
          <input
            type="button"
            class="sign-in-button"
            value="登录"
            @click="submitLogin()">
        </div>
      </form>
      <!-- 更多登录方式 -->

      <div class="more-sign">
        <h6>社交帐号登录</h6>
        Tip: GitHub 可能会很慢，不建议使用
        <br>
        <br>
        <ul>
          <li>
            <a
              id="weixin"
              :href="baseURL+'/api/gitee/login/authorize'"
              class="weixin">
              <img width="35" height="35" src="~/assets/img/gitee.png">
            </a>
          </li>
          <li>
            <a
              id="github"
              :href="baseURL+'/api/github/login/authorize'"
              class="github">
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
import cookie from 'js-cookie'
import loginApi from '~/api/login'

export default {
  layout: 'sign',

  data() {
    return {
      user: {
        mobile: '',
        password: ''
      },
      baseURL: process.env.baseUrl
    }
  },

  methods: {
    // 登录
    submitLogin() {
      // 调用登录接口
      loginApi.login(this.user).then(response => {
        // jwt 写入 cookie, domain 就是各个子网站之间的顶级域名
        // 这里设置的 cookie 的 path 默认为 /
        cookie.set('jwt_token', response.data.token, { domain: 'localhost' })
        // 如果用户是从注册页面过来的，则跳转到首页
        if (document.referrer.indexOf('register') !== -1) {
          window.location.href = '/'
        } else {
          history.go(-1)
        }
      })
    }
  }
}
</script>

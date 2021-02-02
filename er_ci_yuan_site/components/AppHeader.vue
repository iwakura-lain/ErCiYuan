<template>
  <!-- 公共头 -->
  <header id="header">
    <section class="container">
      <h1 id="logo">
        <a href="#" title="二刺螈">
          <img src="~/assets/img/logo4.png" width="100%" alt="二刺螈">
        </a>
      </h1>
      <div class="h-r-nsl">
        <ul class="nav">
          <div style="color:red;">
            <a :href="'/'">首页</a>
          </div>
          <nuxt-link to="/anime" tag="li" active-class="current">
            <a>动漫</a>
          </nuxt-link>
          <nuxt-link to="/maker" tag="li" active-class="current">
            <a>制作方</a>
          </nuxt-link>
          <nuxt-link to="/article" tag="li" active-class="current">
            <a>资源</a>
          </nuxt-link>
          <nuxt-link to="/question" tag="li" active-class="current">
            <a>讨论</a>
          </nuxt-link>
        </ul>
        <!-- / nav -->
        <ul class="h-r-login">
          <li v-if="!userInfo" id="no-login">
            <a href="/login" title="登录">
              <i>🧙</i>
              <span style="color: rgb(94, 206, 235)" class="vam ml5">登录</span>
            </a>
            |
            <a href="/register" title="注册">
              <i>📝</i>
              <span style="color: rgb(94, 206, 235)" class="vam ml5">注册</span>
            </a>
          </li>
          <!-- 注意undis将当前节点隐藏了 -->
          <li v-if="userInfo" id="is-login-two" class="h-r-user">
            <a href="/ucenter" title>
              <img
                :src="userInfo.avatar"
                width="100px"
                height="100px"
                class="vam picImg"
                alt
              >
              <span id="userName" class="vam disIb">{{ userInfo.nickname }}</span>
            </a>
            <a href="javascript:void(0)" title="退出" class="ml5" @click="logout()">退出</a>
          </li>
          <!-- /未登录显示第1 li；登录后显示第2，3 li -->
        </ul>
        <aside class="h-r-search">
          <form action="anime">
            <label class="h-r-s-box">
              <el-autocomplete
                v-model="title"
                :fetch-suggestions="querySearch"
                :trigger-on-focus="false"
                class="inline-input"
                placeholder="搜索动漫"
              />
              <nuxt-link
                :to="{path: '/anime', query:{title}}"
                type="submit"
                class="s-btn"
              >
                <em class="icon18">&nbsp;</em>
              </nuxt-link>
            </label>
          </form>
        </aside>
      </div>
      <aside class="mw-nav-btn">
        <div class="mw-nav-icon"/>
      </aside>
      <div class="clear"/>
    </section>
  </header>
  <!-- /公共头 -->
</template>

<script>
import animeApi from '~/api/anime'
import loginApi from '~/api/login'
import cookie from 'js-cookie'

export default {
  data() {
    return {
      userInfo: null,
      title: '',
      refreshToken: null
    }
  },

  created() {
    this.getUserInfo()
  },

  mounted() {
    // url token获取
    this.token = this.$route.query.token
    if (this.token) {
      // 将token存在cookie中
      cookie.set('jwt_token', this.token, { domain: 'localhost' })
      // 跳转页面：擦除url中的token
      // 注意：window对象在created方法中无法被访问，因此要写在mounted中
      window.location = '/'
    }
  },

  methods: {
    // 输入建议
    querySearch(queryString, callback) {
      animeApi.searchAutoComplete(queryString).then(response => {
        callback(response.data.animes)
      })
    },

    getUserInfo() {
      if (!cookie.get('jwt_token')) {
        return
      }
      loginApi.getLoginInfo().then(response => {
        this.userInfo = response.data.userInfo
        this.refreshToken = response.data.token
        if (this.refreshToken) {
          cookie.set('jwt_token', this.refreshToken, { domain: 'localhost' })
        }
      })
    },

    logout() {
      cookie.set('jwt_token', '')
      window.location.href = '/'
    }
  }
}
</script>

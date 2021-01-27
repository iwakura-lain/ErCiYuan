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
          <router-link to="/" tag="li" active-class="current" exact>
            <a>首页</a>
          </router-link>
          <router-link to="/anime" tag="li" active-class="current">
            <a>动漫</a>
          </router-link>
          <router-link to="/maker" tag="li" active-class="current">
            <a>制作方</a>
          </router-link>
          <router-link to="/article" tag="li" active-class="current">
            <a>资源</a>
          </router-link>
          <router-link to="/question" tag="li" active-class="current">
            <a>讨论</a>
          </router-link>
        </ul>
        <!-- / nav -->
        <ul class="h-r-login">
          <li id="no-login">
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
          <li id="is-login-one" class="mr10 undis">
            <a id="headerMsgCountId" href="#" title="消息">
              <em class="icon18 news-icon">&nbsp;</em>
            </a>
            <q class="red-point">&nbsp;</q>
          </li>
          <!-- 注意undis将当前节点隐藏了 -->
          <li id="is-login-two" class="h-r-user undis">
            <a href="/ucenter" title>
              <img
                src="~/assets/img/avatar-boy.gif"
                width="30"
                height="30"
                class="vam picImg"
                alt
              >
              <span id="userName" class="vam disIb">登录的用户名</span>
            </a>
            <a href="javascript:void(0)" title="退出" class="ml5">退出</a>
          </li>
          <!-- /未登录显示第1 li；登录后显示第2，3 li -->
        </ul>
        <aside class="h-r-search">
          <form action="#" method="post">
            <label class="h-r-s-box">
              <input type="text" placeholder="搜索动漫" name="queryCourse.courseName" value>
              <button type="submit" class="s-btn">
                <em class="icon18">&nbsp;</em>
              </button>
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

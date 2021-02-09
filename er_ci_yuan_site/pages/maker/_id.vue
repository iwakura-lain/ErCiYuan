<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- 制作方介绍 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">制作方</span>
        </h2>
      </header>
      <div class="t-infor-wrap">
        <!-- 制作方基本信息 开始 -->
        <section class="fl t-infor-box c-desc-content">
          <div class="mt20 ml20">
            <section class="t-infor-pic">
              <img :src="admin.avatar" :alt="admin.name">
            </section>
            <h3 class="hLh30">
              <span class="fsize24 c-333">{{ admin.name }}
              </span>
            </h3>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <section class="mt10">
              <span style="text-align: center;display:block;" class="t-tag-bg">代表作：{{ admin.career }}</span>
            </section>
            <br>
            <section class="t-infor-txt">
              <p class="mt20">{{ admin.intro }}</p>
            </section>
            <div class="clear"/>
          </div>
        </section>
        <!-- /制作方基本信息 结束 -->
        <div class="clear"/>
      </div>
      <section class="mt30">
        <div>
          <header class="comm-title all-maker-title c-anime-content">
            <h2 class="fl tac">
              <span class="c-333">制作动画</span>
            </h2>
            <section class="c-tab-title">
              <a href="javascript: void(0)">&nbsp;</a>
            </section>
          </header>
          <!-- /无数据提示 开始-->
          <section v-if="animes.length === 0" class="no-data-wrap">
            <img
              src="~/assets/img/noData.png"
              style="width:150px; height:150px; display: inline"
              class="img-responsive">
            <br>
            <span class="c-666 fsize14 ml10 vam">没有相关动漫</span>
          </section>
          <!-- /无数据提示 结束-->

          <!-- 动漫列表 开始-->
          <article v-if="animes.length !== 0" class="comm-anime-list">
            <ul class="of">
              <li v-for="(item, index) in animes" :key="index">
                <div class="cc-l-wrap">
                  <section class="anime-img">
                    <img :alt="item.title" :src="item.cover" class="img-responsive">
                    <div class="cc-mask">
                      <nuxt-link :to="'/anime/'+item.id" title="开始观看" class="comm-btn c-btn-1">开始观看</nuxt-link>
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <nuxt-link :title="item.title" :to="'/anime/'+item.id" class="anime-title fsize18 c-333">{{ item.title }}</nuxt-link>
                  </h3>
                </div>
              </li>
            </ul>
            <div class="clear"/>
          </article>
          <!-- /动漫列表 结束-->
        </div>
      </section>
    </section>
    <!-- /制作方介绍 结束 -->
  </div>
</template>

<script>
import adminApi from '~/api/admin'

export default {
  async asyncData(page) {
    const response = await adminApi.getMakerInfoById(page.route.params.id)
    return {
      admin: response.data.item.admin,
      animes: response.data.item.animeList
    }
  }
}
</script>

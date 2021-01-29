<template>

  <div>
    <!-- 幻灯片 开始 -->
    <div v-swiper:mySwiper="swiperOption">
      <div class="swiper-wrapper">
        <div v-for="(item, index) in slidWindowResponse" :key="index" :style="'background:'+item.color" style="height:700px" class="swiper-slide">
          <a :href="item.linkUrl" target="_blank">
            <img :src="item.imageUrl" :alt="item.title" height="700px">
          </a>
        </div>
      </div>
      <div class="swiper-pagination swiper-pagination-white"/>
      <div slot="button-prev" class="swiper-button-prev swiper-button-white"/>
      <div slot="button-next" class="swiper-button-next swiper-button-white"/>
    </div>
    <!-- 幻灯片 结束 -->

    <div id="aCoursesList">
      <!-- 动漫开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">热门番剧</span>
            </h2>
          </header>
          <div>
            <article class="comm-anime-list">
              <ul id="bna" class="of">
                <li v-for="(anime, index) in animeList" :key="index">
                  <div class="cc-l-wrap">
                    <section class="anime-img">
                      <img
                        :src="anime.cover"
                        :alt="anime.title"
                        class="img-responsive"
                      >
                      <div class="cc-mask">
                        <a :href="'/anime/'+anime.id" title="开始观看" class="comm-btn c-btn-1">开始观看</a>
                      </div>
                    </section>
                    <h3 class="hLh30 txtOf mt10">
                      <a :href="'/anime/'+anime.id" :title="anime.title" class="anime-title fsize18 c-333">{{ anime.title }}</a>
                    </h3>
                    <section class="mt10 hLh20 of">
                      <span class="fr jgTag">
                        <i style="font-size:16px;" class="c-blue fsize12 f-fA">更新至 {{ anime.animeNum }} 集</i>
                      </span>
                      <span class="fl jgAttr c-ccc f-fA">
                        <i style="font-size:19px;" class="c-999 f-fA">播放量：{{ anime.viewCount }}</i>
                      </span>
                    </section>
                  </div>
                </li>

              </ul>
              <div class="clear"/>
            </article>
            <section class="tac pt20">
              <a href="/anime" title="全部动漫" class="comm-btn c-btn-2">全部动漫</a>
            </section>
          </div>
        </section>
      </div>
      <!-- /动漫结束 -->
      <!-- 制作方开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">热门制作方</span>
            </h2>
          </header>
          <div>
            <article class="i-admin-list">
              <ul class="of">
                <li v-for="(admin, index) in adminList" :key="index">
                  <section class="i-admin-wrap">
                    <div class="i-admin-pic">
                      <a :href="'/maker/'+admin.id" :title="admin.name">
                        <img :alt="admin.name" :src="admin.avatar" width="142">
                      </a>
                    </div>
                    <div class="mt10 hLh30 txtOf tac">
                      <a :href="'/maker/'+admin.id" :title="admin.name" class="fsize18 c-666">{{ admin.name }}</a>
                    </div>
                  </section>
                </li>
              </ul>
              <div class="clear"/>
            </article>
            <section class="tac pt20">
              <a href="/maker" title="全部制作方" class="comm-btn c-btn-2">全部制作方</a>
            </section>
          </div>
        </section>
      </div>
      <!-- /网校名师 结束 -->
    </div>
  </div>
</template>

<script>
import indexApi from '~/api/index'

export default {

  async asyncData() {
    const slidWindowResponse = await indexApi.getSlidWindow()
    const listResponse = await indexApi.getList()
    return {
      slidWindowResponse: slidWindowResponse.data.items,
      animeList: listResponse.data.animes,
      adminList: listResponse.data.admins
    }
  },

  data() {
    return {
      swiperOption: {
        // 配置分页
        pagination: {
          el: '.swiper-pagination'// 分页的dom节点
        },
        // 配置导航
        navigation: {
          nextEl: '.swiper-button-next', // 下一页dom节点
          prevEl: '.swiper-button-prev'// 前一页dom节点
        }
      }
    }
  }
}
</script>

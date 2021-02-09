<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- 动漫详情 开始 -->
    <section class="container">

      <!-- 动漫所属分类 开始 -->
      <section class="path-wrap txtOf hLh30">
        <a href="/" title class="c-999 fsize14">首页</a>
        \
        <a href="/anime" title class="c-999 fsize14">动漫列表</a>
        \
        <a :href="'/anime?subjectParentId='+animeInfo.subjectLevelOneId" class="c-333 fsize14">{{ animeInfo.subjectLevelOne }}</a>
        \
        <a
          :href="'/anime?subjectParentId='+
            animeInfo.subjectLevelOneId+'&subjectId='+
          animeInfo.subjectLevelTwoId"
          class="c-333 fsize14">{{ animeInfo.subjectLevelTwo }}</a>
      </section>
      <!-- /动漫所属分类 结束 -->

      <!-- 动漫基本信息 开始 -->
      <div>
        <article class="c-v-pic-wrap" style="height: 357px;">
          <section id="videoPlay" class="p-h-video-box">
            <img :src="animeInfo.cover" :alt="animeInfo.title" width="640" height="360" class="dis c-v-pic">
          </section>
        </article>
        <aside class="c-attr-wrap">
          <section class="ml20 mr15">
            <h2 class="hLh30 txtOf mt15">
              <span class="c-fff fsize24">{{ animeInfo.title }}</span>
            </h2>
            <section class="c-attr-jg">
              <b v-if="Number(animeInfo.price) === 0" class="c-blue2" style="font-size:24px;">免费观看</b>
              <b v-else class="c-blue2" style="font-size:24px;">💰{{ animeInfo.price }} 软妹币</b>
            </section>
            <section class="c-attr-mt c-attr-undis">
              <span class="c-fff fsize14">制作方：{{ animeInfo.adminName }}&nbsp;&nbsp;&nbsp;</span>
            </section>
            <section class="c-attr-mt of">
              <span v-if="!isCollect" class="ml10 vam">
                👺
                <el-button
                  style="cursor:pointer"
                  type="success"
                  class="c-fff vam"
                  title="收藏"
                  @click="addCollectAnime(animeInfo.id)" >收藏</el-button>
              </span>
              <span v-if="isCollect" class="ml10 vam">
                🤡
                <el-button
                  style="cursor:pointer"
                  type="warning"
                  class="c-fff vam"
                  title="取消收藏"
                  @click="deleteCollectAnime(animeInfo.id)" >取消收藏</el-button>
              </span>
            </section>
            <section v-if="isBuy || animeInfo.price === 0" class="c-attr-mt">
              <a
                href="javascript:(0)"
                title="立即观看"
                class="comm-btn c-btn-3"
              >立即观看
              </a>
            </section>
            <section v-else class="c-attr-mt">
              <a
                href="javascript:(0)"
                title="立即购买"
                class="comm-btn c-btn-3"
                @click="createOrder(animeInfo.id)">立即购买
              </a>
            </section>
          </section>
        </aside>
        <aside class="thr-attr-box">
          <ol class="thr-attr-ol">
            <li>
              <p>&nbsp;</p>
              <aside>
                <span style="color: #4169E1" class="c-fff f-fM">集数</span>
                <br>
                <h6 style="color: #4169E1" class="c-fff f-fM mt10">{{ animeInfo.animeNum }}</h6>
              </aside>
            </li>
            <li>
              <p>&nbsp;</p>
              <aside>
                <img width="80px" height="80px" src="~/assets/img/logo5.png">
              </aside>
            </li>
            <li>
              <p>&nbsp;</p>
              <aside>
                <span style="color: #4169E1" class="c-fff f-fM">播放量</span>
                <br>
                <h6 style="color: #4169E1" class="c-fff f-fM mt10">{{ animeInfo.viewCount }}</h6>
              </aside>
            </li>
          </ol>
        </aside>
        <div class="clear"/>
      </div>
      <!-- /动漫基本信息 结束 -->

      <div class="mt20 c-infor-box">
        <article class="fl col-7">
          <section class="mr30">
            <div class="i-box">
              <div>
                <section id="c-i-tabTitle" class="c-infor-tabTitle c-tab-title">
                  <a name="c-i" class="current" title="动漫介绍">动漫介绍</a>
                </section>
              </div>
              <article class="ml10 mr10 pt20">

                <!-- 动漫详情介绍 开始 -->
                <div>
                  <h6 class="c-i-content c-infor-title">
                    <span>动漫介绍</span>
                  </h6>
                  <div class="anime-txt-body-wrap">
                    <section class="anime-txt-body" v-html="animeInfo.description">
                      <!-- 将内容中的html翻译过来 -->
                      {{ animeInfo.description }}
                    </section>
                  </div>
                </div>
                <!-- /动漫详情介绍 结束 -->

                <!-- 动漫每一集以及每一季 开始-->
                <div class="mt50">
                  <h6 class="c-g-content c-infor-title">
                    <span>动漫目录</span>
                  </h6>
                  <section class="mt20">
                    <div class="lh-menu-wrap">
                      <menu id="lh-menu" class="lh-menu mt10 mr10">
                        <ul>
                          <!-- 目录 -->
                          <li
                            v-for="(item, index) in chapterList"
                            :key="index"
                            class="lh-menu-stair">
                            <a :title="item.title" href="javascript: void(0)" class="current-1">
                              <em class="lh-menu-i-1 icon18 mr10"/>{{ item.title }}
                            </a>
                            <ol class="lh-menu-ol" style="display: block;">
                              <li
                                v-for="(video, index) in item.childrenVideo"
                                :key="index"
                                class="lh-menu-second ml30">
                                <a
                                  v-if="video.videoSourceId !== null && (isBuy || animeInfo.price === 0 || video.free)"
                                  :href="'/player/'+video.videoSourceId"
                                  :title="video.title"
                                  target="_blacnk" >
                                  <span class="fr">
                                    <i class="free-icon vam mr10">让我康康</i>
                                  </span>
                                  <em class="lh-menu-i-2 icon16 mr5">&nbsp;</em>{{ video.title }}
                                </a>
                                <a
                                  v-else-if="video.videoSourceId !== null"
                                  :title="video.title"
                                  href="javascript:void(0)"
                                >
                                  <span class="fr">
                                    <i class="free-icon vam mr10">请先购买</i>
                                  </span>
                                  <em class="lh-menu-i-2 icon16 mr5">&nbsp;</em>{{ video.title }}
                                </a>
                              </li>
                            </ol>
                          </li>
                        </ul>
                      </menu>
                    </div>
                  </section>
                  <!-- /动漫每一集以及每一季 结束 -->
              </div></article>
            </div>
          </section>
        </article>
        <aside class="fl col-3">
          <div class="i-box">
            <!-- 制作方 开始-->
            <div>
              <section class="c-infor-tabTitle c-tab-title">
                <a title href="javascript:void(0)">{{ animeInfo.adminName }}</a>
              </section>
              <section class="stud-act-list">
                <ul style="height: auto;">
                  <li style="padding-left: 0px; height: auto" >
                    <div class="u-face">
                      <a :href="'/maker/'+animeInfo.adminId" target="_blank">
                        <img :src="animeInfo.avatar" style="border-radius: 0%" width="100" height="20" alt>
                      </a>
                    </div>
                    <section style="padding-top:30px">
                      <span class="c-999">{{ animeInfo.intro }}</span>
                    </section>
                  </li>
                </ul>
              </section>
            </div>
            <!-- /制作方 结束 -->
          </div>
        </aside>
        <div class="clear"/>
      </div>
    </section>
    <!-- /动漫详情 结束 -->
  </div>
</template>

<script>
import animeApi from '~/api/anime'
import orderApi from '~/api/order'
import collectApi from '~/api/collect'
import cookie from 'js-cookie'

export default {
  async asyncData(page) {
    const id = page.route.params.id
    const animeResponse = await animeApi.animeInfo(id)
    const animeInfo = animeResponse.data.item
    const chapterList = animeResponse.data.chapterList
    return {
      animeInfo: animeInfo,
      chapterList: chapterList
    }
  },

  data() {
    return {
      isBuy: false,
      isCollect: false
    }
  },

  created() {
    const token = cookie.get('jwt_token')
    if (!token) return
    orderApi.isBuy(this.animeInfo.id).then(response => {
      this.isBuy = response.data.isBuy
      this.refreshToken = response.data.token
      if (this.refreshToken) {
        cookie.set('jwt_token', this.refreshToken, { domain: 'localhost' })
      }
    })
    collectApi.isCollect(this.animeInfo.id).then(response => {
      this.isCollect = response.data.isCollect
    })
  },

  methods: {
    createOrder(animeId) {
      orderApi.createOrder(animeId).then(response => {
        // 跳转到订单页
        this.$router.push({ path: '/order/' + response.data.orderId })
      })
    },

    addCollectAnime(animeId) {
      collectApi.addCollect(animeId).then(response => {
        this.isCollect = true
      })
    },

    deleteCollectAnime(animeId) {
      collectApi.removeById(animeId).then(response => {
        this.isCollect = false
      })
    }
  }
}
</script>

<style>
  .anime-txt-body o1, .anime-txt-body ul{
    padding-left: 40px;
    margin: 16px 0;
  }
  .anime-txt-body o1 li{
    list-style: decimal;
  }
  .anime-txt-body ul li{
    list-style: disc;
  }
</style>

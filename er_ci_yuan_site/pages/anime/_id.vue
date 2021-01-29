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
      <!-- /课程所属分类 结束 -->

      <!-- 动漫基本信息 开始 -->
      <div>
        <article class="c-v-pic-wrap" style="height: 357px;">
          <section id="videoPlay" class="p-h-video-box">
            <img :src="animeInfo.cover" :alt="animeInfo.title" class="dis c-v-pic">
          </section>
        </article>
        <aside class="c-attr-wrap">
          <section class="ml20 mr15">
            <h2 class="hLh30 txtOf mt15">
              <span class="c-fff fsize24">{{ animeInfo.title }}</span>
            </h2>
            <section class="c-attr-jg">
              <b v-if="Number(animeInfo.price) === 0" class="c-blue2" style="font-size:24px;">免费观看</b>
              <b v-else class="c-blue2" style="font-size:24px;">${{ animeInfo.price }}</b>
            </section>
            <section class="c-attr-mt c-attr-undis">
              <span class="c-fff fsize14">制作方：{{ animeInfo.adminName }}&nbsp;&nbsp;&nbsp;</span>
            </section>
            <section class="c-attr-mt of">
              <span class="ml10 vam">
                <em class="icon18 scIcon"/>
                <a class="c-fff vam" title="收藏" href="#" >收藏</a>
              </span>
            </section>
            <section class="c-attr-mt">
              <a href="#" title="立即观看" class="comm-btn c-btn-3">立即观看</a>
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
                  <a name="c-i" class="current" title="课程详情">课程详情</a>
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
                                <a :href="'/player/'+video.videoSourceId" :title="video.title" target="_blacnk" >
                                  <span v-if="video.free === true" class="fr">
                                    <i class="free-icon vam mr10">免费观看</i>
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
                  <li style="padding-left: 0px" >
                    <div class="u-face">
                      <a :href="'/maker/'+animeInfo.adminId" target="_blank">
                        <img :src="animeInfo.avatar" style="border-radius: 0%" width="100" height="20" alt>
                      </a>
                    </div>
                    <br><br>
                    <section class="hLh20 txtOf">
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

export default {
  async asyncData(page) {
    const animeResponse = await animeApi.animeInfo(page.route.params.id)
    const animeInfo = animeResponse.data.item
    const chapterList = animeResponse.data.chapterList
    return {
      animeInfo: animeInfo,
      chapterList: chapterList
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

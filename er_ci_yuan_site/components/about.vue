<template>
  <div

    style="width:24%; float:right; background-color:#F0F8FF"
    class="bg-fa of">
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
    <!-- 动漫每一集以及每一季 开始-->
    <div class="mt50">
      <section class="mt20">
        <div class="lh-menu-wrap">
          <menu id="lh-menu" class="lh-menu mt10 mr10">
            <ul>
              <!-- 目录 -->
              <li
                v-for="(item, index) in chapterList"
                :key="index"
                class="lh-menu-stair">
                <el-collapse accordion>
                  <el-collapse-item>
                    <template slot="title">
                      <a :title="item.title" href="javascript: void(0)">
                        <em/>{{ item.title }}
                      </a>
                    </template>
                    <ol class="lh-menu-ol" style="display: block;">
                      <li
                        v-for="(video, index) in item.childrenVideo"
                        :key="index"
                        class="lh-menu-second ml30">
                        <!--<a
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
                      @click="checkUserInfo()"
                    >
                      <span class="fr">
                        <i class="free-icon vam mr10" >请先购买</i>
                      </span>
                      <em class="lh-menu-i-2 icon16 mr5">&nbsp;</em>{{ video.title }}
                    </a>-->
                        <a
                          :href="'/player/'+video.videoSourceId"
                          :title="video.title"
                          style="width: 75px;
                              float: left;
                              height: 50px;
                              margin-bottom:10px"
                          target="_blacnk" >
                          <el-card
                            class="card"
                            body-style="padding:0px"
                            shadow="hover">
                            {{ index+1 }}
                          </el-card>
                        </a>
                      </li>
                    </ol>
                  </el-collapse-item>
                </el-collapse>
              </li>
            </ul>
          </menu>
        </div>
      </section>
      <!-- /动漫每一集以及每一季 结束 -->
    </div>
  </div>

</template>

<script>
import animeApi from '~/api/anime'
import cookie from 'js-cookie'

export default {
  name: 'About',
  data() {
    return {
      animeInfo: '',
      chapterList: '',
      isBuy: cookie.get('buyStatus')
    }
  },

  mounted() {

  },

  created() {
    animeApi.getAnimeInfoByVideoSourceId(this.$route.params.id).then(res => {
      this.animeInfo = res.data.item
      this.chapterList = res.data.chapterList
    })
  },

  methods: {
    checkUserInfo() {
      if (!cookie.get('jwt_token')) {
        window.location.href = '/login'
      }
    }
  }

}
</script>

<style scoped>

</style>

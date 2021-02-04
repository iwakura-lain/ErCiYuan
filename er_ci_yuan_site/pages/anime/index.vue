<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- 课程列表 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">全部动漫</span>
        </h2>
      </header>
      <section class="c-sort-box">
        <section class="c-s-dl">
          <dl>
            <dt>
              <span class="c-999 fsize14">动漫分类</span>
            </dt>
            <dd class="c-s-dl-li">
              <!-- 一级类别 开始-->
              <ul class="clearfix">
                <li :class="{current:!$route.query.subjectParentId}">
                  <a
                    title="全部"
                    href="javascript:void(0);"
                    @click="searchObjLevelOne('')">全部</a>
                </li>
                <li
                  v-for="(item, index) in subjectNestedList"
                  :key="index"
                  :class="{current:$route.query.subjectParentId === item.id}">
                  <a
                    :title="item.title"
                    href="javascript:void(0);"
                    @click="searchObjLevelOne(item.id)">{{ item.title }}</a>
                </li>
              </ul>
              <!-- /一级类别 结束-->
            </dd>
          </dl>
          <dl>
            <dt>
              <span class="c-999 fsize14"/>
            </dt>
            <dd class="c-s-dl-li">
              <!-- 二级类别 开始-->
              <ul v-if="searchObj.subjectParentId !== ''" class="clearfix">
                <li :class="{current:!$route.query.subjectId}">
                  <a
                    title="全部"
                    href="javascript:void(0);"
                    @click="searchObjLevelTwo('')"
                  >全部</a>
                </li>
                <li
                  v-for="(item, index) in levelTwoSubjectList"
                  :key="index"
                  :class="{current:$route.query.subjectId === item.id}"
                >
                  <a
                    :title="item.title"
                    href="javascript:void(0);"
                    @click="searchObjLevelTwo(item.id)">{{ item.title }}</a>
                </li>
              </ul>
              <!-- /二级类别 结束-->
            </dd>
          </dl>
          <div class="clear"/>
        </section>
        <div class="js-wrap">
          <section class="fr">
            <span class="c-ccc">
              <i class="c-master f-fM">{{ Math.ceil(total/searchObj.size) > 0 ? parseInt(searchObj.page) : 0 }}</i>/
              <i class="c-666 f-fM">{{ Math.ceil(total/searchObj.size) }}</i>
            </span>
          </section>
          <section class="fl">
            <!-- 排序 开始-->
            <ol class="js-tap clearfix">
              <li :class="{'current bg-blue': searchObj.hasViewSort !== 0}">
                <a
                  v-if="searchObj.hasViewSort == 1 || searchObj.hasViewSort == 0"
                  title="观看量"
                  href="javascript:void(0);"
                  @click="searchByViewSort()">观看量
                  <i>👇</i>
                </a>

                <a
                  v-if="searchObj.hasViewSort == -1"
                  title="观看量"
                  href="javascript:void(0);"
                  @click="searchByViewDescSort()">观看量
                  <i>☝</i>
                </a>
              </li>
              <li :class="{'current bg-blue': searchObj.hasGmtModifiedSort !== 0}">
                <a
                  v-if="searchObj.hasGmtModifiedSort == 1 || searchObj.hasGmtModifiedSort == 0"
                  title="最近更新"
                  href="javascript:void(0);"
                  @click="searchByGmtSort()">最近更新
                  <i>👇</i>
                </a>

                <a
                  v-if="searchObj.hasGmtModifiedSort == -1"
                  title="最近更新"
                  href="javascript:void(0);"
                  @click="searchByGmtDescSort()">最近更新
                  <i>☝</i>
                </a>
              </li>
              <li :class="{'current bg-blue': searchObj.hasAnimeNumSort !== 0}">
                <a
                  v-if="searchObj.hasAnimeNumSort == 1 || searchObj.hasAnimeNumSort == 0"
                  title="最近上传"
                  href="javascript:void(0);"
                  @click="searchByAnimeNumSort()">总集数
                  <i>👇</i>
                </a>

                <a
                  v-if="searchObj.hasAnimeNumSort == -1"
                  title="最近上传"
                  href="javascript:void(0);"
                  @click="searchByAnimeNumDescSort()">总集数
                  <i>☝</i>
                </a>
              </li>
            </ol>
            <!-- /排序 结束-->
          </section>
        </div>
        <div class="mt40">
          <!-- /无数据提示 开始-->
          <section v-if="animeList.length === 0" class="no-data-wrap">
            <img
              src="~/assets/img/noData.png"
              style="width:150px; height:150px; display: inline"
              class="img-responsive">
            <br>
            <span class="c-666 fsize14 ml10 vam">没有相关动漫</span>
          </section>
          <!-- /无数据提示 结束-->

          <!-- 数据列表 开始-->
          <article v-if="animeList.length !== 0" class="comm-anime-list">
            <ul id="bna" class="of">
              <li v-for="(item, index) in animeList" :key="index">
                <div class="cc-l-wrap">
                  <section class="anime-img">
                    <img :alt="item.title" :src="item.cover" style="height: 177px; box-shadow: 300px 300px 500px #6495ED;" class="img-responsive">
                    <div class="cc-mask">
                      <nuxt-link :to="'/anime/'+item.id" title="开始观看" class="comm-btn c-btn-1">
                        开始观看
                      </nuxt-link>
                    </div>
                  </section>
                  <h3 style="text-align:center;" class="hLh30 txtOf mt10">
                    <nuxt-link :to="'/anime/'+item.id" :title="item.title" class="anime-title fsize18 c-333">
                      {{ item.title }}
                    </nuxt-link>
                  </h3>
                  <section class="mt10 hLh20 of">
                    <span style="border-radius:10%" class="fr jgTag bg-blue">
                      <i class="c-fff fsize12 f-fA">{{ item.price === 0 ? '免费观看':'带会员限定' }}</i>
                    </span>
                    <span class="fl jgAttr c-ccc f-fA">
                      <i class="c-999 f-fA">观看数：{{ item.viewCount }}</i>
                    </span>
                  </section>
                </div>
              </li>
            </ul>
            <div class="clear"/>
          </article>
          <!-- /数据列表 结束-->
        </div>
      </section>
    </section>
    <!-- /课程列表 结束 -->
    <!-- 分页组件 -->
    <el-pagination
      :current-page="pages"
      :total="total"
      :page-size="size"
      style="padding: 30px 0; text-align: center"
      layout="prev, pager, next, jumper, ->"
      @current-change="changeCurrentPage"
    />
  </div>
</template>

<script>
import animeApi from '~/api/anime'
import queryString from 'querystring'

export default {

  async asyncData(page) {
    const searchObj = {}
    const query = page.route.query
    searchObj.subjectParentId = query.subjectParentId || ''
    searchObj.subjectId = query.subjectId || ''
    // 状态，1为从高到低，-1为从低到高
    searchObj.title = query.title || ''
    searchObj.hasAnimeNumSort = query.hasAnimeNumSort || 0
    searchObj.hasViewSort = query.hasViewSort || 0
    searchObj.hasGmtModifiedSort = query.hasGmtModifiedSort || 0
    searchObj.page = query.page || 1
    searchObj.size = 8
    // 动漫列表
    const animeListResponse = await animeApi.pageList(searchObj)
    const animeList = animeListResponse.data.items
    const total = animeListResponse.data.total
    // 类别列表
    const subjectNestedListResponse = await animeApi.nestedList()
    const subjectNestedList = subjectNestedListResponse.data.items
    // 根据存在的一级类别查询对应的二级类别列表
    let levelTwoSubjectList = []
    subjectNestedList.forEach(subject => {
      if (subject.id === searchObj.subjectParentId) {
        levelTwoSubjectList = subject.childrenSubject
      }
    })
    return {
      subjectNestedList: subjectNestedList,
      animeList: animeList,
      levelTwoSubjectList: levelTwoSubjectList,
      searchObj: searchObj,
      pages: Number.parseInt(searchObj.page),
      size: Number.parseInt(searchObj.size),
      total: total
    }
  },

  methods: {
    // 选择一级类别事件
    searchObjLevelOne(subjectParentId) {
      const paramsObj = {
        title: this.searchObj.title,
        subjectParentId: subjectParentId
      }
      const params = queryString.stringify(paramsObj)
      window.location = 'anime?' + params
    },

    // 选择二级类别事件
    searchObjLevelTwo(subjectId) {
      const paramsObj = {
        title: this.searchObj.title,
        subjectId: subjectId,
        subjectParentId: this.searchObj.subjectParentId
      }
      const params = queryString.stringify(paramsObj)
      window.location = 'anime?' + params
    },

    searchByViewSort() {
      const paramsObj = {
        title: this.searchObj.title,

        subjectId: this.searchObj.subjectId,
        subjectParentId: this.searchObj.subjectParentId,
        hasViewSort: -1
      }
      const params = queryString.stringify(paramsObj)
      window.location = 'anime?' + params
    },

    searchByGmtSort() {
      const paramsObj = {
        title: this.searchObj.title,
        subjectId: this.searchObj.subjectId,
        subjectParentId: this.searchObj.subjectParentId,
        hasGmtModifiedSort: -1
      }
      const params = queryString.stringify(paramsObj)
      window.location = 'anime?' + params
    },

    searchByAnimeNumSort() {
      const paramsObj = {
        title: this.searchObj.title,
        subjectId: this.searchObj.subjectId,
        subjectParentId: this.searchObj.subjectParentId,
        hasAnimeNumSort: -1
      }
      const params = queryString.stringify(paramsObj)
      window.location = 'anime?' + params
    },

    searchByViewDescSort() {
      const paramsObj = {
        title: this.searchObj.title,
        subjectId: this.searchObj.subjectId,
        subjectParentId: this.searchObj.subjectParentId,
        hasViewSort: 1
      }
      const params = queryString.stringify(paramsObj)
      window.location = 'anime?' + params
    },

    searchByGmtDescSort() {
      const paramsObj = {
        title: this.searchObj.title,
        subjectId: this.searchObj.subjectId,
        subjectParentId: this.searchObj.subjectParentId,
        hasGmtModifiedSort: 1
      }
      const params = queryString.stringify(paramsObj)
      window.location = 'anime?' + params
    },

    searchByAnimeNumDescSort() {
      const paramsObj = {
        title: this.searchObj.title,
        subjectId: this.searchObj.subjectId,
        subjectParentId: this.searchObj.subjectParentId,
        hasAnimeNumSort: 1
      }
      const params = queryString.stringify(paramsObj)
      window.location = 'anime?' + params
    },

    // 改变页码
    changeCurrentPage(page) {
      console.log(page)
      this.searchObj.page = page
      const paramsObj = {
        title: this.searchObj.title,
        subjectId: this.searchObj.subjectId,
        subjectParentId: this.searchObj.subjectParentId
      }
      if (this.searchObj.hasViewSort !== 0) {
        paramsObj.hasViewSort = 1
      }
      if (this.searchObj.hasGmtModifiedSort !== 0) {
        paramsObj.hasGmtModifiedSort = 1
      }
      if (this.searchObj.hasAnimeNumSort !== 0) {
        paramsObj.hasAnimeNumSort = 1
      }
      const params = queryString.stringify(paramsObj)
      window.location = 'anime?' + params + '&page=' + page
    }
  }

}
</script>

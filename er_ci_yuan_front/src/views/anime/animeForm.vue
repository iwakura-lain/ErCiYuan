<template>
  <div class="app-container">

    <!-- 步骤导航 -->
    <h2 style="text-align: center;">发布新动漫</h2>
    <el-steps :active="active" finish-status="success" simple style="margin-bottom: 40px">
      <el-step icon="el-icon-edit" title="填写动漫基本信息" />
      <el-step icon="el-icon-upload" title="上传动漫内容" />
      <el-step icon="el-icon-star-on" title="发布动漫" />
    </el-steps>

    <!-- 步骤内容 -->
    <!-- step1：Info -->
    <info v-if="active === 0" />

    <!-- step2：ChapterIndex -->
    <chapter v-if="active === 1" />

    <!-- step3：Publish -->
    <publish v-if="active === 2 || active === 3" />

  </div>
</template>

<script>
// 引入子组件
import Info from '@/views/anime/components/info'
import Chapter from '@/views/anime/components/Chapter/chapterIndex' // 找Chapter.vue 或 Chapter/Index.vue
import Publish from '@/views/anime/components/publish'

export default {

  components: { Info, Chapter, Publish }, // 注册子组件

  data() {
    return {
      active: 0,
      animeId: null
    }
  },

  created() {
    if (this.$route.name === 'animeChapter') {
      this.animeId = this.$route.params.id
      this.active = 1
    }
    if (this.$route.params.id && this.$route.name === 'animeInfoEdit') {
      this.animeId = this.$route.params.id
    }
  }
}
</script>

<style>
    .el-steps--simple{
      background-image: linear-gradient(to right, #74ebd5 0%, #9face6 100%);
    }
    .el-step__title.is-wait{
        font-weight: bold;
        color: #251e31;
    }
    .el-step__head.is-wait{
        color: #060408;
    }
</style>

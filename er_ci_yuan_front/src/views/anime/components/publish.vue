<template>
  <div class="app-container">

    <!--动漫预览-->
    <div class="aInfo">
      <img :src="animePublish.cover">
      <div class="main">
        <h2>{{ animePublish.title }}</h2>
        <p class="gray"><span>共{{ animePublish.animeNum }}集</span></p>
        <p><span>所属分类：{{ animePublish.subjectParentTitle }} ——— {{ animePublish.subjectTitle }}</span></p>
        <p>制作方：{{ animePublish.adminName }}</p>
        <h3 class="red">￥{{ animePublish.price }}</h3>
      </div>
    </div>

    <div style="text-align:center">
      <el-button type="primary" @click="prev()">上一步</el-button>
      <router-link :to="'/anime/list/'">
        <el-button :disabled="publishBtnDisabled" type="primary" @click="publish()">发布动漫</el-button>
      </router-link>
    </div>
  </div>
</template>

<script>
import animeApi from '@/api/anime'
export default {

  data() {
    return {
      publishBtnDisabled: false, // 按钮是否禁用
      animePublish: {}

    }
  },

  created() {
    if (this.$parent.animeId) {
      this.fetchData(this.$parent.animeId)
    }
  },

  methods: {
    // 上一步
    prev() {
      this.$parent.active = 1
    },

    fetchData(id) {
      animeApi.getPublishAnime(id).then(response => {
        this.animePublish = response.data.item
      })
    },

    // 下一步
    publish() {
      this.publishBtnDisabled = true
      animeApi.updateAnimeStatus(this.$parent.animeId).then(response => {
        this.$parent.active = 3
        this.$message.success(response.message)
      })
    }
  }
}
</script>

<style>
.aInfo {
    background: #f5f5f5;
    padding: 20px;
    overflow: hidden;
    border: 1px dashed #DDD;
    margin-bottom: 40px;
    position: relative;
}
.aInfo img {
    background: #d6d6d6;
    width: 500px;
    height: 278px;
    display: block;
    float: left;
    border: none;
}
.aInfo .main {
    margin-left: 520px;
}

.aInfo .main h2 {
    font-size: 28px;
    margin-bottom: 30px;
    line-height: 1;
    font-weight: normal;
}
.aInfo .main p {
    margin-bottom: 10px;
    word-wrap: break-word;
    line-height: 24px;
    max-height: 48px;
    overflow: hidden;
}

.aInfo .main p {
    margin-bottom: 10px;
    word-wrap: break-word;
    line-height: 24px;
    max-height: 48px;
    overflow: hidden;
}
.aInfo .main h3 {
    left: 540px;
    bottom: 20px;
    line-height: 1;
    font-size: 28px;
    color: #d32f24;
    font-weight: normal;
    position: absolute;
}
</style>


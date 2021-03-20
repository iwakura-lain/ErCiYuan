<template>
  <div class="app-container">
    <!-- 季列表 -->
    <!-- 添加新一季 -->
    <div>
      <el-button type="primary" @click="addChapter()">添加最新季</el-button>
    </div>

    <!-- 季列表 -->
    <ul class="chapterList">
      <li v-for="chapter in chapterList" :key="chapter.id">
        <p class="parent">{{ chapter.title }}
          <span class="acts">
            <el-button type="success" @click="addVideo(chapter.id)">添加最新集</el-button>
            <el-button type="primary" @click="editChapter(chapter.id)">编辑</el-button>
            <el-button type="danger" @click="removeChapterById(chapter.id)">删除</el-button>
          </span>
        </p>
        <ul class="chapterList videoList">
          <li v-for="video in chapter.childrenVideo" :key="video.id">
            <p>{{ video.title }}
              <el-tag v-if="!video.videoSourceId" size="medium" type="danger">
                {{ '尚未上传视频' }}
              </el-tag>
              <el-tag v-if="video.free" size="medium" type="success">
                {{ '不需要带会员' }}
              </el-tag>
              <span class="acts">
                <el-button size="small" type="primary" @click="editVideo(chapter.id, video.id)">编辑</el-button>
                <el-button size="small" type="danger" @click="removeVideoById(video.id)">删除</el-button>
              </span>
            </p>
          </li>
        </ul>
      </li>
    </ul>

    <!-- 季表单对话框 -->
    <chapter-form ref="chapterForm" />

    <!-- 集表单对话框 -->
    <video-form ref="videoForm" />

    <div style="text-align:center">
      <el-button type="primary" @click="prev()">上一步</el-button>
      <el-button type="primary" @click="next()">下一步</el-button>
    </div>

  </div>
</template>

<script>
import chapterApi from '@/api/chapter'
import videoApi from '@/api/video'
import chapterForm from '@/views/anime/components/Chapter/chapterForm'
import videoForm from '@/views/anime/components/Video/videoForm'

export default {
  components: {
    chapterForm,
    videoForm
  },

  data() {
    return {
      chapterList: []
    }
  },

  created() {
    this.fetchTreeList()
  },

  methods: {
    fetchTreeList() {
      chapterApi.getNestedTreeList(this.$parent.animeId)
        .then(response => {
          this.$parent.animeId = this.$parent.animeId
          this.chapterList = response.data.items
        })
    },

    // 添加季
    addChapter() {
      this.$refs.chapterForm.open()
    },

    // 编辑季
    editChapter(chapterId) {
      this.$refs.chapterForm.open(chapterId)
    },

    // 添加集
    addVideo(chapterId) {
      this.$refs.videoForm.open(chapterId)
    },

    // 编辑ji
    editVideo(chapterId, videoId) {
      this.$refs.videoForm.open(chapterId, videoId)
    },

    removeVideoById(videoId) {
      // 询问是否删除
      this.$confirm('将删除该集, 是否确定?', '警告！！！', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        videoApi.deleteById(videoId).then(response => {
          // 刷新数据
          this.fetchTreeList()
          // 弹出成功提示
          this.$notify({
            message: '<strong> <b style="color:green">删除成功<b><strong>',
            dangerouslyUseHTMLString: true,
            type: 'success'
          })
        })
      }).catch((err) => {
        if (err === 'cancel') {
          this.$notify({
            type: 'info',
            dangerouslyUseHTMLString: true,
            message: '<strong> <b style="color:#8A2BE2">取消删除<b> <strong>'
          })
        }
      })
    },

    removeChapterById(chapterId) {
      // 询问是否删除
      this.$confirm('将删除该季, 是否确定?', '警告！！！', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        chapterApi.deleteById(chapterId).then(response => {
          // 刷新数据
          this.fetchTreeList()
          // 弹出成功提示
          this.$notify({
            message: '<strong> <b style="color:green">删除成功<b><strong>',
            dangerouslyUseHTMLString: true,
            type: 'success'
          })
        })
      }).catch((err) => {
        if (err === 'cancel') {
          this.$notify({
            type: 'info',
            dangerouslyUseHTMLString: true,
            message: '<strong> <b style="color:#8A2BE2">取消删除<b> <strong>'
          })
        }
      })
    },

    // 上一步
    prev() {
      this.$parent.active = 0
    },

    // 下一步
    next() {
      this.$parent.active = 2
    }
  }
}
</script>

<style>
.chapterList{
    position: relative;
    list-style: none;
    margin: 0;
    padding: 0;
}
.chapterList li{
  position: relative;
}
.chapterList p{
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border-radius:10px;
}
.chapterList .acts {
    float: right;
    font-size: 14px;
}

.videoList{
  padding-left: 50px;
}
.videoList p{
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dashed rgb(141, 219, 134);
  border-radius:10px;
}

.parent{
  background-image: linear-gradient(to top, #fff1eb 0%, #ace0f9 100%);
}
</style>

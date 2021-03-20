<template>
  <div>
    <div v-clickoutside="hideReplyBtn" class="my-reply" @click="inputFocus">
      <div class="reply-info" >
        <div
          id="replyInput"
          placeholder="留言，滴...."
          tabindex="0"
          contenteditable="true"
          spellcheck="false"
          class="reply-input"
          @focus="showReplyBtn"
          @input="onDivInput($event)"
        />
      </div>
      <div v-show="btnShow" class="reply-btn-box">
        <el-button class="reply-btn" size="medium" type="primary" @click="sendComment">发表评论</el-button>
      </div>
    </div>

    <div v-for="(item,i) in comments" :key="i" class="author-title reply-father">
      <img :src="item.userAvatar" width="40px" height="40px" style="border-radius: 25px" class="header-img">
      <div class="author-info">
        <span class="author-name">{{ item.userNickname }}</span>
        <span class="author-time">{{ item.gmtCreate }}</span>
      </div>
      <div class="icon-btn">
        <span @click="showReplyInput(i, item.userNickname, item.userId, item.id)"><i
          class="el-icon-chat-dot-square"/>💬</span>
          <!--<i/>👍{{ item.like }}-->
      </div>
      <div class="talk-box">
        <p>
          <section class="reply" v-html="item.comment">
            <!-- 将内容中的html翻译过来 -->
            {{ item.comment }}
          </section>
        </p>
      </div>
      <div class="reply-box">
        <div v-for="(reply,j) in item.childrenComments" :key="j" class="author-title">
          <img :src="reply.userAvatar" width="40px" height="40px" style="border-radius: 25px" class="header-img">
          <div class="author-info">
            <span class="author-name">{{ reply.userNickname }}</span>
            <span class="author-time">{{ reply.gmtCreate }}</span>
          </div>
          <div class="icon-btn">
            <span @click="showReplyInput(i, reply.userNickname, reply.userId, reply.id)">
            <i class="el-icon-chat-dot-square"/>💬 </span>
            <!--<i class="el-icon-arrow-up"/>{{ reply.like }}-->
          </div>
          <div class="talk-box">
            回复 {{ reply.toNickname }}：
            <span style="color:#00008B	" class="reply" v-html="reply.comment">
              {{ reply.comment }}
            </span>
          </div>
          <div class="reply-box"/>
        </div>
      </div>
      <div v-show="_inputShow(i)" class="my-reply my-comment-reply">
        <div class="reply-info" >
          <div
            :placeholder="'回复：'+toName"
            tabindex="0"
            contenteditable="true"
            spellcheck="false"
            class="reply-input reply-comment-input"
            @input="onDivInput($event)"/>
        </div>
        <div class=" reply-btn-box">
          <el-button
            class="reply-btn"
            size="medium"
            type="primary"
            @click="sendCommentReply(i, reply)">发表评论</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import loginApi from '~/api/login'
import cookie from 'js-cookie'
import commentApi from '~/api/comment'
import About from '~/components/About.vue'

const clickoutside = {
  // 初始化指令
  bind(el, binding, vnode) {
    function documentHandler(e) {
    // 这里判断点击的元素是否是本身，是本身，则返回
      if (el.contains(e.target)) {
        return false
      }
      // 判断指令中是否绑定了函数
      if (binding.expression) {
        // 如果绑定了函数 则调用那个函数，此处binding.value就是handleClose方法
        binding.value(e)
      }
    }
    // 给当前元素绑定个私有变量，方便在unbind中可以解除事件监听
    el.vueClickOutside = documentHandler
    document.addEventListener('click', documentHandler)
  },
  update() {},
  unbind(el, binding) {
    // 解除事件监听
    document.removeEventListener('click', el.vueClickOutside)
    delete el.vueClickOutside
  }
}
export default {
  name: 'AnimeComment',
  components: {
    About
  },
  directives: { clickoutside },

  data() {
    return {
      toName: '',
      btnShow: false,
      index: '0',
      replyComment: '',
      myName: '',
      myHeader: '',
      myId: null,
      replyId: '',
      to: '',
      toId: -1,
      userInfo: null,
      comments: []
    }
  },

  mounted() {
    this.getUserInfo()
    commentApi.get(this.$route.params.id).then(response => {
      this.comments = response.data.items
    })
  },

  methods: {
    getUserInfo() {
      if (!cookie.get('jwt_token')) {
        return
      }
      loginApi.getLoginInfo().then(response => {
        this.userInfo = response.data.userInfo
        this.refreshToken = response.data.token
        if (this.refreshToken) {
          cookie.set('jwt_token', this.refreshToken, { domain: 'localhost' })
        }
      })
    },

    inputFocus() {
      var replyInput = document.getElementById('replyInput')
      replyInput.style.padding = '8px 8px'
      replyInput.style.border = '2px solid blue'
      replyInput.focus()
    },
    showReplyBtn() {
      this.btnShow = true
    },
    hideReplyBtn() {
      this.btnShow = false
      var replyInput = document.getElementById('replyInput')
      replyInput.style.padding = '10px'
      replyInput.style.border = 'none'
    },
    showReplyInput(i, name, id, replyId) {
      this.toName = name
      this.comments[this.index].inputShow = false
      this.index = i
      this.comments[i].inputShow = true
      this.to = name
      this.replyId = replyId
      this.toId = id
    },
    _inputShow(i) {
      return this.comments[i].inputShow
    },
    sendComment() {
      if (!this.replyComment) {
        this.$message({
          showClose: true,
          type: 'warning',
          message: '评论不能为空'
        })
      } else {
        const a = {}
        const input = document.getElementById('replyInput')
        const timeNow = new Date().getTime()

        a.userNickname = this.userInfo.nickname
        a.comment = this.replyComment
        a.userAvatar = this.userInfo.avatar
        a.gmtCreate = this.timestampToTime(timeNow)
        a.commentNum = 0
        a.userId = this.userInfo.id
        a.parentCommentId = '0'
        a.animeId = this.$route.params.id
        a.childrenComments = []

        this.comments.push(a)

        const comment = JSON.parse(JSON.stringify(a))
        comment.gmtCreate = timeNow
        commentApi.post(comment).then(res => {
          this.$message({
            showClose: true,
            type: 'success',
            message: res.message
          })
        })
        this.replyComment = ''
        input.innerHTML = ''
      }
    },
    sendCommentReply(i, reply) {
      if (!this.replyComment) {
        this.$message({
          showClose: true,
          type: 'warning',
          message: '评论不能为空'
        })
      } else {
        const a = {}
        const timeNow = new Date().getTime()

        a.userNickname = this.userInfo.nickname
        a.comment = this.replyComment
        a.userAvatar = this.userInfo.avatar
        a.gmtCreate = this.timestampToTime(timeNow)
        a.commentNum = 0
        a.toNickname = this.to
        a.parentCommentId = this.replyId
        a.userId = this.userInfo.id
        a.animeId = this.$route.params.id

        this.comments[i].childrenComments.push(a)

        const comment = JSON.parse(JSON.stringify(a))
        comment.gmtCreate = timeNow
        commentApi.post(comment).then(res => {
          this.$message({
            showClose: true,
            type: 'success',
            message: res.message
          })
        })

        this.replyComment = ''
        document.getElementsByClassName('reply-comment-input')[i].innerHTML = ''
      }
    },
    onDivInput: function(e) {
      this.replyComment = e.target.innerHTML
    },
    timestampToTime(timestamp) {
      var date = new Date(timestamp)// 时间戳为10位需*1000，时间戳为13位的话不需乘1000
      const Y = date.getFullYear() + '-'
      const M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
      const D = date.getDate() + ' '
      const h = date.getHours() + ':'
      const m = date.getMinutes()
      return Y + M + D + h + m
    }
    /* dateStr(date) {
      // 获取js 时间戳
      var time = new Date().getTime()
      // 去掉 js 时间戳后三位
      time = parseInt((time - date) / 1000)
      // 存储转换值
      var s
      if (time < 60 * 10) { // 十分钟内
        return '刚刚'
      } else if ((time < 60 * 60) && (time >= 60 * 10)) {
        // 超过十分钟少于1小时
        s = Math.floor(time / 60)
        return s + '分钟前'
      } else if ((time < 60 * 60 * 24) && (time >= 60 * 60)) {
        // 超过1小时少于24小时
        s = Math.floor(time / 60 / 60)
        return s + '小时前'
      } else if ((time < 60 * 60 * 24 * 30) && (time >= 60 * 60 * 24)) {
        // 超过1天少于30天内
        s = Math.floor(time / 60 / 60 / 24)
        return s + '天前'
      } else {
        // 超过30天ddd
        var date2 = new Date(parseInt(date))
        return date2.getFullYear() + '/' + (date2.getMonth() + 1) + '/' + date2.getDate()
      }
    }*/
  }
}
</script>

<style lang="stylus" scoped>
.my-reply
    padding 10px
    background-color #5387e7
    width 75%
    .header-img
        display inline-block
        vertical-align top
    .reply-info
        display inline-block
        margin-left 5px
        width 100%
        @media screen and (max-width:1200px) {
            width 80%
        }
        .reply-input
            min-height 20px
            line-height 22px
            padding 10px 10px
            color #ccc
            background-color #fff
            border-radius 5px
            &:empty:before
                content attr(placeholder)
            &:focus:before
                content none
            &:focus
                padding 8px 8px
                border 2px solid blue
                box-shadow none
                outline none
    .reply-btn-box
        height 25px
        margin 10px 0
        .reply-btn
            position relative
            float right
            margin-right 15px
.reply-father
    background-color #5387e7
    width 75%
.my-comment-reply
    margin-left 50px
    width 75%
    background-color #5387e7
    .reply-input
        width flex
.author-title:not(:last-child)
    border-bottom: 3px solid rgba(255, 255, 255,.3)
.author-title
    padding 10px
    .header-img
        display inline-block
        vertical-align top
    .author-info
        display inline-block
        margin-left 5px
        width 60%
        height 40px
        line-height 20px
        >span
            display block
            cursor pointer
            overflow hidden
            white-space nowrap
            text-overflow ellipsis
        .author-name
            color #FFFFFF
            font-size 18px
            font-weight bold
        .author-time
            font-size 14px
    .icon-btn
        width 30%
        padding 0 !important
        float right
        @media screen and (max-width : 1200px){
            width 20%
            padding 7px
        }
        >span
            cursor pointer
        .iconfont
            margin 0 5px
    .talk-box
        margin 0 50px
        >p
           margin 0
        .reply
            font-size 16px
            color #FFF
    .reply-box
        margin 10px 0 0 50px
        background-color #6aa2dd
</style>

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
      <img :src="item.headImg" width="40px" height="40px" style="border-radius: 25px" class="header-img">
      <div class="author-info">
        <span class="author-name">{{ item.name }}</span>
        <span class="author-time">{{ item.time }}</span>
      </div>
      <div class="icon-btn">
        <span @click="showReplyInput(i,item.name,item.id)"><i
          class="el-icon-chat-dot-square"/>💬{{ item.commentNum }}</span>
          <!--<i/>👍{{ item.like }}-->
      </div>
      <div class="talk-box">
        <p>
          <span class="reply">{{ item.comment }}</span>
        </p>
      </div>
      <div class="reply-box">
        <div v-for="(reply,j) in item.reply" :key="j" class="author-title">
          <img :src="reply.fromHeadImg" width="40px" height="40px" style="border-radius: 25px" class="header-img">
          <div class="author-info">
            <span class="author-name">{{ reply.from }}</span>
            <span class="author-time">{{ reply.time }}</span>
          </div>
          <div class="icon-btn">
            <span @click="showReplyInput(i,reply.from,reply.id)"><i
              class="el-icon-chat-dot-square"/>💬{{ item.commentNum }}</span>
              <!--<i class="el-icon-arrow-up"/>{{ reply.like }}-->
          </div>
          <div class="talk-box">
            <p>
              <span>回复 {{ reply.to }}:</span>
              <span class="reply">{{ reply.comment }}</span>
            </p>
          </div>
          <div class="reply-box"/>
        </div>
      </div>
      <div v-show="_inputShow(i)" class="my-reply my-comment-reply">
        <div class="reply-info" >
          <div :placeholder="'回复：'+toName" tabindex="0" contenteditable="true" spellcheck="false" class="reply-input reply-comment-input" @input="onDivInput($event)"/>
        </div>
        <div class=" reply-btn-box">
          <el-button class="reply-btn" size="medium" type="primary" @click="sendCommentReply(i,j)">发表评论</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import loginApi from '~/api/login'
import cookie from 'js-cookie'

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
      to: '',
      toId: -1,
      comments: [
        {
          name: 'Lana Del Rey',
          id: 19870621,
          headImg: 'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/ucenter/2021/02/03/98edcbcd-5277-4117-ab8e-843eb741b328.jpg',
          comment: '我发布一张新专辑Norman Fucking Rockwell,大家快来听啊',
          time: '2019年9月16日 18:43',
          commentNum: 2,
          like: 15,
          inputShow: false,
          reply: [
            {
              from: 'Taylor Swift',
              fromId: 19891221,
              fromHeadImg: 'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/ucenter/2021/02/06/07b7725f-b16c-4b9a-a23d-02b5def66f71.jpg',
              to: 'Lana Del Rey',
              toId: 19870621,
              comment: '我很喜欢你的新专辑！！',
              time: '2019年9月16日 18:43',
              commentNum: 1,
              like: 15,
              inputShow: false
            },
            {
              from: 'Ariana Grande',
              fromId: 1123,
              fromHeadImg: 'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/ucenter/2021/02/06/07b7725f-b16c-4b9a-a23d-02b5def66f71.jpg',
              to: 'Lana Del Rey',
              toId: 19870621,
              comment: '别忘记宣传我们的合作单曲啊',
              time: '2019年9月16日 18:43',
              commentNum: 0,
              like: 5,
              inputShow: false

            }
          ]
        },
        {
          name: 'Taylor Swift',
          id: 19891221,
          headImg: 'https://ae01.alicdn.com/kf/H94c78935ffa64e7e977544d19ecebf06L.jpg',
          comment: '我发行了我的新专辑Lover',
          time: '2019年9月16日 18:43',
          commentNum: 1,
          like: 5,
          inputShow: false,
          reply: [
            {
              from: 'Lana Del Rey',
              fromId: 19870621,
              fromHeadImg: 'https://ae01.alicdn.com/kf/Hd60a3f7c06fd47ae85624badd32ce54dv.jpg',
              to: 'Taylor Swift',
              toId: 19891221,
              comment: '新专辑和speak now 一样棒！',
              time: '2019年9月16日 18:43',
              commentNum: 25,
              like: 5,
              inputShow: false

            }
          ]
        },
        {
          name: 'Norman Fucking Rockwell',
          id: 20190830,
          headImg: 'https://ae01.alicdn.com/kf/Hdd856ae4c81545d2b51fa0c209f7aa28Z.jpg',
          comment: 'Plz buy Norman Fucking Rockwell on everywhere',
          time: '2019年9月16日 18:43',
          commentNum: 0,
          like: 5,
          inputShow: false,
          reply: []
        }
      ]
    }
  },

  created() {
    this.getUserInfo()
  },

  methods: {
    getUserInfo() {
      if (!cookie.get('jwt_token')) {
        return
      }
      loginApi.getLoginInfo().then(response => {
        this.userInfo = response.data.userInfo
        this.myName = this.userInfo.nickname
        this.myId = this.userInfo.id
        this.myHeader = this.userInfo.avatar
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
    showReplyInput(i, name, id) {
      this.toName = name
      this.comments[this.index].inputShow = false
      this.index = i
      this.comments[i].inputShow = true
      this.to = name
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
        const time = this.dateStr(timeNow)
        a.name = this.myName
        a.comment = this.replyComment
        a.headImg = this.myHeader
        a.time = time
        a.commentNum = 0
        a.like = 0
        this.comments.push(a)
        this.replyComment = ''
        input.innerHTML = ''
      }
    },
    sendCommentReply(i, j) {
      if (!this.replyComment) {
        this.$message({
          showClose: true,
          type: 'warning',
          message: '评论不能为空'
        })
      } else {
        const a = {}
        const timeNow = new Date().getTime()
        const time = this.dateStr(timeNow)
        a.from = this.myName
        a.to = this.to
        a.fromHeadImg = this.myHeader
        a.comment = this.replyComment
        a.time = time
        a.commentNum = 0
        a.like = 0
        this.comments[i].reply.push(a)
        this.replyComment = ''
        document.getElementsByClassName('reply-comment-input')[i].innerHTML = ''
      }
    },
    onDivInput: function(e) {
      this.replyComment = e.target.innerHTML
    },
    dateStr(date) {
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
    }
  }
}
</script>

<style lang="stylus" scoped>
.my-reply
    padding 10px
    background-color #6aa2dd
    width 80%
    margin-left 10%
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
    width 80%
    margin-left 10%
.my-comment-reply
    margin-left 50px
    width 80%
    margin-left 10%
    background-color #011d6e
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

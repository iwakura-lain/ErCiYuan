<template>
  <div style="background-color:black;">
    <div id="dplayer">
      <script src="https://cdnjs.cloudflare.com/ajax/libs/dplayer/1.26.0/DPlayer.min.js"/>
    </div>
  </div>
</template>

<script>
import videoApi from '~/api/video'

export default {

  async asyncData(page) {
    // 得到视频id
    const vid = page.route.params.id
    // 获得播放地址的列表
    const response = await videoApi.getVideoUrls(vid)
    const urls = response.data.urls
    const baseUrl = urls[0].substring(0, urls[0].lastIndexOf('/'))
    // 视频不同清晰度的 url
    const ldUrl = urls[0]
    const fdUrl = urls[1]
    console.log(baseUrl)
    return {
      vid: vid,
      baseUrl: baseUrl,
      ldUrl: ldUrl,
      fdUrl: fdUrl
    }
  },

  // 页面渲染之后执行
  mounted() {
    /* eslint-disable no-undef */
    new DPlayer({
      container: document.getElementById('dplayer'),
      autoplay: false,
      theme: '#b7daff',
      lang: 'zh-cn',
      hotkey: true,
      preload: 'auto',
      volume: 0.2,
      screenshot: true,
      mutex: true,
      video: {
        quality: [
          {
            name: '流畅',
            url: this.ldUrl
          },
          {
            name: '标清',
            url: this.fdUrl
          }
        ],
        defaultQuality: 0
      },
      danmaku: {
        id: this.baseUrl,
        api: 'http://192.168.224.129:1207/' // 这里填写弹幕地址
      }
    })
  }
}
</script>

<style >
  .dplayer{
    width: 80%;
    height: 50%;
    margin-left: 10%;
  }
</style>

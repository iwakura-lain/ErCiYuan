<template>
  <div style="background-color:black;">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/dplayer/1.26.0/DPlayer.min.js"/>
    <div id="dplayer"/>
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
    return {
      vid: vid,
      baseUrl: baseUrl,
      ldUrl: ldUrl
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
      volume: 0.05,
      screenshot: true,
      mutex: true,
      video: {
        quality: [
          {
            name: '超清',
            url: this.ldUrl
          }
        ],
        defaultQuality: 0
      },
      danmaku: {
        id: this.ldUrl,
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

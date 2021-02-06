import request from '~/utils/request'

export default{
  getVideoUrls(id) {
    return request({
      url: `/api/vod/media/get-play-url/${id}`,
      method: 'get'
    })
  }
}

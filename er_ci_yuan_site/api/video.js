import request from '~/utils/request'

export default{
  getVideoUrls(id) {
    return request({
      baseURL: 'http://localhost:8170',
      url: `/api/vod/media/get-play-url/${id}`,
      method: 'get'
    })
  }
}

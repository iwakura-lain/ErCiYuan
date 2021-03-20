import request from '~/utils/request'

export default{
  get(animeId) {
    return request({
      url: `/api/ucenter/comment/get-comment-list/${animeId}`,
      method: 'get'
    })
  },
  post(comment) {
    return request({
      url: `/api/ucenter/comment/auth/add-comment`,
      method: 'post',
      data: comment
    })
  }
}

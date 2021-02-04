import request from '@/utils/request'

export default {

  isCollect(animeId) {
    return request({
      url: `/api/site/anime-collect/auth/is-collect/${animeId}`,
      method: 'get'
    })
  },

  addCollect(animeId) {
    return request({
      url: `/api/site/anime-collect/auth/save/${animeId}`,
      method: 'post'
    })
  },

  getCollectList() {
    return request({
      url: '/api/site/anime-collect/auth/list',
      method: 'get'
    })
  },

  removeById(animeId) {
    return request({
      url: `/api/site/anime-collect/auth/remove/${animeId}`,
      method: 'delete'
    })
  }
}

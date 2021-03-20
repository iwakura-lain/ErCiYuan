import request from '~/utils/request'

export default{
  list(searchObj) {
    return request({
      url: '/api/admin/anime/get-anime-by-query',
      method: 'get',
      params: searchObj
    })
  },

  nestedList() {
    return request({
      url: '/api/admin/subject/nested-list',
      method: 'get'
    })
  },

  pageList(searchObj) {
    return request({
      url: `/api/admin/anime/list`,
      method: 'get',
      params: searchObj
    })
  },

  animeInfo(id) {
    return request({
      url: `/api/admin/anime/anime-info/${id}`,
      method: 'get'
    })
  },

  searchAutoComplete(title) {
    return request({
      url: `/api/admin/anime/auto-list/${title}`,
      method: 'get'
    })
  },

  getAnimeInfoByVideoSourceId(id) {
    return request({
      url: `/api/admin/anime/get-anime-by-video/${id}`,
      method: 'get'
    })
  }
}

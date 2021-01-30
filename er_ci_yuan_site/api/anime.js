import request from '~/utils/request'

export default{
  list(searchObj) {
    return request({
      url: '/api/site/anime/get-anime-by-query',
      method: 'get',
      params: searchObj
    })
  },

  nestedList() {
    return request({
      url: '/api/site/subject/nested-list',
      method: 'get'
    })
  },

  pageList(searchObj) {
    return request({
      url: `/api/site/anime/list`,
      method: 'get',
      params: searchObj
    })
  },

  animeInfo(id) {
    return request({
      url: `/api/site/anime/anime-info/${id}`,
      method: 'get'
    })
  },

  searchAutoComplete(title) {
    return request({
      url: `/api/site/anime/auto-list/${title}`,
      method: 'get'
    })
  }
}

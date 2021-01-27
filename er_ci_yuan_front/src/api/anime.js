import request from '@/utils/request'

export default {
  addAnimeInfo(animeInfo) {
    return request({
      url: `/admin/manager/anime/add-anime-info`,
      method: 'post',
      data: animeInfo
    })
  },

  getAnimeInfoById(id) {
    return request({
      url: `/admin/manager/anime/get-by-id/${id}`,
      method: 'get'
    })
  },

  updateAnimeInfoById(animeInfo) {
    return request({
      url: `/admin/manager/anime/update-anime-info`,
      method: 'put',
      data: animeInfo
    })
  },

  pageList(page, limit, searchFrom) {
    return request({
      url: `/admin/manager/anime/list/${page}/${limit}`,
      method: 'get',
      params: searchFrom
    })
  },

  removeById(id) {
    return request({
      url: `/admin/manager/anime/delete/${id}`,
      method: 'delete'
    })
  },

  getPublishAnime(id) {
    return request({
      url: `/admin/manager/anime/get-publish-anime/${id}`,
      method: 'get'
    })
  },

  updateAnimeStatus(id) {
    return request({
      url: `/admin/manager/anime/publish-anime/${id}`,
      method: 'put'
    })
  },

  getRecordsNameByKey(key) {
    return request({
      url: `/admin/manager/anime/list/name/${key}`,
      method: 'get'
    })
  }
}

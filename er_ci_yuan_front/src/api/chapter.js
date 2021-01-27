import request from '@/utils/request'

export default {

  getNestedTreeList(animeId) {
    return request({
      url: `/admin/manager/chapter/nested-list/${animeId}`,
      method: 'get'
    })
  },

  deleteById(id) {
    return request({
      url: `/admin/manager/chapter/delete/${id}`,
      method: 'delete'
    })
  },

  addChapter(chapter) {
    return request({
      url: '/admin/manager/chapter/add',
      method: 'post',
      data: chapter
    })
  },

  getById(id) {
    return request({
      url: `/admin/manager/chapter/get-by-id/${id}`,
      method: 'get'
    })
  },

  updateById(chapter) {
    return request({
      url: '/admin/manager/chapter/update',
      method: 'put',
      data: chapter
    })
  }
}

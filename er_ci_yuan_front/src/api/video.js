import request from '@/utils/request'

export default {

  deleteById(id) {
    return request({
      url: `/admin/manager/video/delete/${id}`,
      method: 'delete'
    })
  },

  addVideo(video) {
    return request({
      url: '/admin/manager/video/add',
      method: 'post',
      data: video
    })
  },

  getById(id) {
    return request({
      url: `/admin/manager/video/get-by-id/${id}`,
      method: 'get'
    })
  },

  updateById(video) {
    return request({
      url: '/admin/manager/video/update',
      method: 'put',
      data: video
    })
  }
}

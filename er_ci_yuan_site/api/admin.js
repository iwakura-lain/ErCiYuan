import request from '~/utils/request'

export default{
  list() {
    return request({
      url: '/api/admin/admin/list',
      method: 'get'
    })
  },

  getMakerInfoById(id) {
    return request({
      url: `/api/admin/admin/details/${id}`,
      method: 'get'
    })
  }
}

import request from '~/utils/request'

export default{
  list() {
    return request({
      url: '/api/site/admin/list',
      method: 'get'
    })
  },

  getMakerInfoById(id) {
    return request({
      url: `/api/site/admin/details/${id}`,
      method: 'get'
    })
  }
}

import request from '~/utils/request'

export default{
  getSlidWindow() {
    return request({
      url: `/api/site/ad/list/1`,
      method: 'get'
    })
  },
  getList() {
    return request({
      url: `/api/admin/index/get-top-list`,
      method: 'get'
    })
  }

}

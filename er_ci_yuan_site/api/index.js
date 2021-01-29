import request from '~/utils/request'

export default{
  getSlidWindow() {
    return request({
      baseURL: 'http://localhost:8160',
      url: `/api/site/ad/list/1`,
      method: 'get'
    })
  },
  getList() {
    return request({
      url: `/api/site/index/get-top-list`,
      method: 'get'
    })
  }

}

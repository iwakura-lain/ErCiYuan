import request from '~/utils/request'

export default{
  createOrder(animeId) {
    return request({
      baseURL: 'http://localhost:8130',
      url: `/api/trade/order/auth/add/${animeId}`,
      method: 'post'
    })
  },

  getById(animeId) {
    return request({
      baseURL: 'http://localhost:8130',
      url: `/api/trade/order/auth/get/${animeId}`,
      method: 'get'
    })
  },

  isBuy(animeId) {
    return request({
      baseURL: 'http://localhost:8130',
      url: `/api/trade/order/auth/is-buy/${animeId}`,
      method: 'get'
    })
  },

  getList() {
    return request({
      baseURL: 'http://localhost:8130',
      url: `/api/trade/order/auth/list`,
      method: 'get'
    })
  },

  removeById(orderId) {
    return request({
      baseURL: 'http://localhost:8130',
      url: `/api/trade/order/auth/delete/${orderId}`,
      method: 'delete'
    })
  }
}

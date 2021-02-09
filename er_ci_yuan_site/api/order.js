import request from '~/utils/request'

export default{
  createOrder(animeId) {
    return request({
      url: `/api/trade/order/auth/add/${animeId}`,
      method: 'post'
    })
  },

  getById(animeId) {
    return request({
      url: `/api/trade/order/auth/get/${animeId}`,
      method: 'get'
    })
  },

  isBuy(animeId) {
    return request({
      url: `/api/trade/order/auth/is-buy/${animeId}`,
      method: 'get'
    })
  },

  getList() {
    return request({
      url: `/api/trade/order/auth/list`,
      method: 'get'
    })
  },

  removeById(orderId) {
    return request({
      url: `/api/trade/order/auth/delete/${orderId}`,
      method: 'delete'
    })
  },

  queryOrderStatus(orderNo) {
    return request({
      url: `/api/trade/order/query-pay-status/${orderNo}`,
      method: 'get'
    })
  }
}

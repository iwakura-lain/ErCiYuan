import request from '~/utils/request'

export default {
  createNative(orderNo) {
    return request({
      url: `/api/trade/wx-pay/create-native/${orderNo}`,
      method: 'get'
    })
  }
}

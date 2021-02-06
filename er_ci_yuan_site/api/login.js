import request from '~/utils/request'

export default{
  login(user) {
    return request({
      url: `/api/site/member/login`,
      method: 'post',
      data: user
    })
  },

  getLoginInfo() {
    return request({
      url: `/api/site/member/get-login-info`,
      method: 'get'
    })
  }
}

import request from '~/utils/request'

export default{
  updateMember(user) {
    return request({
      baseURL: 'http://localhost:8140',
      url: `/api/site/member/auth/update-member`,
      method: 'post',
      data: user
    })
  }
}

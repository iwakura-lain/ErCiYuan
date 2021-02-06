import request from '~/utils/request'

export default{
  updateMember(user) {
    return request({
      url: `/api/site/member/auth/update-member`,
      method: 'post',
      data: user
    })
  }
}

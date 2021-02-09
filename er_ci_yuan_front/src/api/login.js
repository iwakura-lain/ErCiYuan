import request from '@/utils/request'

// 登录
export function login(username, password) {
  return request({
    url: '/admin/acl/login',
    method: 'post',
    data: {
      username,
      password
    }
  })
}

// 获取用户信息
export function getInfo(token) {
  return request({
    url: '/admin/acl/index/info',
    method: 'get',
    params: { token }
  })
}

// 登出
export function logout() {
  return request({
    url: '/admin/acl/index/logout',
    method: 'post'
  })
}

// 获取菜单权限数据
export function getMenu() {
  return request({
    url: '/admin/acl/index/menu',
    method: 'get'
  })
}

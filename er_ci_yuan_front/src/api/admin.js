import request from '@/utils/request'

export default{
  list() {
    return request({
      url: '/admin/manager/admin/getall',
      method: 'get'
    })
  },

  pageList(page, limit, searchFrom) {
    return request({
      url: `/admin/manager/admin/list/${page}/${limit}`,
      method: 'get',
      // 表单数据(不是@RequestBody接收的) / url 参数使用 params
      params: searchFrom
    })
  },

  removeById(id) {
    return request({
      url: `/admin/manager/admin/delete/${id}`,
      method: 'delete'
    })
  },

  save(admin) {
    return request({
      url: '/admin/manager/admin/add',
      method: 'post',
      // json 数据使用 data 传递
      data: admin
    })
  },

  getById(id) {
    return request({
      url: `/admin/manager/admin/get/${id}`,
      method: 'get'
    })
  },

  updateById(admin) {
    return request({
      url: `/admin/manager/admin/update`,
      method: 'put',
      data: admin
    })
  }
}

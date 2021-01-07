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
      // 这里也可以写成 post (但是后端也必须是 post 接收)
      // 两种情况下后端都会自动将表单封装，
      // 并用和这个表单中参数对应的对象接收 (如果有的话)
      // 从 restful 风格来说，更推荐使用 get
      // 因此这里的请求本质还是对数据进行查找，而不是对数据进行增删改
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
      // data 表示会将表单 / 数据以 json 格式传递
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
  },

  batchRemoveById(ids) {
    return request({
      url: `/admin/manager/admin/batch-remove`,
      method: 'delete',
      data: ids
    })
  },

  getRecordsNameByKey(key) {
    return request({
      url: `/admin/manager/admin/list/name/${key}`,
      method: 'get'
    })
  }
}

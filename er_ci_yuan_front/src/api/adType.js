// @ 符号在build/webpack.base.conf.js 中配置 表示 'src' 路径
import request from '@/utils/request'

export default {

  list() {
    return request({
      baseURL: 'http://127.0.0.1:8160',
      url: '/admin/cms/ad-type/list',
      method: 'get'
    })
  },

  pageList(page, limit) {
    return request({
      baseURL: 'http://127.0.0.1:8160',
      url: `/admin/cms/ad-type/list/${page}/${limit}`,
      method: 'get'
    })
  },

  removeById(id) {
    return request({
      baseURL: 'http://127.0.0.1:8160',
      url: `/admin/cms/ad-type/remove/${id}`,
      method: 'delete'
    })
  },

  save(adType) {
    return request({
      baseURL: 'http://127.0.0.1:8160',
      url: '/admin/cms/ad-type/save',
      method: 'post',
      data: adType
    })
  },

  getById(id) {
    return request({
      baseURL: 'http://127.0.0.1:8160',
      url: `/admin/cms/ad-type/get/${id}`,
      method: 'get'
    })
  },

  updateById(adType) {
    return request({
      baseURL: 'http://127.0.0.1:8160',
      url: '/admin/cms/ad-type/update',
      method: 'put',
      data: adType
    })
  }
}

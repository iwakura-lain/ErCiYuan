import request from '@/utils/request'

export default {

  deleteById(url) {
    return request({
      baseURL: 'http://localhost:8180',
      url: `/admin/oss/file/delete`,
      method: 'delete',
      params: {
        url: url
      }
    })
  }
}

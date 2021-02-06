import request from '@/utils/request'

export default {

  deleteById(url) {
    return request({
      url: `/admin/oss/file/delete`,
      method: 'delete',
      params: {
        url: url
      }
    })
  }
}

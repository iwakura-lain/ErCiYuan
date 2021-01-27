import request from '@/utils/request'

export default {

  deleteById(id) {
    return request({
      baseURL: 'http://localhost:8170',
      url: `/admin/vod/file/delete/${id}`,
      method: 'delete'
    })
  }
}

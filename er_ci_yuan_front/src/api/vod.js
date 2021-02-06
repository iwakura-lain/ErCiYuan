import request from '@/utils/request'

export default {

  deleteById(id) {
    return request({
      url: `/admin/vod/file/delete/${id}`,
      method: 'delete'
    })
  }
}

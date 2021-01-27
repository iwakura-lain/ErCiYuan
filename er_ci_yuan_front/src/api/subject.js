import request from '@/utils/request'

export default {
  getTreeList() {
    return request({
      url: `/admin/manager/subject/nested-list`,
      method: 'get'
    })
  },

  removeSubjectById(id) {
    return request({
      url: `/admin/manager/subject/delete/${id}`,
      method: 'delete'
    })
  },

  addSubject(subject) {
    return request({
      url: `/admin/manager/subject/add`,
      method: 'post',
      data: subject
    })
  },

  updateSubject(subject) {
    return request({
      url: `/admin/manager/subject/update`,
      method: 'put',
      data: subject
    })
  }
}

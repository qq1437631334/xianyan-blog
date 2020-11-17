import request from '@/utils/request'
var group_name = 'comment'
export default {
  save(comment) { // 保存
    return request({
      url: `/${group_name}/save`,
      method: 'post',
      data: comment
    })
  },
  getByBlogId(id) {
    return request({
      url: `/${group_name}/getByBlogId/${id}`,
      method: 'get'
    })
  },
  commentGood(blogId, commentId) {
    return request({
      url: `/${group_name}/commentGood/${blogId}&${commentId}`,
      method: 'post'
    })
  },
  getPage(page) {
    return request({
      url: `/${group_name}/getPage`,
      method: 'post',
      data: page
    })
  },
  deleteById(id) {
    return request({
      url: `/${group_name}/delete/${id}`,
      method: 'delete'
    })
  }
}

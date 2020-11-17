import request from '@/utils/request'
var group_name = 'user'
export default {
  register(user) { // 保存
    return request({
      url: `/${group_name}/register`,
      method: 'post',
      data: user
    })
  },
  getCollectionPage(page) {
    return request({
      url: `/${group_name}/getCollectionPage`,
      method: 'post',
      data: page
    })
  },
  login(user) { // 登录
    return request({
      url: `/${group_name}/login`,
      method: 'post',
      data: user
    })
  },
  update(user) { // 更新
    return request({
      url: `/${group_name}/update`,
      method: 'put',
      data: user
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
      url: `/${group_name}/deleteCollection/${id}`,
      method: 'delete'
    })
  },
  getById(id) { // 根据id查询
    return request({
      url: `/${group_name}/getById/${id}`,
      method: 'get'
    })
  },
  getCommentAndCollectionCount() {
    return request({
      url: `/${group_name}/getCommentAndCollectionCount`,
      method: 'get'
    })
  },
  updatePassword(id, password) { // 根据id修改密码
    return request({
      url: `/${group_name}/updatePassword/${id}&${password}`,
      method: 'put'
    })
  }
}

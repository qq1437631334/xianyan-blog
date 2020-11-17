import request from '@/utils/request'
var group_name = 'user'
export default {
  save(user) { // 保存
    return request({
      url: `/${group_name}/save`,
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
  delete(id) { // 根据id删除
    return request({
      url: `/${group_name}/delete/${id}`,
      method: 'delete'
    })
  },
  getById(id) { // 根据id查询
    return request({
      url: `/${group_name}/getById/${id}`,
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

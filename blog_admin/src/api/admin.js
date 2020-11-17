import request from '@/utils/request'
var group_name = 'admin'
export default {
  getAdmin() { // 获得管理员
    return request({
      url: `/${group_name}/getAdmin`,
      method: 'get'
    })
  },
  update(admin) { // 更新
    return request({
      url: `/${group_name}/updateInfo`,
      method: 'put',
      data: admin
    })
  },
  updatePassword(password) { // 更改管理员密码
    return request({
      url: `/${group_name}/updatePassword`,
      method: 'put',
      data: password
    })
  }
}

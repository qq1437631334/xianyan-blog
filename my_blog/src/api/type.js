import request from '@/utils/request'
var group_name = 'type'
export default {
  getTypeList() { // 前台获取所有类型
    return request({
      url: `/${group_name}/getAll`,
      method: 'get'
    })
  },
  getCount() {
    return request({
      url: `/${group_name}/getCount`,
      method: 'get'
    })
  },
  getById(id) {
    return request({
      url: `/${group_name}/getById/${id}`,
      method: 'get'
    })
  }
}

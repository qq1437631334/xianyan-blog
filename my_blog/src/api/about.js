import request from '@/utils/request'
var group_name = 'about'
export default {
  read() { // 保存
    return request({
      url: `/${group_name}/read`,
      method: 'put'
    })
  }
}

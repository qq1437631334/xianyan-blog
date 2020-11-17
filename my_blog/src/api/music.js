import request from '@/utils/request'
var group_name = 'music'
export default {
  getList() {
    return request({
      url: `/${group_name}/getList`,
      method: 'get'
    })
  }

}

import request from '@/utils/request'
var group_name = 'log'
export default {
  getPage(page) {
    return request({
      url: `/${group_name}/getPage`,
      method: 'post',
      data: page
    })
  },
  deleteByIds(ids) {
    return request({
      url: `/${group_name}/deleteByIds`,
      method: 'delete',
      data: ids
    })
  },
  exportExcel() {
    return request({
      url: `/${group_name}/export`,
      method: 'get',
      responseType: 'blob'
    })
  }
}

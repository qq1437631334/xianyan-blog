import request from '@/utils/request'
var group_name = 'comment'
export default {
  getPageBack(page) { // 保存
    return request({
      url: `/${group_name}/getPageBack`,
      method: 'post',
      data: page
    })
  },
  delete(id) { // 保存
    return request({
      url: `/${group_name}/delete/${id}`,
      method: 'delete'
    })
  }
}

import request from '@/utils/request'
var group_name = 'about'
export default {
  save(about) { // 保存
    return request({
      url: `/${group_name}/save`,
      method: 'post',
      data: about
    })
  },
  update(about) { // 更新
    return request({
      url: `/${group_name}/update`,
      method: 'put',
      data: about
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
  read(id) { // 阅读
    return request({
      url: `/${group_name}/read/${id}`,
      method: 'put'
    })
  },
  enable(id) { // 启用
    return request({
      url: `/${group_name}/enable/${id}`,
      method: 'put'
    })
  },
  disable(id) { // 弃用
    return request({
      url: `/${group_name}/disable/${id}`,
      method: 'put'
    })
  }
}

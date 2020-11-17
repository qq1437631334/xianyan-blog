import request from '@/utils/request'
var group_name = 'music'
export default {
  save(music) { // 保存
    return request({
      url: `/${group_name}/save`,
      method: 'post',
      data: music
    })
  },
  update(music) { // 更新
    return request({
      url: `/${group_name}/update`,
      method: 'put',
      data: music
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

import request from '@/utils/request'
var group_name = 'link'
export default {
  save(link) { // 保存
    return request({
      url: `/${group_name}/save`,
      method: 'post',
      data: link
    })
  },
  update(link) { // 更新
    return request({
      url: `/${group_name}/update`,
      method: 'put',
      data: link
    })
  },
  getLinkList() { // 获取所有友情链接
    return request({
      url: `/${group_name}/getAll`,
      method: 'get'
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
  getByName(name) { // 根据链接名字查询
    return request({
      url: `/${group_name}/getByName/${name}`,
      method: 'get'
    })
  }
}

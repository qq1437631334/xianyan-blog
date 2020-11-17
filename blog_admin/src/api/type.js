import request from '@/utils/request'
var group_name = 'type'
export default {
  save(type) { // 保存
    return request({
      url: `/${group_name}/save`,
      method: 'post',
      data: type
    })
  },
  update(type) { // 更新
    return request({
      url: `/${group_name}/update`,
      method: 'put',
      data: type
    })
  },
  getList() { // 前台获取所有类型
    return request({
      url: `/${group_name}/getAll`,
      method: 'get'
    })
  },
  getListBack() { // 后台获取所有类型
    return request({
      url: `/${group_name}/getAllBack`,
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
  getByName(name) { // 根据类型名字查询
    return request({
      url: `/${group_name}/getByName/${name}`,
      method: 'get'
    })
  }
}

import request from '@/utils/request'
var group_name = 'blog'
export default {
  save(blog) { // 保存
    return request({
      url: `/${group_name}/save`,
      method: 'post',
      data: blog
    })
  },
  update(blog) { // 更新
    return request({
      url: `/${group_name}/update`,
      method: 'put',
      data: blog
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
  }
}

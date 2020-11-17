import request from '@/utils/request'
var group_name = 'blog'
export default {
  getPage(page) {
    return request({
      url: `/${group_name}/getPage`,
      method: 'post',
      data: page
    })
  },
  getById(id) {
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
  getRecommendedReadList() { // 获得推荐阅读
    return request({
      url: `/${group_name}/recommendedRead`,
      method: 'get'
    })
  },
  getTimeLine() { // 获得推荐阅读
    return request({
      url: `/${group_name}/getTimeLine`,
      method: 'get'
    })
  },
  good(good) { // 点赞
    return request({
      url: `/${group_name}/good`,
      method: 'post',
      data: good
    })
  },
  getIsGood(id) { // 查询是否已经点赞
    return request({
      url: `/${group_name}/getIsGood/${id}`,
      method: 'get'
    })
  },
  collection(collection) { // 收藏
    return request({
      url: `/${group_name}/collection`,
      method: 'post',
      data: collection
    })
  },
  getIsCollection(id) { // 查询是否已经收藏
    return request({
      url: `/${group_name}/getIsCollection/${id}`,
      method: 'get'
    })
  }
}

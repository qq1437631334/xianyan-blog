import request from '@/utils/request'

export function uploadImage(data) {
  return request({
    url: `/upload/uploadImage`,
    method: 'post',
    data: data
  })
}

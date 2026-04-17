import request from '@/utils/request'

export function pageClass(page: number, size: number) {
  return request.get('/page-class', { params: { page, size } })
}

export function searchClass(params: { id?: string; name?: string }) {
  return request.get('/class', { params })
}

export function createClass(data: FormData) {
  return request.post('/class', data)
}

export function updateClass(data: FormData) {
  return request.put('/class', data)
}

export function deleteClass(id: number) {
  return request.delete('/class', { params: { id } })
}

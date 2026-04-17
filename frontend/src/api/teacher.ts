import request from '@/utils/request'

export function pageTeacher(page: number, size: number) {
  return request.get('/page-teacher', { params: { page, size } })
}

export function searchTeacher(params: { id?: number; name?: string }) {
  return request.get('/teacher', { params })
}

export function createTeacher(data: FormData) {
  return request.post('/teacher', data)
}

export function updateTeacher(data: FormData) {
  return request.put('/teacher', data)
}

export function deleteTeacher(id: number) {
  return request.delete('/teacher', { params: { id } })
}

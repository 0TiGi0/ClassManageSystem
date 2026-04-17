import request from '@/utils/request'

export function pageStudent(page: number, size: number) {
  return request.get('/page-student', { params: { page, size } })
}

export function searchStudent(params: { id?: number; name?: string }) {
  return request.get('/student/name', { params })
}

export function createStudent(data: FormData) {
  return request.post('/student', data)
}

export function updateStudent(data: FormData) {
  return request.put('/student', data)
}

export function deleteStudent(id: number) {
  return request.delete('/student', { params: { id } })
}

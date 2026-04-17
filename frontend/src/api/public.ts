import request from '@/utils/request'

export function getTeacherClasses() {
  return request.get('/hava_teacher_class')
}

export function getNotTeacherClasses() {
  return request.get('/hava_not_teacher_class')
}

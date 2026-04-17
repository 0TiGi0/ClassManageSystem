import { createRouter, createWebHistory } from 'vue-router'
import AppLayout from '@/components/AppLayout.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: AppLayout,
      redirect: '/class',
      children: [
        {
          path: 'class',
          name: 'ClassManage',
          component: () => import('@/components/ClassManage.vue'),
        },
        {
          path: 'student',
          name: 'StudentManage',
          component: () => import('@/components/StudentManage.vue'),
        },
        {
          path: 'teacher',
          name: 'TeacherManage',
          component: () => import('@/components/TeacherManage.vue'),
        },
      ],
    },
  ],
})

export default router

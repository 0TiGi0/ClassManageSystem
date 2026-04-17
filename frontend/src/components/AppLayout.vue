<script setup lang="ts">
import { useRoute } from 'vue-router'

const route = useRoute()

const menuItems = [
  { path: '/class', label: '班级管理' },
  { path: '/student', label: '学生管理' },
  { path: '/teacher', label: '教师管理' },
]
</script>

<template>
  <div class="app-layout">
    <header class="app-header">
      <div class="header-inner">
        <h1 class="logo">班级管理系统</h1>
        <nav class="nav-menu">
          <router-link
            v-for="item in menuItems"
            :key="item.path"
            :to="item.path"
            class="nav-item"
            :class="{ active: route.path === item.path }"
          >
            {{ item.label }}
          </router-link>
        </nav>
      </div>
    </header>
    <main class="app-body">
      <router-view v-slot="{ Component }">
        <transition name="fade" mode="out-in">
          <component :is="Component" />
        </transition>
      </router-view>
    </main>
  </div>
</template>

<style scoped>
.app-layout {
  min-height: 100vh;
  background: #f0f2f5;
}

.app-header {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 100;
  height: 60px;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.08);
}

.header-inner {
  max-width: 1200px;
  margin: 0 auto;
  height: 100%;
  display: flex;
  align-items: center;
  padding: 0 24px;
}

.logo {
  font-size: 20px;
  font-weight: 600;
  color: #409eff;
  margin: 0;
  margin-right: 48px;
  white-space: nowrap;
}

.nav-menu {
  display: flex;
  gap: 8px;
}

.nav-item {
  padding: 8px 20px;
  border-radius: 20px;
  font-size: 14px;
  color: #606266;
  text-decoration: none;
  transition: all 0.3s ease;
}

.nav-item:hover {
  color: #409eff;
  background: #ecf5ff;
}

.nav-item.active {
  color: #fff;
  background: #409eff;
}

.app-body {
  max-width: 1200px;
  margin: 0 auto;
  padding: 84px 24px 24px;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.25s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>

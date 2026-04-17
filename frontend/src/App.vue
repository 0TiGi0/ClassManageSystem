<script setup lang="ts"></script>

<template>
  <router-view />
</template>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial,
    sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.el-button {
  border-radius: 8px !important;
  transition: all 0.3s ease !important;
}

.el-input__wrapper,
.el-select__wrapper {
  border-radius: 8px !important;
  transition: all 0.3s ease !important;
}

.el-table {
  transition: all 0.3s ease !important;
}

.el-drawer {
  border-radius: 16px 0 0 16px !important;
}

.el-pagination .el-pager li {
  border-radius: 6px !important;
  transition: all 0.3s ease !important;
}

.el-message-box {
  border-radius: 12px !important;
}
</style>

<script setup lang="ts">
import { ref, reactive, computed, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import {
  pageTeacher,
  searchTeacher,
  createTeacher,
  updateTeacher,
  deleteTeacher,
} from '@/api/teacher'
import { getNotTeacherClasses } from '@/api/public'
import type { FormInstance, FormRules } from 'element-plus'

interface TeacherRow {
  id: number
  name: string
  age: number
  gender: boolean
  className: string
  studentNum: number
  createTime: string
  updateTime: string
}

const tableData = ref<TeacherRow[]>([])
const loading = ref(false)
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)

const searchField = ref('name')
const searchValue = ref('')
const isSearchMode = ref(false)
const searchResults = ref<TeacherRow[]>([])

const drawerVisible = ref(false)
const drawerTitle = ref('新增教师')
const isEdit = ref(false)
const formRef = ref<FormInstance>()

const classOptions = ref<{ id: number; name: string }[]>([])

const form = reactive({
  id: 0,
  name: '',
  age: undefined as number | undefined,
  gender: undefined as boolean | undefined,
  class_id: undefined as number | undefined,
})

const rules = reactive<FormRules>({
  name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
  age: [{ required: true, message: '请输入年龄', trigger: 'blur' }],
  gender: [{ required: true, message: '请选择性别', trigger: 'change' }],
})

async function loadData() {
  loading.value = true
  try {
    const res: any = await pageTeacher(currentPage.value, pageSize.value)
    tableData.value = res.data || []
    if (tableData.value.length < pageSize.value) {
      total.value = (currentPage.value - 1) * pageSize.value + tableData.value.length
    } else {
      total.value = currentPage.value * pageSize.value + 1
    }
  } finally {
    loading.value = false
  }
}

async function handleSearch() {
  if (!searchValue.value.trim()) {
    isSearchMode.value = false
    currentPage.value = 1
    loadData()
    return
  }
  loading.value = true
  try {
    const params: Record<string, any> = {}
    params[searchField.value] =
      searchField.value === 'id' ? Number(searchValue.value) : searchValue.value
    const res: any = await searchTeacher(params)
    searchResults.value = res.data || []
    isSearchMode.value = true
    total.value = searchResults.value.length
    currentPage.value = 1
  } finally {
    loading.value = false
  }
}

function handleReset() {
  searchField.value = 'name'
  searchValue.value = ''
  isSearchMode.value = false
  currentPage.value = 1
  loadData()
}

const currentDisplayData = computed(() => {
  if (isSearchMode.value) {
    const start = (currentPage.value - 1) * pageSize.value
    return searchResults.value.slice(start, start + pageSize.value)
  }
  return tableData.value
})

function handlePageChange(page: number) {
  currentPage.value = page
  if (!isSearchMode.value) {
    loadData()
  }
}

function handleSizeChange(size: number) {
  pageSize.value = size
  currentPage.value = 1
  if (!isSearchMode.value) {
    loadData()
  }
}

async function loadClassOptions() {
  try {
    const res: any = await getNotTeacherClasses()
    classOptions.value = res.data || []
  } catch {}
}

function handleAdd() {
  drawerTitle.value = '新增教师'
  isEdit.value = false
  form.id = 0
  form.name = ''
  form.age = undefined
  form.gender = undefined
  form.class_id = undefined
  loadClassOptions()
  drawerVisible.value = true
}

function handleEdit(row: TeacherRow) {
  drawerTitle.value = '编辑教师'
  isEdit.value = true
  form.id = row.id
  form.name = row.name
  form.age = row.age
  form.gender = row.gender
  form.class_id = undefined
  loadClassOptions()
  drawerVisible.value = true
}

async function handleSubmit() {
  await formRef.value?.validate()
  const fd = new FormData()
  if (isEdit.value) {
    fd.append('id', String(form.id))
    fd.append('name', form.name)
    fd.append('age', String(form.age))
    fd.append('gender', String(form.gender))
    if (form.class_id !== undefined) {
      fd.append('classId', String(form.class_id))
    }
    await updateTeacher(fd)
  } else {
    const now = new Date().toISOString().replace('T', ' ').slice(0, 19)
    fd.append('name', form.name)
    fd.append('age', String(form.age))
    fd.append('gender', String(form.gender))
    fd.append('createTime', now)
    fd.append('updateTime', now)
    if (form.class_id !== undefined) {
      fd.append('classId', String(form.class_id))
    }
    await createTeacher(fd)
  }
  ElMessage.success(isEdit.value ? '修改成功' : '新增成功')
  drawerVisible.value = false
  if (isSearchMode.value) {
    handleSearch()
  } else {
    loadData()
  }
}

async function handleDelete(row: TeacherRow) {
  await ElMessageBox.confirm(`确认删除教师「${row.name}」？`, '提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning',
  })
  await deleteTeacher(row.id)
  ElMessage.success('删除成功')
  if (isSearchMode.value) {
    handleSearch()
  } else {
    loadData()
  }
}

function formatGender(val: boolean) {
  return val ? '男' : '女'
}

onMounted(() => {
  loadData()
})
</script>

<template>
  <div class="manage-page">
    <div class="search-bar">
      <el-select v-model="searchField" style="width: 120px">
        <el-option label="ID" value="id" />
        <el-option label="姓名" value="name" />
      </el-select>
      <el-input
        v-model="searchValue"
        placeholder="请输入搜索内容"
        clearable
        style="width: 220px"
        @keyup.enter="handleSearch"
      />
      <el-button type="primary" @click="handleSearch">查询</el-button>
      <el-button @click="handleReset">重置</el-button>
    </div>

    <div class="table-wrapper">
      <el-table :data="currentDisplayData" v-loading="loading" stripe style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="name" label="姓名" />
        <el-table-column prop="age" label="年龄" width="80" />
        <el-table-column prop="gender" label="性别" width="80">
          <template #default="{ row }">{{ formatGender(row.gender) }}</template>
        </el-table-column>
        <el-table-column prop="className" label="班级名称" />
        <el-table-column prop="studentNum" label="班级学生人数" width="130" />
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column prop="updateTime" label="修改时间" width="180" />
        <el-table-column label="操作" width="160" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link size="small" @click="handleEdit(row)">修改</el-button>
            <el-button type="danger" link size="small" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="table-footer">
        <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :total="total"
          :page-sizes="[5, 10, 20]"
          layout="total, sizes, prev, pager, next"
          @current-change="handlePageChange"
          @size-change="handleSizeChange"
        />
        <el-button type="primary" @click="handleAdd">新增</el-button>
      </div>
    </div>

    <el-drawer
      v-model="drawerVisible"
      :title="drawerTitle"
      size="400px"
      :close-on-click-modal="false"
    >
      <el-form ref="formRef" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input-number v-model="form.age" :min="1" :max="150" style="width: 100%" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="form.gender" placeholder="请选择性别" style="width: 100%">
            <el-option label="男" :value="true" />
            <el-option label="女" :value="false" />
          </el-select>
        </el-form-item>
        <el-form-item label="班级">
          <el-select v-model="form.class_id" placeholder="请选择班级" clearable style="width: 100%">
            <el-option
              v-for="item in classOptions"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="drawerVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确认</el-button>
      </template>
    </el-drawer>
  </div>
</template>

<style scoped>
.manage-page {
  background: #fff;
  border-radius: 16px;
  padding: 24px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.04);
}

.search-bar {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 20px;
}

.table-wrapper {
  border-radius: 12px;
  overflow: hidden;
}

.table-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 16px;
}

:deep(.el-table) {
  border-radius: 12px;
}

:deep(.el-table th.el-table__cell) {
  background: #fafafa;
}

:deep(.el-drawer__body) {
  padding: 20px;
}
</style>

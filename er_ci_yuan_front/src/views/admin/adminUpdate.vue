<template>
  <div class="app-container">
    <adminForm/>
  </div>
</template>

<script>
import adminApi from '@/api/admin'
import adminForm from '@/views/admin/adminForm'

export default {
  components: { adminForm },
  data() {
    return {
      // admin 对象
      admin: {
        // 默认
        sort: 0,
        level: 1
      },
      // 默认按钮可用
      saveBtnDisabled: false
    }
  },

  created() {
    // 创建页面时，根据 url 是否有 id 路由过来来进行数据回显调用
    if (this.$route.params.id) {
      this.fetchDataById(this.$route.params.id)
    }
  },

  methods: {
    saveOrUpdate() {
      this.saveBtnDisabled = true
      if (!this.admin.id) {
        this.saveData()
      } else {
        this.updateData()
      }
    },

    // 数据保存
    saveData() {
      adminApi.save(this.admin).then(response => {
        // 弹出成功提示
        this.$message({
          message: '注册成功',
          type: 'success'
        })
      })
      this.$router.push({ path: '/admin/list' })
    },

    // 数据更新
    updateData() {
      adminApi.updateById(this.admin).then(response => {
        // 弹出成功提示
        this.$message({
          message: '更新成功',
          type: 'success'
        })
      })
      this.$router.push({ path: '/admin/list' })
    },

    // 根据id查询数据，用于跳转后回显数据
    fetchDataById(id) {
      adminApi.getById(id).then(response => {
        this.admin = response.data.item
      })
    },

    // 返回
    backListPage() {
      this.$router.push({ path: '/admin/list' })
      this.$message({
        message: '已取消',
        type: 'info'
      })
    }
  }
}
</script>

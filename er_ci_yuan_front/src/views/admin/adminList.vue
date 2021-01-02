<template>
  <div class="app-container">

    <!--查询表单-->
    <el-form :inline="true">
      <el-form-item>
        <el-input v-model="searchFrom.name" placeholder="名讳"/>
      </el-form-item>

      <el-form-item>
        <el-select v-model="searchFrom.level" clearable placeholder="头衔">
          <el-option value="1" label="见习魔女"/>
          <el-option value="2" label="灰之魔女"/>
        </el-select>
      </el-form-item>

      <el-form-item label="在册时间">
        <el-date-picker
          v-model="searchFrom.joinDateBegin"
          placeholder="开始时间"
          value-format="yyyy-MM-dd" />
      </el-form-item>
      <el-form-item label="-">
        <el-date-picker
          v-model="searchFrom.joinDateEnd"
          placeholder="结束时间"
          value-format="yyyy-MM-dd" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="fetchData()">查询</el-button>
        <el-button type="warning" icon="el-icon-close" @click="resetData()">清空</el-button>
      </el-form-item>
    </el-form>

    <!-- 表格 -->
    <el-table v-loading="loading" :data="adminList" align="center" border stripe>
      <el-table-column label="" width="50" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column align="center" prop="name" label="名讳" width="200" />
      <el-table-column align="center" prop="level" label="头衔" width="150">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.level === 1" type="success">见习魔女</el-tag>
          <el-tag v-if="scope.row.level === 2" type="info">灰之魔女</el-tag>
        </template>
      </el-table-column>

      <el-table-column width="100" label="阅历" type="expand">
        <template slot-scope="scope">
          <el-form inline class="table-expand">
            <el-form-item>
              <span>{{ scope.row.career }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>

      <el-table-column align="center" prop="sort" label="rating" width="100" />
      <el-table-column align="center" prop="joinDate" label="注册时间" width="120" />
      <el-table-column align="center" prop="intro" label="人生格言" width="530" />

      <el-table-column fixed="right" align="center" label="操作" width="200">
        <template slot-scope="scope">
          <router-link :to="'/admin/edit/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">更新</el-button>
          </router-link>
          <el-button
            size="mini"
            type="danger"
            icon="el-icon-delete"
            @click="removeById(scope.row.id)">注销</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <el-pagination
      :current-page="page"
      :total="total"
      :page-size="limit"
      :page-sizes="[5, 10, 15]"
      style="padding: 30px 0; text-align: center"
      layout="sizes, prev, pager, next, jumper, ->, total"
      @current-change="changeCurrentPage"
      @size-change="changePageSize"/>
  </div>
</template>

<script>
import adminApi from '@/api/admin'

export default {
  data() {
    return {
      // 管理员列表
      adminList: [],
      // 总记录数
      total: 0,
      // 页码
      page: 1,
      // 当前页记录数
      limit: 5,
      // 查询表单对象
      searchFrom: {},
      // 加载动画开关
      loading: true
    }
  },

  created() {
    this.fetchData()
  },

  mounted() {

  },

  methods: {
    // 调用 api，加载列表
    fetchData() {
      adminApi.pageList(this.page, this.limit, this.searchFrom).then((response) => {
        this.adminList = response.data.rows
        this.total = response.data.total
        this.loading = false
      })
    },
    // 改变页码
    changeCurrentPage(page) {
      this.page = page
      this.fetchData()
    },

    // 改变每页记录数
    changePageSize(size) {
      this.limit = size
      this.fetchData()
    },

    // 重置查询 表单，刷新数据
    resetData() {
      this.searchFrom = {}
      this.fetchData()
    },

    // 删除记录
    removeById(id) {
      // 询问是否删除
      this.$confirm('将注销该魔女, 是否确定?', 'Warning', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        adminApi.removeById(id).then(response => {
          // 刷新数据
          this.fetchData()
          // 弹出成功提示
          this.$notify({
            message: '<strong> <b style="color:green">注销成功<b><strong>',
            dangerouslyUseHTMLString: true,
            type: 'success'
          })
        })
      }).catch((err) => {
        if (err === 'cancel') {
          this.$notify({
            type: 'info',
            dangerouslyUseHTMLString: true,
            message: '<strong> <b style="color:#8A2BE2">取消注销<b> <strong>'
          })
        }
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
  .table-expand {
    font-size: 0;
  }

  .table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 70%;
    color: rgb(6, 155, 19);
  }
</style>

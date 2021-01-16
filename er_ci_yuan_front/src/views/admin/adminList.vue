<template>
  <div class="app-container">

    <!--查询表单-->
    <el-form :inline="true">
      <el-form-item>
        <el-autocomplete
          v-model="searchForm.name"
          :fetch-suggestions="querySearch"
          :trigger-on-focus="false"
          class="inline-input"
          placeholder="名讳"
        />
      </el-form-item>

      <el-form-item>
        <el-select v-model="searchForm.level" clearable placeholder="头衔">
          <el-option value="1" label="见习魔女"/>
          <el-option value="2" label="灰之魔女"/>
        </el-select>
      </el-form-item>

      <el-form-item label="在册时间">
        <el-date-picker
          v-model="searchForm.joinDateBegin"
          placeholder="开始时间"
          value-format="yyyy-MM-dd" />
      </el-form-item>
      <el-form-item label="-">
        <el-date-picker
          v-model="searchForm.joinDateEnd"
          placeholder="结束时间"
          value-format="yyyy-MM-dd" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="fetchDataByQuery()">查询</el-button>
        <el-button type="warning" icon="el-icon-close" @click="resetData()">清空</el-button>
      </el-form-item>
    </el-form>

    <div style="margin-bottom: 20px">
      <el-button type="danger" size="mini" @click="batchRemove()">
        批量删除
      </el-button>
    </div>

    <!-- 表格 -->
    <el-table v-loading="loading" :data="adminList" align="center" border stripe @selection-change="handleSelectionChange">
      <el-table-column
        type="selection"
        width="55"/>

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
      searchForm: {},
      // 加载动画开关
      loading: true,
      // 批量删除数组
      batchSelectAdmin: [],
      // 查询后的分页page
      listPage: 1,
      // 查询后的分页记录数
      listLimit: 5
    }
  },

  created() {
    this.fetchData()
  },

  mounted() {

  },

  methods: {
    // 无 query 条件下，以及可以达到分页条件下的查询的专用方法，
    fetchData() {
      adminApi.pageList(this.page, this.limit, this.searchForm).then((response) => {
        this.adminList = response.data.rows
        this.total = response.data.total
        this.loading = false
      })
    },
    // 查询专用分页方法，从第一页开始查，避免和 fetchData() 冲突导致除了第一页以外搜不出数据
    fetchDataByQuery() {
      adminApi.pageList(1, 5, this.searchForm).then((response) => {
        this.total = response.data.total
        // 如果总记录数达到了可以分页的要求，则计算
        // 记录数可以达到的分页数，以及显示哪个分页，否则不以当前分页页码显示
        if (response.data.total > this.limit) {
          this.page = Math.floor(this.total / this.limit) + 1
          this.fetchData()
          return
        }
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

    // 表格记录选择事件处理, 获取选中的对象
    handleSelectionChange(selection) {
      this.batchSelectAdmin = selection
    },

    // 重置查询表单，刷新数据
    resetData() {
      this.searchForm = {}
      this.page = 1
      this.limit = 5
      this.fetchData()
    },
    // 输入建议
    querySearch(queryString, callback) {
      adminApi.getRecordsNameByKey(queryString).then(response => {
        callback(response.data.records)
      })
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
    },
    // 批量删除
    batchRemove() {
      if (this.batchSelectAdmin.length === 0) {
        this.$message({
          message: '请选择要注销的魔女，不然就爪巴',
          type: 'error'
        })
        return
      }

      // 询问是否删除
      this.$confirm('将注销这些魔女, 是否确定?', 'Warning', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 获取选中的记录的 id
        const ids = []
        this.batchSelectAdmin.forEach(item => {
          ids.push(item.id)
        })
        adminApi.batchRemoveById(ids).then(response => {
          // 弹出成功提示
          this.$notify({
            message: '<strong> <b style="color:green">注销成功<b><strong>',
            dangerouslyUseHTMLString: true,
            type: 'success'
          })
          // 刷新数据
          this.fetchData()
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

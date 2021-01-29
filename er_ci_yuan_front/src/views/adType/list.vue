<template>
  <div class="app-container">

    <!-- 工具按钮 -->
    <div style="margin-bottom: 10px">
      <el-button type="primary" size="mini" icon="el-icon-edit" @click="openDialog()">添加推荐位</el-button>
    </div>

    <!-- 表格 -->
    <el-table :data="list" border stripe>

      <el-table-column prop="title" label="推荐位名称" />

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-edit" @click="openDialog(scope.row.id)">修改</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
      :visible.sync="dialogFormVisible"
      :title="saveStrOrUpdateStr"
      center
      width="300px"
    >
      <el-form :model="adType">
        <el-form-item :label-width="formLabelWidth" label="推荐位名称">
          <el-input v-model="adType.title"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="appendOrUpdate()">保存</el-button>
      </div>
    </el-dialog>

    <!-- 分页组件 -->
    <el-pagination
      :current-page="page"
      :total="total"
      :page-size="limit"
      :page-sizes="[5, 10, 20]"
      style="padding: 30px 0; text-align: center;"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="changePageSize"
      @current-change="changeCurrentPage"
    />
  </div>
</template>

<script>
import adTypeApi from '@/api/adType'

export default {
  // 定义数据模型
  data() {
    return {
      adType: {},
      list: [], // 列表
      total: 0, // 总记录数
      page: 1, // 页码
      limit: 10, // 每页记录数
      saveStrOrUpdateStr: '',
      dialogFormVisible: false,
      formLabelWidth: '100px'
    }
  },

  // 页面渲染成功后获取数据
  created() {
    this.fetchData()
  },

  // 定义方法
  methods: {
    fetchData() {
      // 调用api
      adTypeApi.pageList(this.page, this.limit).then(response => {
        this.list = response.data.rows
        this.total = response.data.total
      })
    },

    // 每页记录数改变，size：回调参数，表示当前选中的“每页条数”
    changePageSize(size) {
      this.limit = size
      this.fetchData()
    },

    // 弹窗表格
    openDialog(id) {
      this.dialogFormVisible = true
      if (id) {
        adTypeApi.getById(id).then(response => {
          this.adType = response.data.item
        })
        this.saveStrOrUpdateStr = '修改推荐位'
      } else {
        this.saveStrOrUpdateStr = '新增推荐位'
      }
    },

    // 改变页码，page：回调参数，表示当前选中的“页码”
    changeCurrentPage(page) {
      this.page = page
      this.fetchData()
    },

    // 根据id删除数据
    removeById(id) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return adTypeApi.removeById(id)
      }).then((response) => {
        this.fetchData()
        this.$message.success(response.message)
      }).catch(error => {
        // 当取消时会进入catch语句:error = 'cancel'
        // 当后端服务抛出异常时：error = 'error'
        if (error === 'cancel') {
          this.$message.info('取消删除')
        }
      })
    },
    // 增加 or 修改
    appendOrUpdate() {
      if (this.adType.id) {
        this.updateById()
      } else {
        this.append()
      }
    },

    updateById() {
      adTypeApi.updateById(this.adType).then(response => {
        this.fetchData()
        this.$message.success(response.message)
      })
      this.subject = {}
      this.dialogFormVisible = false
    },

    append() {
      adTypeApi.save(this.adType).then(response => {
        this.fetchData()
        this.$message.success(response.message)
      })
      this.subject = {}
      this.dialogFormVisible = false
    }
  }

}
</script>

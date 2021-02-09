<template>
  <div class="app-container">
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">

      <!-- 所属分类：级联下拉列表 -->
      <!-- 一级分类 -->
      <el-form-item label="动漫类别">
        <el-select
          v-model="searchForm.subjectParentId"
          placeholder="请选择"
          @change="parentSubjectChanged">
          <el-option
            v-for="subject in subjectFirstList"
            :key="subject.id"
            :value="subject.id"
            :label="subject.title" />
        </el-select>

        <!-- 二级分类 -->
        <el-select
          v-model="searchForm.subjectId"
          placeholder="请选择">
          <el-option
            v-for="subject in subjectTwiceList"
            :key="subject.id"
            :value="subject.id"
            :label="subject.title" />
        </el-select>
      </el-form-item>

      <!-- 标题, 带搜索建议 -->
      <el-form-item>
        <el-autocomplete
          v-model="searchForm.title"
          :fetch-suggestions="querySearch"
          :trigger-on-focus="false"
          class="inline-input"
          placeholder="动漫名称"
        />
      </el-form-item>

      <!-- 制作方 -->
      <el-form-item>
        <el-select
          v-model="searchForm.adminId"
          placeholder="请选择制作方">
          <el-option
            v-for="teacher in adminList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"/>
        </el-select>
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="fetchDataByQuery()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 表格 -->
    <el-table :data="animeList" border stripe >

      <el-table-column label="封面" width="200" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.cover" alt="scope.row.title" width="100%">
        </template>
      </el-table-column>
      <el-table-column label="动漫信息">
        <template slot-scope="scope">
          <a>{{ scope.row.title }}</a>
          <p>
            分类：{{ scope.row.subjectParentTitle }} > {{ scope.row.subjectTitle }}
          </p>
          <p>
            集数:{{ scope.row.animeNum+'&nbsp;&nbsp;&nbsp;&nbsp;' }}
            播放量:{{ scope.row.viewCount+'&nbsp;&nbsp;&nbsp;&nbsp;' }}
          </p>
        </template>
      </el-table-column>
      <el-table-column label="制作方" width="100" align="center">
        <template slot-scope="scope">
          {{ scope.row.adminName }}
        </template>
      </el-table-column>
      <el-table-column label="价格(元)" width="100" align="center" >
        <template slot-scope="scope">
          <el-tag v-if="Number(scope.row.price) === 0" type="success">免费</el-tag>
          <el-tag v-else>{{ scope.row.price }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="status" label="发布状态" width="100" align="center" >
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === 'Draft' ? 'warning' : 'success'">{{ scope.row.status === 'Draft' ? '未发布' : '已发布' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="上传时间" width="120" align="center">
        <template slot-scope="scope">
          {{ scope.row.gmtCreate.substr(0, 10) }}
        </template>
      </el-table-column>

      <el-table-column
        v-if="hasPerm('anime.update') || hasPerm('chapter.update') || hasPerm('anime.remove')"
        fixed="right"
        label="操作"
        width="350"
        align="center">
        <template slot-scope="scope">
          <router-link :to="'/anime/info/'+scope.row.id">
            <el-button v-if="hasPerm('anime.update')" type="success" size="mini" icon="el-icon-edit">修改基本信息</el-button>
          </router-link>
          <router-link :to="'/anime/chapter/'+scope.row.id">
            <el-button v-if="hasPerm('chapter.update')" type="primary" size="mini" icon="el-icon-edit">增加视频</el-button>
          </router-link>
          <el-button v-if="hasPerm('anime.remove')" type="danger" size="mini" icon="el-icon-delete" @click="removeById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      :current-page="page"
      :total="recordTotal"
      :page-size="limit"
      :page-sizes="[5, 10, 15]"
      style="padding: 30px 0; text-align: center"
      layout="total, sizes, prev, pager, next, jumper"
      @current-change="changeCurrentPage"
      @size-change="changePageSize"/>

  </div>
</template>

<script>
import animeApi from '@/api/anime'
import adminApi from '@/api/admin'
import subjectApi from '@/api/subject'

export default {
  data() {
    return {
      animeList: [],
      recordTotal: 0,
      limit: 5,
      page: 1,
      searchForm: {
        subjectId: ''
      },
      subjectFirstList: [],
      subjectTwiceList: [],
      adminList: []
    }
  },

  created() {
    this.fetchData()
    this.initadminList()
    this.initSubjectList()
  },

  methods: {
    fetchData() {
      animeApi.pageList(this.page, this.limit, this.searchForm)
        .then(response => {
          this.recordTotal = response.data.total
          this.animeList = response.data.rows
        })
    },

    // 获取动漫一级分类列表，其中含有其子列表
    initSubjectList() {
      subjectApi.getTreeList().then(response => {
        this.subjectFirstList = response.data.items
      })
    },

    // 查询专用分页方法，从第一页开始查，避免和 fetchData() 冲突导致除了第一页以外搜不出数据
    fetchDataByQuery() {
      animeApi.pageList(1, 5, this.searchForm)
        .then((response) => {
          this.recordTotal = response.data.total
          // 如果总记录数达到了可以分页的要求，则计算
          // 记录数可以达到的分页数，以及显示哪个分页，否则不以当前分页页码显示
          if (response.data.total > this.limit) {
            this.page = Math.floor(this.recordTotal / this.limit) + 1
            this.fetchData()
            return
          }
          this.animeList = response.data.rows
          this.recordTotal = response.data.total
        })
    },

    // 切换一级类别的下拉列表时调用该函数，
    // value 是回调参数，是 el-options 里面的 :value 绑定的值
    parentSubjectChanged(value) {
      // 遍历一级类别列表
      this.subjectFirstList.forEach(subject => {
        // 找到当前选中的值对应的一级类别，并取出其二级类别进行渲染
        if (subject.id === value) {
          // 则将当前的一级类别的子类别绑定在页面的二级类别列表中
          this.subjectTwiceList = subject.childrenSubject
          // 清空当前值
          this.searchForm.subjectId = ''
        }
      })
    },

    removeById(id) {
      // 询问是否删除
      this.$confirm('将删除该视频, 是否确定?', 'Warning', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        animeApi.removeById(id).then(response => {
          // 刷新数据
          this.fetchData()
          // 弹出成功提示
          this.$notify({
            message: '<strong> <b style="color:green">删除成功<b><strong>',
            dangerouslyUseHTMLString: true,
            type: 'success'
          })
        })
      }).catch((err) => {
        if (err === 'cancel') {
          this.$notify({
            type: 'info',
            dangerouslyUseHTMLString: true,
            message: '<strong> <b style="color:#8A2BE2">取消删除<b> <strong>'
          })
        }
      })
    },

    // 获取制作方列表
    initadminList() {
      adminApi.list().then(response => {
        this.adminList = response.data.items
      })
    },

    // 重置查询表单，刷新数据
    resetData() {
      this.searchForm = { subjectId: '' }
      this.subjectTwiceList = []
      this.page = 1
      this.limit = 5
      this.fetchData()
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

    // 输入建议
    querySearch(queryString, callback) {
      animeApi.getRecordsNameByKey(queryString).then(response => {
        callback(response.data.records)
      })
    }
  }
}
</script>

<style>

</style>

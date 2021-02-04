<template>
  <article class="col-7 fl">
    <div class="u-r-cont">
      <section>
        <div>
          <section class="c-infor-tabTitle c-tab-title">

            <a href="javascript: void(0)" title="我的收藏" class="current">
              收藏列表
            </a>
          </section>
        </div>
        <div class="mt40">
          <section v-if="collectList.length === 0" class="no-data-wrap">
            <img
              src="~/assets/img/noData.png"
              style="width:150px; height:150px; display: inline"
              class="img-responsive">
            <br>
            <span class="c-666 fsize14 ml10 vam">没有相关收藏</span>
          </section>

          <!-- 表格 -->
          <el-table
            v-if="collectList.length > 0"
            :data="collectList"
            border
            fit
            highlight-current-row>

            <el-table-column label="动漫信息" align="center" >
              <template slot-scope="scope">
                <div class="info" >
                  <div class="pic">
                    <img :src="scope.row.cover" alt="scope.row.title" width="100px">
                  </div>
                  <div class="title">
                    <nuxt-link :to="'/anime/'+scope.row.animeId">{{ scope.row.title }}</nuxt-link>
                  </div>
                </div>
              </template>
            </el-table-column>
            <el-table-column label="制作方名称" align="center">
              <template slot-scope="scope">
                {{ scope.row.adminName }}
              </template>
            </el-table-column>
            <el-table-column label="价格" width="100" align="center">
              <template slot-scope="scope">
                {{ scope.row.price }}
              </template>
            </el-table-column>
            <el-table-column label="收藏时间" align="center">
              <template slot-scope="scope">
                {{ scope.row.gmtCreate }}
              </template>
            </el-table-column>

            <el-table-column label="操作" width="150" align="center">
              <template slot-scope="scope">
                <nuxt-link :to="'/anime/'+scope.row.animeId" style="color:green; margin-right:5px" href="javascript:void(0);" title="去康康">让我康康</nuxt-link>
                |<i
                  class="el-icon-delete"
                  style="cursor:pointer; margin-left:5px"
                  title="取消收藏"
                  @click="removeCollect(scope.row.animeId)"/>
              </template>

            </el-table-column>
          </el-table>
        </div>
      </section>
    </div>
  </article>
</template>
<script>
import collectApi from '~/api/collect'

export default {

  data() {
    return {
      collectList: []
    }
  },

  mounted() {
    this.fetchCollectList()
  },

  methods: {
    fetchCollectList() {
      collectApi.getCollectList().then(response => {
        this.collectList = response.data.items
      })
    },

    removeCollect(animeId) {
      this.$confirm('确认要取消当前收藏吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return collectApi.removeById(animeId)
      }).then((response) => {
        debugger
        this.fetchCollectList()
        this.$message({
          type: 'success',
          message: response.message
        })
      }).catch(error => {
        if (error === 'cancel') {
          this.$message({
            message: '取消删除'
          })
        }
      })
    }
  }
}
</script>

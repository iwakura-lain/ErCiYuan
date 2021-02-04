<template>
  <article class="col-7 fl">
    <div class="u-r-cont">
      <section>
        <div>
          <section class="c-infor-tabTitle c-tab-title">
            <a href="javascript: void(0)" title="我的订单" class="current">
              订单列表
            </a>
          </section>
        </div>
        <div class="mt40">
          <section v-if="orderList.length === 0" class="no-data-wrap">
            <img
              src="~/assets/img/noData.png"
              style="width:150px; height:150px; display: inline"
              class="img-responsive">
            <br>
            <span class="c-666 fsize14 ml10 vam">没有相关订单</span>
          </section>

          <!-- 表格 -->
          <el-table
            v-if="orderList.length>0"
            :data="orderList"
            border
            fit
            highlight-current-row>

            <el-table-column label="动漫信息" align="center" >
              <template slot-scope="scope">
                <div class="info" >
                  <div class="pic">
                    <img :src="scope.row.animeCover" alt="scope.row.animeTitle" width="100px">
                  </div>
                  <div class="title">
                    <a :href="'/anime/'+scope.row.animeId">{{ scope.row.animeTitle }}</a>
                  </div>
                </div>
              </template>
            </el-table-column>
            <el-table-column label="制作方" width="100" align="center">
              <template slot-scope="scope">
                {{ scope.row.adminName }}
              </template>
            </el-table-column>
            <el-table-column label="价格" width="100" align="center" >
              <template slot-scope="scope">
                {{ scope.row.totalFee / 100 }}
              </template>
            </el-table-column>
            <el-table-column label="创建时间" width="180" align="center">
              <template slot-scope="scope">
                {{ scope.row.gmtCreate }}
              </template>
            </el-table-column>
            <el-table-column prop="status" label="订单状态" width="100" align="center" >
              <template slot-scope="scope">
                <el-tag :type="scope.row.status === 0 ? 'warning' : 'success'">{{ scope.row.status === 0 ? '未支付' : '已支付' }}</el-tag>
              </template>
            </el-table-column>

            <el-table-column label="操作" width="150" align="center">
              <template slot-scope="scope" >
                <router-link v-if="scope.row.status === 0" :to="'/order/'+scope.row.id">
                  <el-button type="text" size="mini" icon="el-icon-edit">去支付</el-button>
                </router-link>
                <router-link v-if="scope.row.status === 1" :to="'/anime/'+scope.row.animeId">
                  <el-button type="text" size="mini" icon="el-icon-edit">去观看</el-button>
                </router-link>
                <i
                  v-if="scope.row.status === 0"
                  class="el-icon-delete"
                  style="cursor:pointer"
                  title="删除订单"
                  @click="removeById(scope.row.id)"/>
              </template>

            </el-table-column>
          </el-table>
        </div>
      </section>
    </div>
  </article>
</template>

<script>
import orderApi from '~/api/order'

export default {

  data() {
    return {
      orderList: []
    }
  },

  mounted() {
    this.fetchOrderList()
  },

  methods: {
    fetchOrderList() {
      orderApi.getList().then(response => {
        this.orderList = response.data.items
      })
    },

    removeById(id) {
      this.$confirm('确认要删除当前订单吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return orderApi.removeById(id)
      }).then((response) => {
        this.fetchOrderList()
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

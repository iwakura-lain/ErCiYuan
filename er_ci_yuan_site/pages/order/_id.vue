<template>

  <div class="Page Confirm">
    <div class="Title">
      <h1 class="fl f18">订单确认</h1>
      <div class="clear"/>
    </div>
    <table class="GoodList">
      <tbody>
        <tr>
          <td colspan="3" class="teacher">制作方：{{ order.adminName }}</td>
        </tr>
        <tr class="good">
          <td class="name First">
            <a :href="'https://localhost:3000/anime/'+order.animeId" target="_blank">
              <img :src="order.animeCover">
            </a>
            <div class="goodInfo">
              <a :href="'https://localhost:3000/anime/'+ order.animeId" target="_blank">{{ order.animeTitle }}</a>
            </div>
          </td>
          <td class="red priceNew Last">$<strong>{{ order.totalFee/100 }}</strong></td>
        </tr>
      </tbody>
    </table>
    <div class="Finish">
      <div class="check fr">
        <el-checkbox v-model="agree">我已阅读并同意<a href="javascript:" target="_blank">《二刺螈契约》</a></el-checkbox>
      </div>
      <div class="clear"/>
      <div class="Main fl">
        <div class="fl">
          <nuxt-link :to="'/anime/'+order.animeId">返回动漫详情</nuxt-link>
        </div>
      </div>
      <el-button :disabled="!agree" type="primary" @click="toPay()">去支付</el-button>
      <div class="clear"/>
    </div>
  </div>
</template>

<script>
import orderApi from '~/api/order'

export default {
  data() {
    return {
      order: {},
      agree: true
    }
  },

  mounted() {
    orderApi.getById(this.$route.params.id).then(response => {
      this.order = response.data.item
    })
  },

  methods: {
    toPay() {
      if (this.agree) {
        this.$router.push({ path: '/pay/' + this.order.orderNo })
      }
    }
  }
}
</script>

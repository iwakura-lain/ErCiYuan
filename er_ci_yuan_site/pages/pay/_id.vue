<template>
  <div class="cart py-container">
    <!--主内容-->
    <div class="checkout py-container  pay">
      <div class="checkout-tit" style="width: 1050px; margin: 0 auto; padding: 10px 0;">
        <h4 class="fl tit-txt"><span class="success-info">支付申请提交成功，请在两小时内及时付款！订单号：{{ payObj.outTradeNo }}</span>
        </h4>
        <span class="fr"><em class="sui-lead">应付金额：</em><em class="orange money">💰 {{ payObj.totalFee/100 }} 软妹币</em></span>
        <div class="clearfix"/>
      </div>
      <div class="checkout-steps">
        <div class="fl weixin">微信支付</div>
        <div class="fl sao">
          <div class="fl code">
            <!-- <img id="qrious" src="~/assets/img/erweima.png" alt=""> -->
            <qriously :value="payObj.codeUrl" :size="338"/>
          </div>
          <div style="color: red; text-align:center;">💰 {{ payObj.totalFee/100 }} 软妹币</div>
        </div>
        <div class="clearfix"/>
        <!-- <p><a href="pay.html" target="_blank"> 其他支付方式</a></p> -->
      </div>
    </div>

  </div>
</template>

<script>
import payApi from '~/api/pay'
import orderApi from '~/api/order'

export default {
  async asyncData(page) {
    const response = await payApi.createNative(page.route.params.id)
    return {
      payObj: response.data
    }
  },

  data() {
    return {
      timer: null
    }
  },

  // 轮询订单状态
  mounted() {
    this.timer = setInterval(() => {
      this.queryOrderStatus()
    }, 3000)
  },

  methods: {
    queryOrderStatus() {
      orderApi.queryOrderStatus(this.payObj.outTradeNo).then(response => {
        // 如果订单已经被删除，则清除定时器
        if (response.code === 25002) {
          clearInterval(this.timer)
          return
        }
        // 支付成功
        if (response.success) {
          this.$message.success(response.message)
          // 清除定时器
          clearInterval(this.timer)
          // 3s后跳转到动漫详情页
          setTimeout(() => {
            this.$router.push({ path: '/anime/' + this.payObj.animeId })
          }, 2000)
        }
      })
    }
  }
}
</script>

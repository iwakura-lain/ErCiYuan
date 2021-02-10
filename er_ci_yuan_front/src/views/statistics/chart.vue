<template>
  <div class="app-container">
    <!--表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-date-picker
          v-model="searchObj.startTime"
          type="date"
          placeholder="选择开始日期"
          value-format="yyyy-MM-dd" />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="searchObj.endTime"
          type="date"
          placeholder="选择截止日期"
          value-format="yyyy-MM-dd" />
      </el-form-item>
      <el-button
        :disabled="btnDisabled"
        type="primary"
        icon="el-icon-search"
        @click="showChart()">查询</el-button>
    </el-form>

    <el-row>
      <el-col :span="12"><div id="register_num" style="height:400px;"/></el-col>
      <el-col :span="12"><div id="login_num" style="height:400px;"/></el-col>
    </el-row>
    <el-row>
      <el-col :span="12"><div id="video_view_num" style="height:400px;"/></el-col>
      <el-col :span="12"><div id="anime_num" style="height:400px;"/></el-col>
    </el-row>
  </div>
</template>

<script>
import echarts from 'echarts'
import statisticsApi from '@/api/statistics'

export default {
  data() {
    return {
      searchObj: {},
      btnDisabled: false,
      chartData: {} // 图表数据
    }
  },

  methods: {
    // 展示图表
    showChart() {
      statisticsApi.getCharts(this.searchObj).then(response => {
        this.chartData = response.data.chartData
        this.registerData = response.data.registerChart
        this.registerRadius('register_num',
          '注册数',
          this.registerData)
        this.showChartByType('login_num',
          '登录数',
          this.chartData.loginNum)
        this.showChartByType('video_view_num',
          '播放数',
          this.chartData.videoViewNum)
        this.showChartByType('anime_num',
          '动漫数',
          this.chartData.animeNum)
      })
    },

    registerRadius(type, title, data) {
      var myChart = echarts.init(document.getElementById(type))

      var option = {
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: '5%',
          left: 'center'
        },
        series: [
          {
            name: title,
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: '19',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [
              { value: data.registerCount, name: '总注册数' },
              { value: data.registerCount - (data.giteeCount + data.githubCount), name: '手机号注册' },
              { value: data.giteeCount, name: 'gitee 注册数' },
              { value: data.githubCount, name: 'github 注册数' }
            ]
          }
        ]
      }

      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option)
    },

    showChartByType(type, title, data) {
      // 基于准备好的dom，初始化echarts实例
      var myChart = echarts.init(document.getElementById(type))

      // 指定图表的配置项和数据
      var option = {
        title: {
          text: title
        },
        xAxis: {
          data: data.xData
        },
        yAxis: {},
        series: [{
          type: 'line',
          data: data.yData
        }]
      }

      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option)
    }
  }
}
</script>

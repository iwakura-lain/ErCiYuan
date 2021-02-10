<template>
  <div class="app-container">

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
    var begin = new Date()
    return {
      searchObj: {},
      btnDisabled: false,
      chartData: {}, // 图表数据
      begin: begin,
      end: begin
    }
  },

  mounted() {
    // 起始时间
    this.searchObj.endTime = this.dateFormat('YYYY-mm-dd', this.end)
    // 截止时间
    this.begin.setDate(this.end.getDate() - 7)
    this.searchObj.startTime = this.dateFormat('YYYY-mm-dd', this.begin)
    this.showChart()
  },

  methods: {
    dateFormat(fmt, date) {
      let ret
      const opt = {
        'Y+': date.getFullYear().toString(), // 年
        'm+': (date.getMonth() + 1).toString(), // 月
        'd+': date.getDate().toString() // 日
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
      }
      for (const k in opt) {
        ret = new RegExp('(' + k + ')').exec(fmt)
        if (ret) {
          fmt = fmt.replace(ret[1], (ret[1].length === 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, '0')))
        }
      }
      return fmt
    },
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

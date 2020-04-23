<template>
  <div>
    <el-row :gutter="10">
      <el-col :span="10">
        <el-row style="margin-bottom:10px;">
          <el-col>
            <el-card shadow="never">
            <div slot="header" >
              <span>公司新闻</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="goNewsList('MENU_COMP_NEWS')">更多</el-button>
            </div>
            <el-table :data="compNewsData" style="width: 100%">
              <el-table-column prop="newsTitle" show-overflow-tooltip label="标题" min-width="80" >
              </el-table-column>
            </el-table>
          </el-card>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-card shadow="never">
            <div slot="header" >
              <span>公司公告</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="goNewsList('MENU_COMP_NOTICE')">更多</el-button>
            </div>
            <el-table :data="compNoticeData" style="width: 100%">
              <el-table-column prop="newsTitle" show-overflow-tooltip label="标题" min-width="80" >
              </el-table-column>
            </el-table>
          </el-card>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="10" style="padding-left: 0px;padding-right: 0px;height: 535px;">
        <div>
          <template>
            <el-tabs v-model="activeName">
              <el-tab-pane label="最新要闻" name="news">
                <ul class="middle_content">
                  <template>
                    <li v-for="news in allNewsData" :key="news.key" @click="handleView(news)">
                      <template v-if="news.major===false" ><div class="normalTitle" >{{news.newsTitle}}</div></template>
                      <template v-else ><div class="majorTitle">{{news.newsTitle}}</div></template>
                    </li>
                  </template>
                </ul>
              </el-tab-pane>
            </el-tabs>
          </template>
        </div>
      </el-col>
      <el-col :span="4">
        <el-card shadow="never">
          <div slot="header" >
              <span>后台登陆</span>
            </div>
          <loginForm/>
        </el-card>
        <el-card shadow="never" >
         <div slot="header" >
              <span>快速链接</span>
            </div>
            <div class="visite">
            <ul>
              <li><a href="http://39.100.35.3:8014">东恒鑫源MIS管理系统</a></li>
              <li><a href="http://www.china-cdt.com">中国大唐集团官网</a></li>
            </ul>
            </div>
        </el-card>
        <el-card shadow="never" >
          <div slot="header" >
              <span>最近访问</span>
            </div>
            <div class="visite">
              <ul>
                <li v-for="(item,index) in visiteLogs " :key=index >
                  <router-link :to="{name:item.path,query:{data:item.query}}">{{item.title}}</router-link>
                </li>
              </ul>
            </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import loginForm from '../login/loginForm'
var logs = []
export default {
  data () {
    return {
      activeName: 'news',
      allNewsData: [],
      compNewsData: [],
      tradeNewsData: [],
      bidsNoticeData: [],
      compNoticeData: [],
      professionalNewsData: [],
      mediaNewsData: [],
      visiteLogs: [],
      queryParam: {
        age: 1,
        size: 10
      }
    }
  },
  components: {
    loginForm
  },
  mounted () {
    this.visiteLogs = JSON.parse(localStorage.getItem('visiteLogs'))
    this.initData()
    this.getAllNewsData()
  },
  methods: {
    initData () {
      var that = this
      this.$axios.all([
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/portal/web/news/getNewsList?dataCode=MENU_COMP_NEWS'),
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/portal/web/news/getNewsList?dataCode=MENU_TRADE_NEWS'),
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/portal/web/news/getNewsList?dataCode=MENU_BIDS_NOTICE'),
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/portal/web/news/getNewsList?dataCode=MENU_COMP_NOTICE'),
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/portal/web/news/getNewsList?dataCode=MENU_PROFESSIONAL_NEWS'),
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/portal/web/news/getNewsList?dataCode=MENU_MEDIA_NEWS')
      ]).then(this.$axios.spread(function (compNews, tradeNews, bidsNotice, compNotice, professionalNews, mediaNews) {
        that.compNewsData = compNews.data.dataList
        that.tradeNewsData = tradeNews.data.dataList
        that.bidsNoticeData = bidsNotice.data.dataList
        that.compNoticeData = compNotice.data.dataList
        that.professionalNewsData = professionalNews.data.dataList
        that.mediaNewsData = mediaNews.data.dataList
      }))
    },
    getAllNewsData () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/portal/web/news/getALLNewsList', this.queryParam).then(res => {
        this.allNewsData = res.data.dataList
      })
    },
    goNewsList (v) {
      this.$router.push({ name: 'newsList', query: { dataCode: v } })
    },
    handleView (row) {
      // 记录路由到浏览历史
      console.log(row)
      let vLog = { 'title': row.newsTitle, 'path': 'newsView', query: row }
      logs.unshift(vLog)
      logs = [...new Set(logs)]// 去重
      localStorage.setItem('visiteLogs', JSON.stringify(logs))
      this.$router.push({ name: 'newsView', query: { data: row } })
    }
  }
}
</script>
<style lang="scss" scoped>
.visite >ul{
  padding-left:15px;line-height: 20px;
}
.el-card {
  border-radius: 0px;
}
/deep/.el-card__body {
  padding: 10px 10px 20px 10px;
}
/deep/.el-table__row{
  height: 30px !important;
}
/deep/.el-table td {
  padding: 0px;
}
/deep/.el-table::before {
    left: 0;
    bottom: 0;
    width: 100%;
    /* height: 1px; */
}
/deep/.el-tabs__active-bar{
  background-color: #ce0000;
  margin-left: 10px;
}
/deep/.el-tabs__item.is-active{
  color: #ce0000;
  font-weight: bold;
  font-size: 18px;
  margin-left: 10px;
}
/deep/.el-table thead {
  display: none;
}
/deep/.el-tabs__header {
  margin: 0 0 10px;
}
/deep/.el-tabs__item {
  line-height: 32px;
}
// /deep/.el-table td {
//   border-bottom: 1px solid #ffffff;
// }
.normalTitle {
  font-size:16px;
  color:#404040;
}
.normalTitle:hover, .normalTitle:active{
  color: #ce0000;
  cursor: pointer;
}
.majorTitle{
  font-weight: bold;
  font-size:18px;
  color:#404040;
}
.majorTitle:hover, .majorTitle:active{
  color: #ce0000;
  cursor: pointer;
}
.middle_content>li{
  color:#ccd0dc;
  line-height: 36px;
  width: 450px;
  // text-overflow: clip;
  // white-space: nowrap;
  // overflow: hidden;
}
</style>

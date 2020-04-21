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
      <el-col :span="10">
        我是中间的
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
              <span>精华推荐</span>
            </div>
            <div class="visite">
            <ul>
              <li><a>疫情影响</a></li>
              <li><a>五一放假通知</a></li>
            </ul>
            </div>
        </el-card>
        <el-card shadow="never" >
          <div slot="header" >
              <span>最近访问</span>
            </div>
            <div class="visite">
              <ul>
                <li v-for="(item,index) in visiteLogs " :key=index ><router-link :to="{path:item.path,query:item.query}">{{item.title}}</router-link></li>
              </ul>
            </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import loginForm from '../login/loginForm'
export default {
  data () {
    return {
      compNewsData: [],
      tradeNewsData: [],
      bidsNoticeData: [],
      compNoticeData: [],
      professionalNewsData: [],
      mediaNewsData: [],
      visiteLogs: []
    }
  },
  components: {
    loginForm
  },
  mounted () {
    this.initData()
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
    goNewsList (v) {
      this.$router.push({ name: 'newsList', query: { dataCode: v } })
    },
    handleView (row) {
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
</style>

<template>
  <div>
    <div class="block">
       <el-carousel :interval="4000"  height="200px" >
         <el-carousel-item v-for="item in picList" :key="item.key">
            <img :src="item.fileId===''?'':GlobalVars.globalServiceServlet + '/portal/data/file/downloadFile?fileId=' + item.fileId + '&resoureMenuKey=ALL'"
              width="100%"
               height="200" />
        </el-carousel-item>
      </el-carousel>
  </div>
    <el-row :gutter="10" style="margin-bottom:10px;">
      <el-col :span="8">
        <template>
          <el-card shadow="never">
            <div slot="header" class="clearfix">
              <span>公司新闻</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="goNewsList('MENU_COMP_NEWS')">更多</el-button>
            </div>
            <el-table :data="compNewsData" style="width: 100%">
              <el-table-column prop="newsTitle" show-overflow-tooltip label="标题" min-width="80" >
                <!-- <template slot-scope="scope">
                  <el-link type="primary" @click="handleView(scope.row)">{{scope.row.newsTitle}}</el-link>
                </template> -->
              </el-table-column>
              <!-- <el-table-column prop="creationTime" label="发布日期" min-width="70" ></el-table-column> -->
            </el-table>
          </el-card>
        </template>
      </el-col>
      <el-col :span="8">
        <template>
          <el-card shadow="never">
            <div slot="header" class="clearfix">
              <span>公司公告</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="goNewsList('MENU_COMP_NOTICE')">更多</el-button>
            </div>
            <el-table :data="compNoticeData" style="width: 100%">
              <el-table-column prop="newsTitle" show-overflow-tooltip label="标题" min-width="80" >
                <!-- <template slot-scope="scope">
                  <el-link type="primary" @click="handleView(scope.row)">{{scope.row.newsTitle}}</el-link>
                </template> -->
              </el-table-column>
              <!-- <el-table-column prop="creationTime" label="发布日期" min-width="70" ></el-table-column> -->
            </el-table>
          </el-card>
        </template>
      </el-col>
      <el-col :span="8">
        <template>
          <el-card shadow="never">
            <div slot="header" class="clearfix">
              <span>专题报道</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="goNewsList('MENU_PROFESSIONAL_NEWS')">更多</el-button>
            </div>
            <el-table :data="professionalNewsData" style="width: 100%">
              <el-table-column prop="newsTitle" show-overflow-tooltip label="标题" min-width="80" >
                <!-- <template slot-scope="scope">
                  <el-link type="primary" @click="handleView(scope.row)">{{scope.row.newsTitle}}</el-link>
                </template> -->
              </el-table-column>
              <!-- <el-table-column prop="creationTime" label="发布日期" min-width="70" ></el-table-column> -->
            </el-table>
          </el-card>
        </template>
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :span="8">
        <template>
          <el-card shadow="never">
            <div slot="header" class="clearfix">
              <span>行业新闻</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="goNewsList('MENU_TRADE_NEWS')">更多</el-button>
            </div>
            <el-table :data="tradeNewsData" style="width: 100%">
              <el-table-column prop="newsTitle" show-overflow-tooltip label="标题" min-width="80" >
                <!-- <template slot-scope="scope">
                  <el-link type="primary" @click="handleView(scope.row)">{{scope.row.newsTitle}}</el-link>
                </template> -->
              </el-table-column>
              <!-- <el-table-column prop="creationTime" label="发布日期" min-width="70" ></el-table-column> -->
            </el-table>
          </el-card>
        </template>
      </el-col>
      <el-col :span="8">
        <template>
          <el-card shadow="never">
            <div slot="header" class="clearfix">
              <span>招标公告</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="goNewsList('MENU_BIDS_NOTICE')">更多</el-button>
            </div>
            <el-table :data="bidsNoticeData" style="width: 100%">
              <el-table-column prop="newsTitle" show-overflow-tooltip label="标题" min-width="80" >
                <!-- <template slot-scope="scope">
                  <el-link type="primary" @click="handleView(scope.row)">{{scope.row.newsTitle}}</el-link>
                </template> -->
              </el-table-column>
              <!-- <el-table-column prop="creationTime" label="发布日期" min-width="70" ></el-table-column> -->
            </el-table>
          </el-card>
        </template>
      </el-col>
      <el-col :span="8">
        <template>
          <el-card shadow="never">
            <div slot="header" class="clearfix">
              <span>媒体聚焦</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="goNewsList('MENU_MEDIA_NEWS')">更多</el-button>
            </div>
            <el-table :data="mediaNewsData" style="width: 100%">
              <el-table-column prop="newsTitle" show-overflow-tooltip label="标题" min-width="80" >
                <!-- <template slot-scope="scope">
                  <el-link type="primary" @click="handleView(scope.row)">{{scope.row.newsTitle}}</el-link>
                </template> -->
              </el-table-column>
              <!-- <el-table-column prop="creationTime" label="发布日期" min-width="70" ></el-table-column> -->
            </el-table>
          </el-card>
        </template>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  data () {
    return {
      compNewsData: [],
      tradeNewsData: [],
      bidsNoticeData: [],
      compNoticeData: [],
      professionalNewsData: [],
      mediaNewsData: [],
      picList: [],
      queryBean: {
        page: 1,
        size: 5,
        query: ''
      }
    }
  },
  mounted () {
    this.getPicList()
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
    },
    getPicList () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/portal/web/pictures/getPicturesList', this.queryBean)
        .then(res => {
          var resData = res.data.data.dataList
          this.picList = resData
        })
    }
  }
}
</script>
<style lang="scss" scoped>
.el-carousel{
  margin-bottom: 15px;
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

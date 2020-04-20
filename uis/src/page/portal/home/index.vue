<template>
  <div>
    <div class="block">
       <el-carousel :interval="4000"  height="200px" >
         <el-carousel-item v-for="item in picList" :key="item.key">
          <img :src="item.fileId===''?'':GlobalVars.globalServiceServlet + '/data/file/downloadFile?fileId=' + item.fileId + '&resoureMenuKey=ALL'"
              width="100%"
               height="200" />
        </el-carousel-item>
      </el-carousel>
  </div>
    <el-row :gutter="10" style="margin-bottom:10px;">
      <el-col :span="8">
        <template>
          <el-card>
            <div slot="header" class="clearfix">
              <span>公司新闻</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="goNewsList">更多</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%">
              <el-table-column prop="date" > </el-table-column>
              <el-table-column prop="name" > </el-table-column>
            </el-table>
          </el-card>
        </template>
      </el-col>
      <el-col :span="8">
        <template>
          <el-card>
            <div slot="header" class="clearfix">
              <span>公司公告</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="goNewsList">更多</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%">
              <el-table-column prop="date" > </el-table-column>
              <el-table-column prop="name" > </el-table-column>
            </el-table>
          </el-card>
        </template>
      </el-col>
      <el-col :span="8">
        <template>
          <el-card>
            <div slot="header" class="clearfix">
              <span>专题报道</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="goNewsList">更多</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%">
              <el-table-column prop="date" > </el-table-column>
              <el-table-column prop="name" > </el-table-column>
            </el-table>
          </el-card>
        </template>
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :span="8">
        <template>
          <el-card>
            <div slot="header" class="clearfix">
              <span>行业新闻</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="goNewsList">更多</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%">
              <el-table-column prop="date" > </el-table-column>
              <el-table-column prop="name" > </el-table-column>
            </el-table>
          </el-card>
        </template>
      </el-col>
      <el-col :span="8">
        <template>
          <el-card>
            <div slot="header" class="clearfix">
              <span>招标公告</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="goNewsList">更多</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%">
              <el-table-column prop="date" > </el-table-column>
              <el-table-column prop="name" > </el-table-column>
            </el-table>
          </el-card>
        </template>
      </el-col>
      <el-col :span="8">
        <template>
          <el-card>
            <div slot="header" class="clearfix">
              <span>媒体聚焦</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="goNewsList">更多</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%">
              <el-table-column prop="date" > </el-table-column>
              <el-table-column prop="name" > </el-table-column>
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
      tableData: [],
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
  },
  methods: {
    goNewsList () {
      // this.$router.push('/portal/main/index')
    },
    getPicList () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/portal/web/pictures/getPicturesList', this.queryBean)
        .then(res => {
          var resData = res.data.dataList
          console.log(resData)
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
</style>

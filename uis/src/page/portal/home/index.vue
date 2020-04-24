<template>
  <div>
    <el-row :gutter="10">
      <el-col :span="10">
        <el-row style="margin-bottom:10px;">
          <el-col>
            <el-card shadow="never">
            <div slot="header" >
              <div style="font-size:16px; font-weight: bold;color:#FFFFFF;float:left">公司新闻</div>
              <el-button style="float: right; padding: 3px 0" type="text" @click="goNewsList('MENU_COMP_NEWS')">
                <div style="font-size:14px;">更多 ></div>
              </el-button>
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
              <div style="font-size:16px; font-weight: bold;color:#FFFFFF;float:left">公司公告</div>
              <el-button style="float: right; padding: 3px 0" type="text" @click="goNewsList('MENU_COMP_NOTICE')">
                <div style="font-size:14px;">更多 ></div>
              </el-button>
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
              <li><a href="http://39.100.35.3:8014" target="_blank" >东恒鑫源MIS管理系统</a></li>
              <li><a href="http://www.china-cdt.com" target="_blank">中国大唐集团官网</a></li>
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
                  <router-link :to="{name:item.path,query:{key:item.query}}">{{item.title}}</router-link>
                </li>
              </ul>
            </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import datas from './datas.js'
export default datas
</script>
<style lang="scss" scoped>
@import './styles.scss';
</style>

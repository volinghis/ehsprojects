<template>
  <div>
    <el-row :gutter="10" style="margin-bottom:10px;">
      <el-col :span="8">
        <template>
          <el-card>
            <div slot="header" class="clearfix">
              <span>公司新闻</span>
              <el-button style="float: right; padding: 3px 0" type="text">更多</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%">
              <el-table-column prop="date" label="日期"> </el-table-column>
              <el-table-column prop="name" label="姓名"> </el-table-column>
            </el-table>
          </el-card>
        </template>
      </el-col>
      <el-col :span="8">
        <template>
          <el-card>
            <div slot="header" class="clearfix">
              <span>公司公告</span>
              <el-button style="float: right; padding: 3px 0" type="text">更多</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%">
              <el-table-column prop="date" label="日期"> </el-table-column>
              <el-table-column prop="name" label="姓名"> </el-table-column>
            </el-table>
          </el-card>
        </template>
      </el-col>
      <el-col :span="8">
        <template>
          <el-card>
            <div slot="header" class="clearfix">
              <span>专题报道</span>
              <el-button style="float: right; padding: 3px 0" type="text">更多</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%">
              <el-table-column prop="date" label="日期"> </el-table-column>
              <el-table-column prop="name" label="姓名"> </el-table-column>
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
              <el-button style="float: right; padding: 3px 0" type="text">更多</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%">
              <el-table-column prop="date" label="日期"> </el-table-column>
              <el-table-column prop="name" label="姓名"> </el-table-column>
            </el-table>
          </el-card>
        </template>
      </el-col>
      <el-col :span="8">
        <template>
          <el-card>
            <div slot="header" class="clearfix">
              <span>招标公告</span>
              <el-button style="float: right; padding: 3px 0" type="text">更多</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%">
              <el-table-column prop="date" label="日期"> </el-table-column>
              <el-table-column prop="name" label="姓名"> </el-table-column>
            </el-table>
          </el-card>
        </template>
      </el-col>
      <el-col :span="8">
        <template>
          <el-card>
            <div slot="header" class="clearfix">
              <span>媒体聚焦</span>
              <el-button style="float: right; padding: 3px 0" type="text">更多</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%">
              <el-table-column prop="date" label="日期"> </el-table-column>
              <el-table-column prop="name" label="姓名"> </el-table-column>
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
      tableData: []
    }
  }
}
</script>

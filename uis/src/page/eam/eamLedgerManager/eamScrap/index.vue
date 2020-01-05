<template>
  <div>
    <el-card shadow="hover">
      <div class="table-page-search-wrapper">
        <el-form label-position="right"
                 :inline="true"
                 label-width="80px">
          <el-form-item label="报废编号:">
            <el-input v-model="queryParam.scrapNum"
                      size="small"
                      style="width:300px;" />
          </el-form-item>
          <el-form-item label="报废部门:">
            <el-input v-model="queryParam.scrapDep"
                      size="small"
                      style="width:300px;" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary"
                       :size="GlobalCss.buttonSize"
                       @click="$refs.table.refresh(true)"
                       icon="el-icon-search">查询</el-button>
            <el-button :size="GlobalCss.buttonSize"
                       @click="() => (queryParam = {})">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="operate">
        <el-button type="primary"
                  :size="GlobalCss.buttonSize"
                   @click="handleScrap">报废申请</el-button>
        <el-button type="primary"
                   :size="GlobalCss.buttonSize"
                   @click="handleDelete">删除</el-button>
      </div>
      <div class="table-list">
        <template>
          <el-table :data="tableData"
                    style="width: 100%"
                    border
                    size="medium"
                    @select="onChange">
            <el-table-column type="selection"
                             width="50"> </el-table-column>
            <el-table-column prop="scrapNum"
                             label="报废编号"></el-table-column>
            <el-table-column prop="applicationName"
                             label="申请名称"></el-table-column>
            <el-table-column prop="applicationTime"
                             label="申请时间"></el-table-column>
            <el-table-column prop="purchaseTime"
                             label="采购时间"></el-table-column>
            <el-table-column prop="scrapDate"
                             label="报废日期"></el-table-column>
            <el-table-column prop="applicant"
                             label="申请人"></el-table-column>
            <el-table-column prop="scrapDept"
                             label="报废部门"></el-table-column>
            <el-table-column prop="scrapReason"
                             label="报废原因"></el-table-column>
            <el-table-column prop="status"
                             label="申请状态"
                             align="center">
              <template slot-scope="scope">
                <div slot="reference">
                  <el-tag :size="GlobalCss.buttonSize"
                          :type="scope.row.status === '进行中' ? 'primary' : 'success'">{{ scope.row.status}}</el-tag>
                </div>
              </template>

            </el-table-column>

          </el-table>
          <div class="pagination"
               style="float:right;margin-top:12px;">
            <el-pagination background
                           layout="total, prev, pager, next"
                           :current-page="1"
                           :page-size="10"
                           :total="100"
                           @current-change="handlePageChange"></el-pagination>
          </div>
        </template>
      </div>
    </el-card>
  </div>
</template>
<script>
export default {
  data () {
    return {
      queryParam: {},
      form: {},
      selections: [],
      tableData: [
        {
          scrapNum: '2019100001',
          deviceName: '锅炉',
          applicationName: '2019锅炉报废申请',
          scrapDept: '锅炉专业',
          scrapDate: '2019-05-02',
          applicationTime: '2019-05-03',
          scrapReason: '严重故障，无法修理',
          purchaseTime: '2016-05-02',
          applicant: '张三',
          status: '进行中'
        },
        {
          scrapNum: '2019100001',
          deviceName: '锅炉',
          applicationName: '2019锅炉报废申请',
          scrapDept: '锅炉专业',
          scrapDate: '2019-05-02',
          applicationTime: '2019-05-03',
          scrapReason: '严重故障，无法修理',
          purchaseTime: '2016-05-02',
          applicant: '张三',
          status: '进行中'

        },
        {
          scrapNum: '2019100001',
          deviceName: '锅炉',
          applicationName: '2019锅炉报废申请',
          scrapDept: '锅炉专业',
          scrapDate: '2019-05-02',
          applicationTime: '2019-05-03',
          scrapReason: '严重故障，无法修理',
          purchaseTime: '2016-05-02',
          applicant: '张三',
          status: '进行中'

        },
        {
          scrapNum: '2019100001',
          deviceName: '锅炉',
          applicationName: '2019锅炉报废申请',
          scrapDept: '锅炉专业',
          scrapDate: '2019-05-02',
          applicationTime: '2019-05-03',
          scrapReason: '严重故障，无法修理',
          purchaseTime: '2016-05-02',
          applicant: '张三',
          status: '已通过'
        }
      ]
    }
  },
  mounted: function () {
  },
  methods: {
    customColorMethod: function (percentage) {
      if (percentage < 30) {
        return '#909399'
      } else if (percentage < 70) {
        return '#e6a23c'
      } else {
        return '#67c23a'
      }
    },
    handleDelete: function () {
      var _this = this.selections
      if (_this.length <= 0) {
        this.$message({
          message: '请选择一条记录',
          type: 'warning'
        })
      }
    },
    onChange: function (row) {
      this.selections = row
    },
    handlePageChange: function () {
    },
    // 新增操作
    handleScrap: function () {
      this.$router.push({ name: '241' })
    }
  }
}
</script>
<style lang="scss" scoped>
.operate {
  margin-bottom: 20px;
}
.el-card {
  border: 1px solid #ffffff;
}
</style>

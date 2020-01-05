<template>
  <div>
    <el-card shadow="hover">
      <div class="table-page-search-wrapper">
        <el-form label-position="right"
                 :inline="true"
                 label-width="80px">
          <el-form-item label="调拨编号:">
            <el-input v-model="queryParam.allocateNum"
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
                   @click="handleallocate">调拨申请</el-button>
      </div>
      <div class="table-list">
        <template>
          <el-table :data="tableData"
                    style="width: 100%"
                    border
                    size="medium">
            <!-- <el-table-column type="index" width="50"></el-table-column> -->
            <el-table-column type="selection"
                             width="50"> </el-table-column>
            <el-table-column prop="allocateNum"
                             label="调拨编号"></el-table-column>
            <el-table-column prop="applicationName"
                             label="申请名称"></el-table-column>
            <el-table-column prop="applicationTime"
                             label="申请时间"></el-table-column>
            <el-table-column prop="purchaseTime"
                             label="采购时间"></el-table-column>
            <el-table-column prop="allocateDate"
                             label="调拨日期"></el-table-column>
            <el-table-column prop="applicant"
                             label="申请人"></el-table-column>
            <!-- <el-table-column prop="allocateDep"
                             label="调拨部门"></el-table-column> -->
            <el-table-column prop="sourcePosition"
                             label="调出位置"></el-table-column>
            <el-table-column prop="targetPosition"
                             label="调入位置"></el-table-column>
            <el-table-column prop="allocateReason"
                             label="调拨原因"></el-table-column>
            <el-table-column prop="status"
                             label="申请状态">
              <template slot-scope="scope">
                <div slot="reference">
                  <el-tag size="medium"
                          :type="scope.row.status === '进行中' ? 'primary' : 'success'">{{ scope.row.status}}</el-tag>
                </div>
              </template></el-table-column>
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
      tableData: [
        {
          allocateNum: '2019100001',
          deviceName: '锅炉',
          applicationName: '2019锅炉调拨申请',
          sourcePosition: '锅炉专业',
          targetPosition: '汽机专业',
          allocateDate: '2019-05-02',
          applicationTime: '2019-05-03',
          allocateReason: '没有剩余备件',
          purchaseTime: '2016-05-02',
          applicant: '张三',
          status: '进行中'

        },
        {
          allocateNum: '2019100001',
          deviceName: '锅炉',
          applicationName: '2019锅炉调拨申请',
          sourcePosition: '锅炉专业',
          targetPosition: '汽机专业',
          allocateDate: '2019-05-02',
          applicationTime: '2019-05-03',
          allocateReason: '没有剩余备件',
          purchaseTime: '2016-05-02',
          applicant: '张三',
          status: '进行中'

        },
        {
          allocateNum: '2019100001',
          deviceName: '锅炉',
          applicationName: '2019锅炉调拨申请',
          sourcePosition: '锅炉专业',
          targetPosition: '汽机专业',
          allocateDate: '2019-05-02',
          applicationTime: '2019-05-03',
          allocateReason: '原部门富余',
          purchaseTime: '2016-05-02',
          applicant: '张三',
          status: '已通过'

        },
        {
          allocateNum: '2019100001',
          deviceName: '锅炉',
          applicationName: '2019锅炉调拨申请',
          sourcePosition: '锅炉专业',
          targetPosition: '汽机专业',
          allocateDate: '2019-05-02',
          applicationTime: '2019-05-03',
          allocateReason: '借用',
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
    handleViewClick: function (scope) {
      // 详情查看
      this.$router.push({ name: '23' })
    },
    handleEditClick: function (scope) {
      this.$router.push({ name: '22' })
    },
    handlePageChange: function () {
    },
    // 新增操作
    handleallocate: function () {
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

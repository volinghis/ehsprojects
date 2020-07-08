<template>
  <div>
    <div class="search-wrapper" style="width:30%;margin-bottom:5px;">
      <el-input :size="GlobalCss.controlSize" v-model="form.query" placeholder="请输入角色编码或名称">
        <el-button slot="append" icon="el-icon-search" @click="getLeftMenuRoles"></el-button>
      </el-input>
      <el-form ref="searchForm" :model="form" :inline="true">
        <!-- <el-form-item>
          <el-input type="text" :size="GlobalCss.buttonSize" v-model="form.query" placeholder="请输入角色编码或名称"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getLeftMenuRoles" :size="GlobalCss.buttonSize">查询</el-button>
        </el-form-item> -->
      </el-form>
    </div>
    <div class="table-container">
      <el-table :data="tableData" :size="GlobalCss.controlSize" border v-loading="loading"
        @selection-change="handleSelectionChange" style="width: 100%">
        <!-- <el-table-column type="index"  align="center"  width="50"></el-table-column> -->
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="name" align="center" label="角色姓名"></el-table-column>
        <el-table-column prop="dataCode" align="center" label="角色编码"></el-table-column>
        <el-table-column prop="remark" align="center" label="备注"></el-table-column>
        <el-table-column prop="creationTime" align="center" label="创建时间"></el-table-column>
      </el-table>
      <el-pagination @current-change="handleCurrentChange" :current-page.sync="form.page" :page-size="form.size" background
        layout="total, prev, pager, next" :total="totalCount">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      tableData: [],
      totalCount: 0,
      loading: true,
      multipleSelection: [],
      form: {
        query: '',
        page: 1,
        size: 5
      }
    }
  },
  props: {
    currentMenuKey: String,
    dialogTableVisible: Boolean
  },
  mounted () {
    this.getLeftMenuRoles()
  },
  watch: {
    dialogTableVisible (value) {
      if (value) {
        this.getLeftMenuRoles()
      }
    }
  },
  methods: {
    handleCurrentChange () {},
    getLeftMenuRoles () { // 获取所有待选角色
      this.form['menuKey'] = this.currentMenuKey
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/auth/menu/findLeftRolesByMenuKey', this.form).then(
        res => {
          this.tableData = res.data.dataList
          this.totalCount = res.data.totalCount
          this.loading = false
        })
    },
    handleSelectionChange (val) {
      this.multipleSelection = val
      this.$emit('handleSelect', val)
    }
  }
}

</script>

<style lang="scss" scoped>
  .el-pagination {
    text-align: right;
    margin-top: 10px;
  }
/deep/.el-input-group__append {
    background-color: #0d3154;
    border: 1px solid #0d3154;
    color:#fff
  }
</style>

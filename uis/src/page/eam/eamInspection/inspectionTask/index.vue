<template>
  <div :style="{height:$store.state.contentHeight+'px',padding:'10px',background:'#fff'}" class="cardHeight">
    <el-form :inline="true" :model="queryParam" :size="GlobalCss.buttonSize" label-position="right" label-width="120" class="fromHeight">
      <el-form-item label="任务编号：">
          <el-input v-model="queryParam.code" placeholder="请输入任务编号"/>
      </el-form-item>
      <el-form-item label="任务名称：">
          <el-input v-model="queryParam.name" placeholder="请输入任务名称"/>
      </el-form-item>
      <el-form-item style="margin-left:40px;">
        <span>
          <el-button type="primary" @click="$refs.table.refresh(true)">查询</el-button>
          <el-button style="margin-left: 8px" @click="() => queryParam = {}">重置</el-button>
        </span>
      </el-form-item>
    </el-form>
    <div class="operatorHeight">
      <el-button type="primary" icon="el-icon-plus" class="buttonHeight" :size="GlobalCss.buttonSize" @click="handleAdd()">新增</el-button>
    </div>
    <template>
      <el-table :data="tableData" border ref="multipleTable" resizable highlight-current-row class="tableHeight" :max-height="htable" :size="GlobalCss.buttonSize">
        <el-table-column fixed="left" type="selection" width="50" align="center"> </el-table-column>
        <el-table-column fixed="left" type="index" width="50" align="center"> </el-table-column>
        <el-table-column fixed="left"  prop="code" label="任务编号" width="120" align="center"> </el-table-column>
        <el-table-column  prop="name" label="任务名称" width="120" align="center"> </el-table-column>
        <!-- <el-table-column  prop="completion" label="资料完整度" width="160" sortable>
            <template slot-scope="scope">
                <el-progress :percentage="scope.row.completion" :color="customColors" ></el-progress>
            </template>
        </el-table-column> -->
        <el-table-column prop="type" label="责任部门" width="120" align="center"> </el-table-column>
        <el-table-column prop="norm" label="责任人" width="120" align="center"> </el-table-column>
        <el-table-column prop="brand" label="开始时间" width="140" :show-overflow-tooltip="true" align="center"> </el-table-column>
        <el-table-column prop="warehouse" label="结束时间" width="140" align="center"> </el-table-column>
        <el-table-column prop="amount" label="设备数量" width="100" sortable align="center"> </el-table-column>
        <el-table-column prop="status" label="任务状态" align="center">
          <template slot-scope="scope">
            <div slot="reference">
              <el-tag size="mini" v-if="(scope.row.status === '进行中')" type="primary">{{ scope.row.status}}</el-tag>
              <el-tag size="mini" v-else-if="(scope.row.status  === '已完成')" type="success">{{ scope.row.status}}</el-tag>
              <el-tag size="mini" v-else-if="(scope.row.status  === '未执行')" type="danger">{{ scope.row.status}}</el-tag>
              <el-tag size="mini" v-else-if="(scope.row.status === '已作废')" type="info">{{ scope.row.status}}</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="150" align="center">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="info" :size="GlobalCss.controlSize">作废</el-button>
          <el-button @click="handleEdit(scope.row)" type="primary" :size="GlobalCss.controlSize">延期</el-button>
        </template>
        </el-table-column>
      </el-table>
      <el-pagination
        class="pageHeight"
        background
        style="text-align:right;"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage1"
        :page-sizes="[10, 20, 50]"
        :page-size="10"
        layout="total,sizes,prev, pager, next"
        :total="100">
      </el-pagination>
    </template>
  </div>
</template>
<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import './styles.scss';
</style>

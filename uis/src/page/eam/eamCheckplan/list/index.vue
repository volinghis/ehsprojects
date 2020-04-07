<template>
  <div>
    <!-- <el-card style="margin-left:10px;margin-right:10px;"> -->
    <div class="topPanel">
      <div class="queryBodys">
        <el-form ref="ruleForm" style="width:700px;" label-suffix="：" label-position="left" size="mini" label-width="80px" :inline-message="true" :status-icon="true" class="demo-ruleForm">
          <el-form-item label="执行频率">
            <el-radio-group v-model="queryBean.rates" @change="flushData()">
               <el-radio border label="ALL">全部</el-radio>
              <el-radio border label="DAY">天</el-radio>
              <el-radio border label="WEEK">周</el-radio>
              <el-radio border label="MONTH">月</el-radio>
              <el-radio border label="YEAR">年</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="计划分类">
            <el-radio-group v-model="queryBean.types" @change="flushData()">
               <el-radio border label="ALL">全部</el-radio>
              <el-radio border label="OWNER">我创建的</el-radio>
              <el-radio border label="NEEDEXECUTE">需要我执行的</el-radio>
            </el-radio-group>
          </el-form-item>
           <el-form-item label="计划状态">
            <el-radio-group v-model="queryBean.status" @change="flushData()">
               <el-radio border label="ALL">全部</el-radio>
              <el-radio border label="ENABLE">启用</el-radio>
              <el-radio border label="DISABLE">停止</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="计划时效" >
            <el-radio-group v-model="queryBean.executes" @change="flushData()">
               <el-radio border label="ALL">全部</el-radio>
              <el-radio border label="EFFECTIVE">有效</el-radio>
              <el-radio border label="INVALID">过期</el-radio>
              <el-radio border label="EVERSTART">未开始  </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>
      </div>
         <div class="ehs_form_item_message">
        1)列表显示本人创建的，需要本人执行的，以及开放给所有人的计划。<br>2)非被人创建的的计划仅能查看。<br>3)对于有效期(当前时间小于等于结束时间)内的计划可以进行启动停止或延期操作。<br>4)立即执行功能可以快捷执行计划，如计划没有达到执行频率点，需要执行计划可采用此方法。<br>5)通常计划创建之后，我们不建议您再进行延期变动，如有此需求，建议重新建立计划。
      </div>
      <div class="rightButtons">
        <el-button type="primary" :size="GlobalCss.buttonSize" @click="add()" icon="el-icon-plus" style="margin-bottom:5px;">新增</el-button>
      </div>
    </div>
    <el-table :data="plans" border :size="GlobalCss.buttonSize" @sort-change="sortchange">
      <el-table-column type="index" show-overflow-tooltip min-width="40" align="center" label="序号"></el-table-column>
      <el-table-column show-overflow-tooltip min-width="100" align="center" prop="name" sortable="custom" label="计划名称">
        <template slot-scope="scope">
          <el-link type="primary" @click="handleClick(scope.row)">{{scope.row.name}}</el-link>
        </template>
      </el-table-column>
      <el-table-column show-overflow-tooltip min-width="90" align="center" prop="year" sortable="custom" label="计划年度"></el-table-column>
      <!-- <el-table-column show-overflow-tooltip min-width="90" align="center" prop="ownerName" sortable="custom" label="创建人"></el-table-column> -->
      <el-table-column show-overflow-tooltip min-width="130" align="center" prop="ownerCreationTime" sortable="custom" label="创建时间"></el-table-column>
      <el-table-column show-overflow-tooltip min-width="100" align="center" prop="startTime" sortable="custom" label="开始时间"></el-table-column>
      <el-table-column show-overflow-tooltip min-width="100" align="center" prop="endTime" sortable="custom" label="结束时间"></el-table-column>
      <el-table-column show-overflow-tooltip min-width="100" align="center" prop="rate" width="100" sortable="custom" label="执行频率">
         <template slot-scope="scope">
            <el-tag size="medium">{{ scope.row.rate }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column show-overflow-tooltip min-width="90" align="center" prop="execute" sortable="custom" label="计划时效">
        <template slot-scope="scope">
          <template v-if="scope.row.execute ==='有效'">
            <el-tag size="medium" type="success">{{ scope.row.execute }}</el-tag>
          </template>
          <template v-else-if="scope.row.execute ==='已过期'">
            <el-tag size="medium" type="danger">{{ scope.row.execute }}</el-tag>
          </template>
          <template v-else-if="scope.row.execute ==='未开始'">
            <el-tag size="medium" type="primary">{{ scope.row.execute }}</el-tag>
          </template>
        </template>
      </el-table-column>
      <el-table-column show-overflow-tooltip min-width="70" align="center" prop="enable" sortable="custom" label="状态">
        <template slot-scope="scope" v-if="resetTimeCheck(scope.row)">
          <el-tooltip :content="scope.row.enable === true ? '启用中':'停用中'" placement="left">
            <el-switch @change="changeState($event,scope.row,scope.$index)" v-model="scope.row.enable"
              active-color="#13ce66" inactive-color="#ff4949">
            </el-switch>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column show-overflow-tooltip min-width="140" align="center" label="操作">
        <template slot-scope="scope">
          <el-button type="info" :size="GlobalCss.buttonSize" v-if="resetTimeCheck(scope.row)" @click="delay(scope.row)">延期</el-button>
          <el-button type="warning" :size="GlobalCss.buttonSize" v-if="resetTimeCheck(scope.row)" @click="comply(scope.row)">执行</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="bottomPanel">
      <el-pagination style="float:right" :current-page="queryBean.page" @current-change="changePage"
        :page-size="queryBean.size" layout="total, prev, pager, next" :total="queryBean.totalCount">
      </el-pagination>
    </div>

    <el-dialog title="延期--选择时间" :visible.sync="dialogVisible" width="40%" :destroy-on-close="true">
      <div>
        <el-form :model="formDate" label-width="80px">
          <el-form-item label="原定时间：">
            <el-date-picker v-model="formDate.oldTime" type="date" placeholder="选择日期" style="width: 100%;" size="small"
              :disabled="true"></el-date-picker>
          </el-form-item>
          <el-form-item label="延期时间：">
            <el-date-picker v-model="formDate.newTime" type="date" placeholder="选择日期" style="width: 100%;" size="small">
            </el-date-picker>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="handleReset" :size="GlobalCss.controlSize">取 消</el-button>
        <el-button type="primary" @click="handleSubmit" :size="GlobalCss.controlSize">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="详情页面" :visible.sync="dialogVisibleView" width="40%" :destroy-on-close="true">
      <el-divider></el-divider>
      <div>
        <el-form ref="dataView" :model="dataView" label-width="80px" label-position="right" size="small" :disabled="true">
          <el-form-item label="计划名称:">
            <!-- <el-input v-model="dataView.name"></el-input> -->
            <span>{{dataView.name}}</span>
          </el-form-item>
          <el-form-item label="执行频率:">
            <!-- <el-input v-model="dataView.rate"></el-input> -->
            <span>{{dataView.rate}}</span>
          </el-form-item>
          <el-form-item label="计划年度:">
            <!-- <el-input v-model="dataView.year"></el-input> -->
            <span>{{dataView.year}}</span>
          </el-form-item>
          <el-form-item label="计划周期:">
            <!-- <el-col :span="4"> -->
              <span>{{dataView.startTime}}</span> — <span>{{dataView.endTime}}</span>
              <!-- <el-form-item prop="startTime">
                 <el-date-picker type="date" placeholder="开始日期" v-model="dataView.startTime"  style="width: 100%;"></el-date-picker>
                <span>{{dataView.startTime}}</span> -->
              <!-- </el-form-item>
            </el-col>
            <el-col class="line" :span="1" style="text-align:center">-</el-col>
            <el-col :span="11">
              <el-form-item prop="endTime">
                <el-date-picker placeholder="结束日期" type="date" v-model="dataView.endTime" style="width: 100%;"></el-date-picker>
                <span>{{dataView.endTime}}</span>
              </el-form-item>
            </el-col>-->
          </el-form-item>
          <el-form-item label="设备位置:">
            <!-- <el-input v-model="dataView.deviceAddress"></el-input> -->
            <span>{{dataView.deviceAddress}}</span>
          </el-form-item>
          <el-form-item label="巡检范围:">
            <!-- <el-input v-model="dataView.checkScopeStr"></el-input> -->
             <span>{{checkScopeStrName}}</span>
          </el-form-item>
          <el-form-item label="执行部门:">
            <!-- <el-input v-model="dataView.checkorName"></el-input> -->
             <span>{{dataView.checkorName}}</span>
          </el-form-item>
          <el-form-item label="是否启用:">
            <!-- <el-input v-model="dataView.enableLabel"></el-input> -->
             <span>{{dataView.enableLabel}}</span>
          </el-form-item>
          <el-form-item label="授权查看:">
            <!-- <el-input v-model="dataView.viewType"></el-input> -->
             <span>{{dataView.viewType}}</span>
          </el-form-item>
          <el-form-item label="备注:">
            <!-- <el-input type="textarea" maxlength="300" show-word-limit v-model="dataView.notes"></el-input> -->
             <span>{{dataView.notes}}</span>
          </el-form-item>
        </el-form>
      </div>
    </el-dialog>
    <!-- </el-card> -->
  </div>
</template>
<script>
import datas from './datas'
export default datas

</script>
<style lang="scss" scoped>
  @import "./styles.scss";

</style>

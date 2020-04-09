<template>
  <div>
    <el-table :data="tableData" resizable highlight-current-row border @row-dblclick="handleClick" @cell-click="handleCurrentChange"
              :size="GlobalCss.buttonSize" :summary-method="getSummaries" show-summary style="width: 100%;" class="tb-edit">
      <el-table-column prop="deviceCode" label="备件编号" show-overflow-tooltip min-width="100" align="center">
        <template slot-scope="scope">
            <el-link type="primary" @click="handleRowClick(scope.row)">{{scope.row.deviceCode}}</el-link>
          </template>
      </el-table-column>
      <el-table-column prop="deviceName" label="备件名称" show-overflow-tooltip min-width="100" align="center"></el-table-column>
      <el-table-column prop="norm" label="规格型号" show-overflow-tooltip min-width="100" align="center"></el-table-column>
      <el-table-column prop="leaveFactoryCode" label="出厂编号" show-overflow-tooltip min-width="100" align="center"></el-table-column>
      <el-table-column prop="unit" label="单位" show-overflow-tooltip min-width="70" align="center"></el-table-column>
      <el-table-column prop="price" label="单价" show-overflow-tooltip min-width="80" align="center"></el-table-column>
      <el-table-column prop="amount" label="数量"  show-overflow-tooltip min-width="120" align="center">
        <template slot-scope="scope">
          <template v-if="edit">
            <el-input size="mini" validate-event v-model="scope.row.amount" placeholder="请输入数量" width="100" @change="handleAmountEdit(scope.$index, scope.row)"></el-input>
          </template>
          <template v-else>
            <span>{{scope.row.amount}}</span>
          </template>
        </template>
      </el-table-column>
      <el-table-column v-if="flagBotton" prop="dummyAmount" label="库存数量" show-overflow-tooltip min-width="80" align="center"></el-table-column>
      <el-table-column prop="totalPrice" label="总价" show-overflow-tooltip min-width="80" align="center"></el-table-column>
      <el-table-column label="操作" show-overflow-tooltip min-width="80" align="center" v-if="flagBotton">
        <template slot-scope="scope">
          <!-- <el-button @click="handleEdit(scope.row)"  :size="GlobalCss.buttonSize" type="primary">编辑</el-button> -->
          <el-button @click="handleDel(scope.$index,tableData)" :size="GlobalCss.buttonSize" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- <el-pagination class="pageHeight" background style="text-align:right;" :current-page.sync="form.page"
                   :page-size="form.size" layout="total, prev, pager, next" :total="totalCount">
    </el-pagination> -->
    <el-drawer title="备件编辑" :visible.sync="drawer" :direction="direction" destroy-on-close :wrapperClosable="false" size="60%" >
      <el-divider></el-divider>
      <editPart ref="partData" :flag="flagMark" :partsForm="partsFormEdit"></editPart>
      <div style="text-align:center;">
        <el-button type="primary" :size="GlobalCss.buttonSize" @click="saveForm" v-if="flagBotton">保 存</el-button>
        <el-button :size="GlobalCss.buttonSize" @click="handleClose" v-if="flagBotton">取 消</el-button>
      </div>
    </el-drawer>
    <el-dialog width="50%" title="备件详情" :visible.sync="dialogVisible" append-to-body>
       <div class="pageClass">
        <el-row :gutter="10">
          <el-col :span="5">
            <div class="item-block" style="text-align:center;">
              <el-image v-if="imageUrl" :src="imageUrl" class="avatar"></el-image>
            </div>
          </el-col>
          <el-col :span="19">
              <el-row >
                <el-form label-suffix="：" label-width="80px">
                  <el-col :span="8">
                      <el-form-item label="备件编码">
                        <span class="info-content">{{ form.deviceCode }}</span>
                      </el-form-item>
                      <el-form-item label="备件名称">
                        <span class="info-content">{{ form.deviceName }}</span>
                      </el-form-item>
                      <el-form-item label="规格型号">
                        <span class="info-content">{{ form.norm }}</span>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="物资类型">
                        <span class="info-content">{{ form.materialType }}</span>
                      </el-form-item>
                      <el-form-item label="物资编码">
                        <span class="info-content">{{ form.materialCode }}</span>
                      </el-form-item>
                      <el-form-item label="生产厂家">
                        <span class="info-content">{{ form.manufacturer }}</span>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="出厂编号">
                        <span class="info-content">{{ form.leaveFactoryCode }}</span>
                      </el-form-item>
                      <el-form-item label="出厂日期">
                        <span class="info-content">{{ form.leaveFactoryDate }}</span>
                      </el-form-item>
                  </el-col>
                </el-form>
              </el-row>
          </el-col>
        </el-row>
      </div>
     </el-dialog>
  </div>
</template>

<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import "./styles.scss";
</style>

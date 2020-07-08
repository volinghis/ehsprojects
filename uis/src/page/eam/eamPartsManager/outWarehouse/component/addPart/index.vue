<template>
  <div>
    <el-form :model="queryBean">
      <el-input :size="GlobalCss.controlSize" v-model="queryBean.query" placeholder="请输入名称、编号、类型搜索" style="margin:5px 0px; width:30%;float:left;">
        <el-button slot="append" @click="getAllParts" icon="el-icon-search"></el-button>
      </el-input>
    </el-form>
    <template>
      <el-table :data="tableData" border ref="multipleTable" @selection-change="handleSelectionChange" :size="GlobalCss.controlSize">
        <el-table-column type="selection" show-overflow-tooltip min-width="100" align="center"></el-table-column>
        <el-table-column prop="deviceCode" label="商品编号" show-overflow-tooltip min-width="100" align="center">
          <template slot-scope="scope">
            <el-link type="primary" @click="handleRowClick(scope.row)">{{scope.row.deviceCode}}</el-link>
          </template>
        </el-table-column>
        <el-table-column prop="deviceName" label="商品名称" show-overflow-tooltip min-width="100" align="center"></el-table-column>
        <el-table-column prop="wareHouseCode" label="入库编号" show-overflow-tooltip min-width="100" align="center"></el-table-column>
        <el-table-column prop="wareHouseName" label="所在仓库" show-overflow-tooltip min-width="100" align="center"></el-table-column>
        <el-table-column prop="norm" label="规格型号" show-overflow-tooltip min-width="100" align="center"></el-table-column>
        <el-table-column prop="materialType" label="物资类别" show-overflow-tooltip min-width="100" align="center"></el-table-column>
        <!-- <el-table-column prop="amount" label="数量" show-overflow-tooltip min-width="100" align="center"></el-table-column> -->
        <el-table-column prop="dummyAmount" label="数量" show-overflow-tooltip min-width="70" align="center"></el-table-column>
        <el-table-column prop="warningValue" label="预警值" show-overflow-tooltip min-width="70" align="center">
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              <el-tag type="danger" size="small">{{ scope.row.warningValue }}</el-tag>
            </div>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination style="text-align:right;" :current-page.sync="queryBean.page" :page-size="queryBean.size"
            @current-change="changePage" layout="total, prev, pager, next" :total="queryBean.totalCount"></el-pagination>
    </template>
     <el-dialog width="50%" title="商品详情" :visible.sync="innerVisible" append-to-body>
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
                      <el-form-item label="商品编码">
                        <span class="info-content">{{ form.deviceCode }}</span>
                      </el-form-item>
                      <el-form-item label="商品名称">
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

<template>
  <div>
    <div>
      <el-divider></el-divider>
      <el-steps :active="1" align-center style="margin-bottom:20px;">
        <el-step title="提交申请" description="张三2019-11-12"></el-step>
        <el-step title="负责人审批" description="这是一段很长很长很长的描述性文字"></el-step>
        <el-step title="主管审批" description="这是一段很长很长很长的描述性文字"></el-step>
        <el-step title="库管确认" description="这是一段很长很长很长的描述性文字"></el-step>
        <el-step title="申请成功" description="这是一段很长很长很长的描述性文字"></el-step>
      </el-steps>
    </div>
    <div style="margin-bottom:20px;">
      <div slot="header" style="text-align:left;">
        <span style="font-weight: bold;font-size: 14px;"><i class="el-icon-s-order"></i>领用基本信息</span>
      </div>
      <el-divider></el-divider>
      <div>
        <el-row>
          <el-col :span="12" v-for="(item, index) in partData" :key="index" style="line-height: 30px;">
            <span class="title">{{ item.title }}：</span><span class="content">{{ item.content }}</span>
          </el-col>
        </el-row>
      </div>
    </div>
    <div>
      <div slot="header" style="text-align:left;">
        <span style="font-weight: bold;font-size: 14px;"><i class="el-icon-s-order"></i>领用备件清单</span>
      </div>
      <el-divider></el-divider>
      <el-table :data="gridData" :size="GlobalCss.buttonSize" stripe border>
        <el-table-column type="index" width="50"></el-table-column>
        <el-table-column property="date" label="设备编号"></el-table-column>
        <el-table-column property="name" label="设备名称"></el-table-column>
        <el-table-column property="name" label="设备型号"></el-table-column>
        <el-table-column property="name" label="设备类型"></el-table-column>
        <el-table-column property="name" label="设备品牌"></el-table-column>
        <el-table-column property="address" label="地址"></el-table-column>
      </el-table>
    </div>
    <div style="text-align: center;margin-top:10px;">
      <el-button size="small" type="primary" @click="handleBack">返 回</el-button>
      <el-button size="small" @click="handlePrint">打 印</el-button>
    </div>
  </div>
</template>
<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import './styles.scss';
</style>

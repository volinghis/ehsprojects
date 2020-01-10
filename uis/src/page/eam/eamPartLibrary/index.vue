<template>
    <!-- <el-card :bordered="false" > -->
      <div style="padding:0 24px;">
        <div>
        <el-form :inline="true" :model="queryParam" size="small" label-position="left">
            <el-row :gutter="48">
            <el-col :md="8" :sm="24">
                <el-form-item label="备件编号：">
                    <el-input v-model="queryParam.code" placeholder="请输入备件编号" style="width: 300px;"/>
                </el-form-item>
            </el-col>
            <el-col :md="8" :sm="24">
                <el-form-item label="备件名称：">
                    <el-input v-model="queryParam.name" placeholder="请输入备件名称" style="width: 300px;"/>
                </el-form-item>
            </el-col>
            <template v-if="advanced">
                <el-col :md="8" :sm="24">
                    <el-form-item label="调用次数：">
                        <el-input  placeholder="" style="width: 300px;"/>
                    </el-form-item>
                </el-col>
                <el-col :md="8" :sm="24">
                    <el-form-item label="更新日期：">
                        <el-input  placeholder="" style="width: 300px;"/>
                    </el-form-item>
                </el-col>
                <el-col :md="8" :sm="24">
                    <el-form-item label="使用状态：">
                        <el-input  placeholder="" style="width: 300px;"/>
                    </el-form-item>
                </el-col>
                <el-col :md="8" :sm="24">
                    <el-form-item label="使用状态：">
                        <el-input  placeholder="" style="width: 300px;"/>
                    </el-form-item>
                </el-col>
            </template>
            <el-col :md="!advanced && 8 || 24" :sm="24">
                <span class="table-page-search-submitButtons" :style="advanced && { float: 'right', overflow: 'hidden' } || {} " >
                    <el-button type="primary" @click="$refs.table.refresh(true)" size="small">查询</el-button>
                    <el-button style="margin-left: 8px" @click="() => queryParam = {}" size="small">重置</el-button>
                    <a @click="toggleAdvanced" style="margin-left: 8px" >
                        <span style="font-size:small;color:#409EFF">{{ advanced ? '收起' : '展开' }}
                            <i :class="advanced ? 'el-icon-arrow-up' : 'el-icon-arrow-down'"/>
                        </span>
                    </a>
                </span>
            </el-col>
            </el-row>
        </el-form>
        </div>
        <div class="table-operator">
            <el-button type="primary" icon="el-icon-plus" style="float:left;margin-bottom: 5px;" size="small" @click="handleAdd()">新增</el-button>
            <el-button type="primary" icon="el-icon-delete" style="float:left;margin-bottom: 5px;" size="small" @click="handleAdd()">删除</el-button>
        </div>
        <template>
            <el-table :data="tableData" ref="multipleTable">
                <el-table-column fixed type="index" width="50"> </el-table-column>
                <el-table-column fixed type="selection" width="50"> </el-table-column>
                <el-table-column fixed prop="type" label="图片" width="70">
                   <el-image style="width: 30px; height: 30px" :src="url" :fit="fit" :preview-src-list="srcList"></el-image>
                </el-table-column>
                <el-table-column fixed prop="code" label="备件编号" width="150"> </el-table-column>
                <el-table-column fixed prop="name" label="备件名称" width="120"> </el-table-column>
                <el-table-column fixed prop="completion" label="资料完整度" width="180" sortable>
                    <template slot-scope="scope">
                        <el-progress :percentage="scope.row.completion" :color="customColors" ></el-progress>
                    </template>
                </el-table-column>
                <el-table-column prop="type" label="备件类型" width="100"> </el-table-column>
                <el-table-column prop="model" label="备件型号" width="100"> </el-table-column>
                <el-table-column prop="norm" label="备件规格" width="120"> </el-table-column>
                <el-table-column prop="brand" label="品牌" width="150"> </el-table-column>
                <el-table-column prop="warehouse" label="所在仓库" width="100"> </el-table-column>
                <el-table-column prop="unit" label="单位" width="80"> </el-table-column>
                <el-table-column prop="price" label="价格" width="80" sortable> </el-table-column>
                <el-table-column prop="amount" label="数量" width="80" sortable> </el-table-column>
                <el-table-column prop="warningValue" label="库存预警值" width="80"> </el-table-column>
                <el-table-column fixed="right" label="操作" width="150">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="primary" size="small">查看</el-button>
                    <el-button @click="handleEdit(scope.row)" type="primary" size="small">编辑</el-button>
                </template>
                </el-table-column>
            </el-table>
            <el-pagination
                style="text-align:right;"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page.sync="currentPage1"
                :page-sizes="[10, 20, 50]"
                :page-size="10"
                layout="sizes,total,prev, pager, next"
                :total="100">
            </el-pagination>
        </template>
      </div>
    <!-- </el-card> -->
</template>

<script>
export default {
  methods: {
    handleAdd () {
      this.$router.push({ name: 'partBaseForm' })
    },
    handleClick (row) {
      this.$router.push({ name: 'partDetails' })
    },
    handleEdit (row) {
      this.$router.push({ name: 'partEdit' })
    },
    toggleAdvanced () {
      this.advanced = !this.advanced
    },
    handleSizeChange (val) {
    },
    handleCurrentChange (val) {
    }
  },
  data () {
    return {
      fit: 'cover',
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      srcList: ['https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'],
      currentPage1: 1,
      // 高级搜索 展开/关闭
      advanced: false,
      // 查询参数
      queryParam: {},
      customColor: '#409eff',
      customColors: [
        { color: '#f56c6c', percentage: 20 },
        { color: '#e6a23c', percentage: 40 },
        { color: '#5cb87a', percentage: 60 },
        { color: '#1989fa', percentage: 80 },
        { color: '#6f7ad3', percentage: 100 }
      ],
      tableData: [{
        code: 'FM-0001',
        name: '阀门',
        completion: 80,
        type: '阀门',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'QYFM-0001',
        name: '气压阀门',
        completion: 60,
        type: '气压阀门',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'FDJ-0001',
        name: '发电机',
        completion: 100,
        type: '发电机',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'FM-0002',
        name: '阀门',
        completion: 70,
        type: '阀门',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'FM-0002',
        name: '阀门',
        completion: 70,
        type: '阀门',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'FM-0002',
        name: '阀门',
        completion: 70,
        type: '阀门',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'GL-0002',
        name: '锅炉',
        completion: 90,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }]
    }
  }
}
</script>
<style>
  .el-table th>.cell{
    text-align: center;
  }
  .el-table td div {
    text-align: center;
  }
</style>

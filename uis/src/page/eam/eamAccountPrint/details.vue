<template>
  <div class="container">
    <el-row :gutter="20">
      <el-col :md="24"
              :lg="6">
        <el-card shadow="hover">
          <div slot="header"
               class="devDetailsTitle item-title"
               size="small">
            <span><i class="el-icon-s-tools">设备详情</i></span>
            <el-button style="float: right; padding: 3px 0"
                       type="text">编辑</el-button>
          </div>
          <div class="accountPrints">
            <el-row>
              <el-col>
                <el-image style="width: 150px; height: 150px"
                          src="https://i.loli.net/2019/11/07/2STd8zUw6x5hWaM.jpg"
                          fit="fill"
                          :preview-src-list="srcList"></el-image>
              </el-col>
              <el-col>
                <span>资料完整度：<el-progress :percentage="80"></el-progress></span>
              </el-col>
              <el-col>
                <span>设备编号：101111111</span>
              </el-col>
              <el-col>
                <span>设备名称：阀门</span>
              </el-col>
              <el-col>
                <span>生产厂家：西安电气设备有限公司</span>
              </el-col>
              <el-col>
                <span>供应商：东恒</span>
              </el-col>
              <el-col>
                <span>设备状态：正常</span>
              </el-col>
              <el-col>
                <span>规格型号：FM-SDS</span>
              </el-col>
              <el-col>
                <span>采购价格：20万</span>
              </el-col>
              <el-col>
                <span>采购时间：2019/10/5</span>
              </el-col>
              <el-col>
                <span>启用日期：2019/11/4</span>
              </el-col>
              <el-col>
                <span>安装位置：汽机专业</span>
              </el-col>
              <el-col>
                <span>负责人：张三</span>
              </el-col>
              <el-col>
                <span>备注：无</span>
              </el-col>
            </el-row>
          </div>
        </el-card>
      </el-col>
      <el-col :md="24"
              :lg="18">
        <el-card style="margin-bottom:12px;"
                 shadow="hover">
          <div slot="header"
               class="partTitle item-title">
            <span><i class="el-icon-s-cooperation"></i>关联备件</span>
          </div>
          <el-table :data="partData"
                    style="width: 100%"
                    :highlight-current-row="true">
            <el-table-column type="index"
                             width="50"
                             fixed></el-table-column>
            <el-table-column prop="deviceCode"
                             label="备件编码"
                             width="100"
                             fixed></el-table-column>
            <el-table-column prop="deviceName"
                             label="备件名称"
                             width="100"
                             fixed></el-table-column>
            <el-table-column prop="complete"
                             label="资料完整度"
                             width="150">
              <template slot-scope="scope">
                <el-progress :percentage="scope.row.complete"
                             :color="customColorMethod"></el-progress>
              </template>
            </el-table-column>
            <el-table-column prop="brand"
                             label="品牌"></el-table-column>
            <el-table-column prop="norm"
                             label="规格"></el-table-column>
            <el-table-column prop="warehouse"
                             label="所在仓库"></el-table-column>
            <el-table-column prop="price"
                             label="价格"></el-table-column>
            <el-table-column prop="amount"
                             label="数量"></el-table-column>
            <el-table-column prop="manufacturer"
                             label="生产商"
                             width="150"></el-table-column>
            <el-table-column prop="supplier"
                             label="供应商"></el-table-column>
            <el-table-column prop="materialTypeName"
                             label="物资类型"></el-table-column>
            <el-table-column prop="labelCode"
                             label="标签码"
                             width="150"></el-table-column>
            <el-table-column prop="warningValue"
                             label="预警值"></el-table-column>
          </el-table>
        </el-card>

        <el-row :gutter="10">
          <el-col :md="24"
                  :lg="14">
            <el-card shadow="hover">
              <div slot="header"
                   class="repairTitle item-title">
                <span><i class="el-icon-s-order"></i>检修记录</span>
              </div>
              <el-table :data="tableData"
                        style="width: 100%">
                <el-table-column prop="date"
                                 label="检修日期"></el-table-column>
                <el-table-column prop="name"
                                 label="姓名"></el-table-column>
                <el-table-column prop="address"
                                 label="检修内容"
                                 width="180"
                                 :show-overflow-tooltip="true"></el-table-column>
              </el-table>
            </el-card>
          </el-col>
          <el-col :md="24"
                  :lg="10">
            <el-card shadow="hover">
              <div slot="header"
                   class="modifyTitle item-title">
                <span><i class="el-icon-s-claim"></i>资料纠错/补全记录</span>
              </div>
              <el-table :data="modifyData"
                        style="width: 100%">
                <el-table-column prop="name"
                                 label="修改人">
                  <template slot-scope="scope">
                    <a href="#"
                       style="text-decoration:none;">{{ scope.row.name }}</a>
                    <i class="el-icon-medal"></i>
                  </template>
                </el-table-column>
                <el-table-column prop="modifyContent"
                                 label="修改内容"
                                 width="180"></el-table-column>
                <el-table-column prop="contribute"
                                 label="贡献度"
                                 width="180">
                  <template slot-scope="scope">
                    <el-rate v-model="scope.row.contribute"
                             :allow-half="true"
                             disabled
                             show-score
                             text-color="#ff9900"
                             score-template="{value}"></el-rate>
                  </template>
                </el-table-column>
              </el-table>
            </el-card>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data () {
    return {
      srcList: [
        'https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg',
        'https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg'
      ],
      partData: [{
        deviceCode: '10000001',
        deviceName: '阀门',
        complete: 10,
        brand: '川跃',
        norm: 'FM',
        warehouse: '一号仓库',
        price: '0.2万',
        amount: '5',
        manufacturer: '西安电气设备公司',
        supplier: '东恒',
        materialTypeName: '生产设备',
        labelCode: '20181002001',
        warningValue: '1'
      }, {
        deviceCode: '10000001',
        deviceName: '阀门',
        complete: 50,
        brand: '川跃',
        norm: 'FM 15',
        warehouse: '一号仓库',
        price: '0.2万',
        amount: '5',
        manufacturer: '西安电气设备公司',
        supplier: '东恒',
        materialTypeName: '生产设备',
        labelCode: '20181002001',
        warningValue: '1'
      }, {
        deviceCode: '10000001',
        deviceName: '阀门',
        complete: 80,
        brand: '川跃',
        norm: 'FM',
        warehouse: '一号仓库',
        price: '0.2万',
        amount: '5',
        manufacturer: '西安电气设备公司',
        supplier: '东恒',
        materialTypeName: '生产设备',
        labelCode: '20181002001',
        warningValue: '1'
      }, {
        deviceCode: '10000001',
        deviceName: '阀门',
        complete: 100,
        brand: '上海一恒',
        norm: 'FM',
        warehouse: '一号仓库',
        price: '0.2万',
        amount: '5',
        manufacturer: '西安电气设备公司',
        supplier: '东恒',
        materialTypeName: '生产设备',
        labelCode: '20181002001',
        warningValue: '1'
      }],
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-04',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1517 弄',
        zip: 200333
      }, {
        date: '2016-05-01',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1519 弄',
        zip: 200333
      }, {
        date: '2016-05-03',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1516 弄',
        zip: 200333
      }],
      modifyData: [{
        date: '2016-05-02',
        name: '王小虎',
        modifyContent: '2016-05-02 完善资料',
        contribute: 4.5
      }, {
        date: '2016-05-04',
        name: '王小龙',
        modifyContent: '2016-05-02 纠错',
        contribute: 2
      }, {
        date: '2016-05-01',
        name: '王小鼠',
        modifyContent: '2016-05-02 纠错',
        contribute: 5
      }, {
        date: '2016-05-03',
        name: '王小虎',
        modifyContent: '2016-05-02 完善设备资料',
        contribute: 3.7
      }]
    }
  },
  methods: {
    customColorMethod (percentage) {
      if (percentage < 30) {
        return '#909399'
      } else if (percentage < 70) {
        return '#e6a23c'
      } else {
        return '#67c23a'
      }
    }
  }
}

</script>

<style scoped>
.item-title {
  text-align: left;
  font-weight: bold;
}
.accountPrints {
  margin-top: 15px;
  margin-left: 15px;
  font-size: 14px;
  text-align: left;
  line-height: 26px;
}

.el-card__header {
  background-color: whitesmoke;
  padding: 10px 20px;
}
</style>

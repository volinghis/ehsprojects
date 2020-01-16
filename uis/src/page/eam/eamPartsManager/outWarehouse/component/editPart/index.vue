<template>
  <div style="margin-right:20px;">
    <el-form :model="form"
             :rules="rules"
             :size="GlobalCss.buttonSize"
             ref="form"
             label-width="100px">
      <el-form-item label="备件编号：">
        <el-input v-model="form.deviceCode"
                  disabled></el-input>
      </el-form-item>
      <el-form-item label="备件名称：">
        <el-input v-model="form.deviceName"
                  disabled></el-input>
      </el-form-item>
      <el-form-item label="规格型号：">
        <el-input v-model="form.norm"
                  disabled></el-input>
      </el-form-item>
      <el-form-item label="出厂编号：">
        <el-input v-model="form.leaveFactoryCode"
                  placeholder="请填写出厂编号"
                  disabled></el-input>
      </el-form-item>
      <el-form-item label="出厂日期：">
        <el-date-picker v-model="form.leaveFactoryDate"
                        type="date"
                        disabled
                        placeholder="请选择出厂日期"
                        @blur="factoryDateBlur($event)"
                        style="width:100%"></el-date-picker>
      </el-form-item>
      <el-form-item label="供应商：">
        <el-input v-model="form.supplier"
                  placeholder="请填写供应商"
                  disabled></el-input>
      </el-form-item>
      <el-form-item label="数量："
                    prop="amount">
        <el-input v-model="form.amount"
                  @blur="amountBlur($event)"
                  placeholder="请输入数量"></el-input>
      </el-form-item>
      <el-form-item label="单价："
                    prop="price">
        <el-input v-model="form.price"
                  disabled
                  placeholder="请输入单价"></el-input>
      </el-form-item>
      <el-form-item label="单位："
                    prop="unit">
        <el-input v-model="form.unit"
                  disabled
                  placeholder="请输入单位"></el-input>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  data () {
    return {
      flag: '',
      amountOld: 0,
      amountNew: 0,
      totalPriceNew: 0,
      form: {
        deviceName: '',
        deviceCode: '',
        norm: '',
        leaveFactoryCode: '',
        leaveFactoryDate: '',
        supplier: '',
        price: 0,
        amount: 0,
        unit: '',
        totalPrice: 0
      },
      rules: {
        price: [
          { required: true, message: '请输入价格', trigger: 'blur' },
          { type: 'number', message: '请输入正确数字', trigger: 'blur', transform: (value) => Number(value) }
        ],
        unit: [
          { required: true, message: '请输入单位（例如：‘个’）', trigger: 'blur' },
          { min: 1, max: 3, message: '长度在 1 到 3 个字符', trigger: 'blur' }
        ],
        amount: [
          { required: true, message: '请输入数量', trigger: 'blur' },
          { type: 'number', message: '请输入正确数字', trigger: 'blur', transform: (value) => Number(value) }
        ]
      }
    }
  },
  props: {
    partsForm: Object,
    flagMark: String
  },
  watch: {
    flagMark: function (val) {
      this.flag = val
    },
    amountNew: function (val, old) {
      if (this.flag === 'add') {
        if (this.amountOld < val) {
          this.$message.error('您输入的数量已经大于库存，请重新输入')
          this.form.amount = ''
        } else if (this.amountOld === val) {
          this.$message({
            message: '此备件本次全部领用，请尽快采购',
            type: 'warning'
          })
        } else {
          console.log('this.form.price==' + this.form.price)
          this.totalPriceNew = this.form.price * val
          console.log('this.form.totalPriceNew==' + this.totalPriceNew)
        }
      } else if (this.flag === 'edit') {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamOutWarehouse/validAmount', { params: { amount: this.amountOld, deviceCode: this.form.deviceCode, price: this.form.price } }).then(res => {
          console.log('amount====' + res.data)
          if (res.data < val) {
            this.$message.error('您输入的数量已经大于库存，请重新输入')
            this.form.amount = ''
          } else if (res.data === val) {
            this.$message({
              message: '此备件本次全部领用，请尽快采购',
              type: 'warning'
            })
          } else {
            this.totalPriceNew = this.form.price * val
          }
        })
      }
    },
    totalPriceNew: function (val) {
      this.form.totalPrice = val
    },
    factoryDateBlur: function (val) {
      this.form.leaveFactoryDate = val
    },
    partsForm: {
      handler (val) {
        // this.form = val
        this.form = JSON.parse(JSON.stringify(val))
        console.log(this.form.amount)
        this.amountOld = this.form.amount
        // this.partsForm = val
        // this.$nextTick(() => {
        //   this.form = val
        // })
      }
    }
  },
  mounted: function () {
    JSON.parse(JSON.stringify(this.partsForm))
    this.form = this.partsForm
    this.flag = this.flagMark
  },
  methods: {
    amountBlur: function (e) {
      this.amountNew = e.target.value
    },
    factoryDateBlur: function (e) {
      this.partsForm.leaveFactoryDate = e.displayValue
    }
  }
}
</script>
<style lang="scss" scoped>
</style>

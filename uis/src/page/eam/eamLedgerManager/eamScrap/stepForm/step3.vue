<template>
  <div>
    <!-- <template>
      <el-form :model="personForm"
               :rules="rules"
               ref="personForm"
               class="eamScrapStep3"
               label-width="200px">
        <el-form-item label="部门负责人："
                      prop="principal">
          <UserSelector style="width:60%"
                        @change="handleChange"
                        ref="userSelect" />
        </el-form-item>
        <el-form-item label="部门主管："
                      prop="generalManager">
          <UserSelector style="width:60%" />
        </el-form-item>
        <el-form-item label="库管："
                      prop="warehouseManager">
          <UserSelector style="width:60%" />
        </el-form-item>
      </el-form>
    </template> -->
    <!-- <div style="text-align:center;">
      <el-button @click="prevStep"
                 style="margin:10px;"
                 :size="GlobalCss.buttonSize">上一步</el-button>
      <el-button type="primary"
                 @click="nextStep(personForm)"
                 style="margin:10px;"
                 :size="GlobalCss.buttonSize">提交</el-button>
    </div> -->
  </div>
</template>
<script>
// import UserSelector from '@/components/org/user-selector/index'
export default {
  name: 'partStepThree',
  components: {
  },
  data () {
    return {
      personForm: {
        principal: '',
        generalManager: '',
        warehouseManager: ''
      },
      rules: {
        // principal: [
        //   { required: true, message: '请选择负责人', trigger: 'change' }
        // ]
        // generalManager: [
        //   { required: true, message: '请选择总经理', trigger: 'change' }
        // ],
        // warehouseManager: [
        //   { required: true, message: '请选择库管', trigger: 'change' }
        // ]
      },
      value: []
    }
  },
  methods: {
    prevStep: function () {
      this.$emit('prevStep')
    },
    handleChange () {
      const node = this.$refs.userSelect.getCheckedNodes()[0]
      this.personForm.principal = node.data.label
    },
    nextStep: function (personForm) {
      this.$refs.personForm.validate((valid) => {
        if (valid) {
          this.$emit('nextStep', this.personForm.principal)
        } else {
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.eamScrapStep3 {
  width: 50%;
  position: relative;
  left: 28%;
}
.el-textarea {
  padding: 0px;
}
/deep/ .el-form-item {
  margin-bottom: 22px;
}
</style>

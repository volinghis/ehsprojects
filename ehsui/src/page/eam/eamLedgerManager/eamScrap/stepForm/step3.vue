<template>
  <div>
    <template>
      <el-form :model="scrapStepForm" :rules="rules" ref="scrapStepForm" class="eamScrapStep3" label-width="200px" >
        <el-form-item label="部门负责人：" prop="principal">
          <el-cascader v-model="scrapStepForm.principal"  :options="options" :props="{ expandTrigger: 'hover' }" @change="handleChange" filterable></el-cascader>
        </el-form-item>
        <el-form-item label="部门主管：" prop="generalManager">
          <el-cascader v-model="scrapStepForm.generalManager" :options="options" :props="{ expandTrigger: 'hover' }" @change="handleChange" filterable  ></el-cascader>
        </el-form-item>
        <el-form-item label="库管：" prop="warehouseManager">
          <el-cascader v-model="scrapStepForm.warehouseManager" :options="options" :props="{ expandTrigger: 'hover' }" @change="handleChange" filterable  ></el-cascader>
        </el-form-item>
      </el-form>
    </template>
    <div style="text-align:center;">
      <el-button @click="prevStep" style="margin:10px;" :size="GlobalCss.buttonSize">上一步</el-button>
      <el-button type="primary" @click="nextStep(scrapStepForm)" style="margin:10px;" :size="GlobalCss.buttonSize">下一步</el-button>
    </div>
  </div>
</template>
<script>
export default {
  name: 'partStepThree',
  data () {
    return {
      scrapStepForm: {
        principal: '',
        generalManager: '',
        warehouseManager: ''
      },
      rules: {
        principal: [
          { required: true, message: '请选择负责人', trigger: 'change' }
        ],
        generalManager: [
          { required: true, message: '请选择总经理', trigger: 'change' }
        ],
        warehouseManager: [
          { required: true, message: '请选择库管', trigger: 'change' }
        ]
      },
      value: [],
      options: [{
        value: 'zhinan',
        label: '指南',
        children: [{
          value: 'shejiyuanze',
          label: '设计原则',
          children: [{
            value: 'yizhi',
            label: '一致'
          }, {
            value: 'fankui',
            label: '反馈'
          }, {
            value: 'xiaolv',
            label: '效率'
          }, {
            value: 'kekong',
            label: '可控'
          }]
        }, {
          value: 'daohang',
          label: '导航',
          children: [{
            value: 'cexiangdaohang',
            label: '侧向导航'
          }, {
            value: 'dingbudaohang',
            label: '顶部导航'
          }]
        }]
      }, {
        value: 'zujian',
        label: '组件',
        children: [{
          value: 'basic',
          label: 'Basic',
          children: [{
            value: 'layout',
            label: 'Layout 布局'
          }, {
            value: 'color',
            label: 'Color 色彩'
          }, {
            value: 'typography',
            label: 'Typography 字体'
          }, {
            value: 'icon',
            label: 'Icon 图标'
          }, {
            value: 'button',
            label: 'Button 按钮'
          }]
        }, {
          value: 'form',
          label: 'Form',
          children: [{
            value: 'radio',
            label: 'Radio 单选框'
          }, {
            value: 'checkbox',
            label: 'Checkbox 多选框'
          }, {
            value: 'input',
            label: 'Input 输入框'
          }, {
            value: 'input-number',
            label: 'InputNumber 计数器'
          }, {
            value: 'select',
            label: 'Select 选择器'
          }, {
            value: 'cascader',
            label: 'Cascader 级联选择器'
          }, {
            value: 'switch',
            label: 'Switch 开关'
          }, {
            value: 'slider',
            label: 'Slider 滑块'
          }, {
            value: 'time-picker',
            label: 'TimePicker 时间选择器'
          }, {
            value: 'date-picker',
            label: 'DatePicker 日期选择器'
          }, {
            value: 'datetime-picker',
            label: 'DateTimePicker 日期时间选择器'
          }, {
            value: 'upload',
            label: 'Upload 上传'
          }, {
            value: 'rate',
            label: 'Rate 评分'
          }, {
            value: 'form',
            label: 'Form 表单'
          }]
        }, {
          value: 'data',
          label: 'Data',
          children: [{
            value: 'table',
            label: 'Table 表格'
          }, {
            value: 'tag',
            label: 'Tag 标签'
          }, {
            value: 'progress',
            label: 'Progress 进度条'
          }, {
            value: 'tree',
            label: 'Tree 树形控件'
          }, {
            value: 'pagination',
            label: 'Pagination 分页'
          }, {
            value: 'badge',
            label: 'Badge 标记'
          }]
        }, {
          value: 'notice',
          label: 'Notice',
          children: [{
            value: 'alert',
            label: 'Alert 警告'
          }, {
            value: 'loading',
            label: 'Loading 加载'
          }, {
            value: 'message',
            label: 'Message 消息提示'
          }, {
            value: 'message-box',
            label: 'MessageBox 弹框'
          }, {
            value: 'notification',
            label: 'Notification 通知'
          }]
        }, {
          value: 'navigation',
          label: 'Navigation',
          children: [{
            value: 'menu',
            label: 'NavMenu 导航菜单'
          }, {
            value: 'tabs',
            label: 'Tabs 标签页'
          }, {
            value: 'breadcrumb',
            label: 'Breadcrumb 面包屑'
          }, {
            value: 'dropdown',
            label: 'Dropdown 下拉菜单'
          }, {
            value: 'steps',
            label: 'Steps 步骤条'
          }]
        }, {
          value: 'others',
          label: 'Others',
          children: [{
            value: 'dialog',
            label: 'Dialog 对话框'
          }, {
            value: 'tooltip',
            label: 'Tooltip 文字提示'
          }, {
            value: 'popover',
            label: 'Popover 弹出框'
          }, {
            value: 'card',
            label: 'Card 卡片'
          }, {
            value: 'carousel',
            label: 'Carousel 走马灯'
          }, {
            value: 'collapse',
            label: 'Collapse 折叠面板'
          }]
        }]
      }, {
        value: 'ziyuan',
        label: '资源',
        children: [{
          value: 'axure',
          label: 'Axure Components'
        }, {
          value: 'sketch',
          label: 'Sketch Templates'
        }, {
          value: 'jiaohu',
          label: '组件交互文档'
        }]
      }]
    }
  },
  methods: {
    prevStep: function () {
      this.$emit('prevStep')
    },
    handleChange: function (value) {
      console.log(value)
    },
    nextStep: function (scrapStepForm) {
      this.$refs.scrapStepForm.validate((valid) => {
        if (valid) {
          console.log('submit!')
          this.$emit('nextStep')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
  .eamScrapStep3{
    width: 50%;
    position: relative;
    left: 28%;
  }
  .el-textarea{
    padding: 0px;
  }
  /deep/ .el-form-item {
    margin-bottom: 22px;
  }
</style>

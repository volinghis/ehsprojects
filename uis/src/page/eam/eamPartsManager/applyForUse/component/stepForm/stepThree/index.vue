<template>
  <div>
    <el-divider content-position="left"><span style="font-size:xx-small;color:#409EFF;">审批人信息</span></el-divider>
    <template>
      <el-form :model="emaPartLibrary" :rules="rules" ref="emaPartLibrary" status-icon class="table" label-width="100px" :size="GlobalCss.buttonSize">
        <el-form-item label="负责人：" prop="principal">
          <el-cascader v-model="emaPartLibrary.principal" :options="options" :props="{ expandTrigger: 'hover' }" @change="handleChange" filterable  placeholder="试试搜索：指南"></el-cascader>
          <!-- <user  v-model="emaPartLibrary.principal"></user> -->
        </el-form-item>
        <el-form-item label="主管：" prop="generalManager">
          <el-cascader v-model="emaPartLibrary.generalManager" :options="options" :props="{ expandTrigger: 'hover' }" @change="handleChange" filterable  placeholder="试试搜索：指南"></el-cascader>
          <!-- <user v-model="emaPartLibrary.generalManager"></user> -->
        </el-form-item>
        <el-form-item label="库管：" prop="warehouseManager">
          <el-cascader v-model="emaPartLibrary.warehouseManager" :options="options" :props="{ expandTrigger: 'hover' }" @change="handleChange" filterable  placeholder="试试搜索：指南"></el-cascader>
          <!-- <user v-model="emaPartLibrary.warehouseManager" ></user> -->
        </el-form-item>
      </el-form>
    </template>
    <el-divider></el-divider>
    <div style="text-align:center;">
      <el-button @click="prevStep" style="margin:10px;" :size="GlobalCss.buttonSize">上一步</el-button>
      <el-button type="primary" @click="nextStep(emaPartLibrary)" style="margin:10px;" :size="GlobalCss.buttonSize">提交</el-button>
    </div>
  </div>
</template>
<script>
// import user from '@components/org/user-selector/index.vue'
export default {
  name: 'partStepOne',
  components: {
    // 'user': user
  },
  data () {
    return {
      emaPartLibrary: {
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
      this.$message({
        message: '恭喜你，这是一条成功消息',
        type: 'success'
      })
    },
    nextStep: function (emaPartLibrary) {
      this.$refs.emaPartLibrary.validate((valid) => {
        if (valid) {
          this.$message({
            message: '恭喜你，这是一条成功消息',
            type: 'success'
          })
          this.$emit('nextStep')
        } else {
          this.$message.error('错了哦，这是一条错误消息')
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
  .table{
    width: 100%;
    position: relative;
    left: 28%;
  }
</style>

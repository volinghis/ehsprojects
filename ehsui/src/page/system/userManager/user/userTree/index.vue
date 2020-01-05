<template>
  <div class="block">
    <el-tree :data="treeData"
             show-checkbox
             node-key="id"
             ref="tree"
             :props="treeProps"
             default-expand-all
             @node-click="handleNodeClick"
             highlight-current
             auto-expand-parent
             :expand-on-click-node="false">
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span v-show="!node.isEdit">
          <div>
            <span :class="[data.id >= maxexpandId ? 'slot-t-node--label' : '']">{{ node.label }}</span>
            <span class="buttonStyle">
              <el-button type="text"
                         :size="GlobalCss.controlSize"
                         @click="() => addNode(node,data)">
                <i class="fa fa-plus-square fa-lg"
                   style="color:#67C23A"></i>
              </el-button>
              <el-button type="text"
                         :size="GlobalCss.controlSize"
                         @click="() => editNode(node,data)">
                <i class="fa fa-pencil-square fa-lg"></i>
              </el-button>
              <el-button type="text"
                         :size="GlobalCss.controlSize"
                         @click="() => removeNode(node, data)">
                <i class="fa fa-minus-square fa-lg"
                   style="color:#F56C6C"></i>
              </el-button>
            </span>
          </div>
        </span>
        <!-- 编辑输入框 -->
        <span v-show="node.isEdit">
          <el-input class="slot-t-input"
                    :size="GlobalCss.controlSize"
                    autofocus
                    v-model="data.label"
                    :ref="'slotTreeInput'+data.id"
                    @blur.stop="NodeBlur(node, data)"
                    @keyup.enter.native="NodeBlur(node, data)"></el-input>
        </span>
      </span>
    </el-tree>
  </div>
</template>
<script>
import datas from './datas.js'
export default datas
</script>

<style lang="scss" scoped>
@import "./styles.scss";
</style>

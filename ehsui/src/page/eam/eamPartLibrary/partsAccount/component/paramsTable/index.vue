<template>
<div class="tableClass">
  <el-row>
     <el-col :span="24">
       <el-table size="mini" border :data="master_user.data" style="width: 100%" highlight-current-row>
         <el-table-column type="index" align="center"></el-table-column>
         <el-table-column v-for="(item,index) in master_user.columns" :label="item.label" :prop="item.prop" :width="item.width" :key="index" align="center">
           <template slot-scope="scope">
             <span v-if="scope.row.isSet">
               <el-input size="mini" placeholder="请输入内容" v-model="master_user.sel[item.prop]"></el-input>
             </span>
             <span v-else>{{scope.row[item.prop]}}</span>
           </template>
         </el-table-column>
         <el-table-column label="操作" width="150" align="center">
           <template slot-scope="scope">
              <span class="el-tag el-tag--success el-tag--mini" style="cursor: pointer;" @click.stop="saveRow(scope.row,scope.$index)">
                 <!-- {{scope.row.isSet?'保存':"修改"}} -->保存
              </span>
              <span class="el-tag el-tag--primary el-tag--mini" style="cursor: pointer;" @click="editRow(scope.row,scope.$index)">
                编辑
              </span>
              <span class="el-tag el-tag--danger el-tag--mini" style="cursor: pointer;" @click="deleteRow(scope.$index,master_user.data)">
                删除
              </span>
           </template>
         </el-table-column>
       </el-table>
     </el-col>
     <el-col :span="24">
       <div class="el-table-add-row" style="width: 100%;color:#409EFF" @click="add()"><span>+ 添加参数</span></div>
     </el-col>
   </el-row>
   <!-- <span>{{master_user.data}}</span> -->
</div>
</template>

<script>
import datas from './datas'
export default datas
</script>

<style lang="scss" scoped>
@import './styles.scss';
</style>

<template>
<div style="text-align: center;">
   <el-image :src="dhUrl" fit="fill" style="width:600px;height:600px;"></el-image>
</div>
</template>

<script>
export default {
  data () {
    return {
      dhUrl: require('@/assets/404.svg')
    }
  }
}
</script>

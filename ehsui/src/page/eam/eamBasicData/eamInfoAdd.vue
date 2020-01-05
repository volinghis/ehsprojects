<template>
  <div class="eam-add" >
    <div class="title">设备基础信息录入</div>
    <div class="container">
      <ul>
        <li>
          <span>新增设备</span>
          <el-image
            src="https://fuss10.elemecdn.com/3/28/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg"
          ></el-image>
        </li>
        <li>
          <span>新增备件</span>
          <el-image
            src="https://fuss10.elemecdn.com/9/bb/e27858e973f5d7d3904835f46abbdjpeg.jpeg"
          ></el-image>
        </li>
        <li>
          <span>资料录入</span>
          <el-image
            src="https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg"
          ></el-image>
        </li>
      </ul>
    </div>
  </div>
</template>
<style  lang="scss" scoped>
ul,
ul li {
  list-style: none;
  padding: 0;
  margin: 0;
}
ul li {
  float: left;
  width: 280px;
  height: 350px;
  overflow: hidden;
  position: relative;
}
.el-image {
  height: 100%;
  width: auto;
  position: relative;
  opacity: 0.7;
}
.el-image:hover{
  opacity: 1;
  cursor: pointer;
}
span{
  font-size: 24px;
  position:absolute;
  z-index: 1;
  color: white;
  margin-top: 150px;
  margin-left:88px;
}
.title{
  font-weight: 700;
  text-align: center;
  font-size: 48px;
  letter-spacing: 6px;
  color: #2db7f5;
}
.eam-add{
  position:absolute;
  left: 30%;
  top: 30%;
}

.container{
  margin-top:30px;
}
</style>

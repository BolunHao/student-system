<template>
  <div class="header">
   
  </div>
</template>

<script>
export default {
  name: 'AppHeader'
}
</script>

<style>
/* body stylings */
body {
    padding: 0;
    margin: 0;
    position: relative;
    min-height: 100vh;
    
}

.header {
  background-color: #f2f2f2;
  padding: 20px;
  display: flex;
  justify-content: center; /* 水平居中对齐 */
  align-items: center; /* 垂直居中对齐 */
  position: relative; /* 使得 logo 可以绝对定位 */
  flex-direction: column; /* 设置为竖直排列 */

}

.logo {
  position: absolute; /* 绝对定位 */
  right: 0; 
}

.logo-image {
  width: 100px; /* 设置 logo 图片宽度 */
}

.avatar-link {
  position: relative; /* 相对定位 */
}

.avatar {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}

.name-link {
  font-size: 18px;
  color: #333;
  text-decoration: none;
  margin-top: 10px; /* 姓名与头像间距 */
}
</style>

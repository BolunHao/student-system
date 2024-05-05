<template>
  <div id="app">
    <div class="content">
      <div class="sidebar">
        <div class="logo">
          <img src="./assets/dyson.png" alt="Logo" class="logo-image">
        </div>
        <div class="links">
          <router-link to="/courses" class="link">Courses</router-link>
          <router-link to="/selection" class="link">Courses Selection</router-link>
          <router-link to="/programs" class="link">Programs</router-link>
          <router-link to="/timetable" class="link">Timetable</router-link>
          <router-link to="/academic-guidance" class="link">Academic Guidance</router-link>
          <router-link to="/exam" class="link">Exam</router-link>
        </div>
        <div class="avatar-container">
          <router-link to="/profile" class="avatar-link">
            <img src="./assets/avatar.jpg" alt="Avatar" class="avatar">
          </router-link>
          <router-link to="/profile" class="name-link">John Doe</router-link>
        </div>
      </div>
      <div class="main-content">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App',
}
</script>

<style>
#app {
  display: flex;
  flex-direction: column; /* 垂直布局 */
  align-items: stretch; /* 拉伸以填充容器 */

}
.main-content {

}
.content {
  display: flex;
  flex: 1; /* 让内容部分占据剩余空间 */
}

.sidebar {
  background-color: black; /* 将背景色设为黑色 */
  color: white; /* 将文字颜色设为白色 */
  padding: 30px;
  min-width: 150px; /* 设置侧边栏宽度 */
  height: 100vh; /* 让导航栏高度布满页面 */
}

.links {
  margin-bottom: 30px; /* 调整导航链接和头像之间的垂直间距 */
}

.link {
  font-size: 20px;
  margin-bottom: 10px; /* 调整链接之间的垂直间距 */
  display: block; /* 使链接占据一整行 */
  text-decoration: none;
  color: white;
  margin-top : 20%;
}


.avatar-container {
  display: flex;
  flex-direction: column; /* 垂直排列 */
  align-items: center; /* 水平居中 */
  /* 将头像和姓名推到页面最底部 */
  margin-top: 100%; /* 使得头像和姓名推到页面最底部 */

  margin-bottom: 20px; /* 调整头像和页面底部的垂直间距 */
}

.avatar-link {
  text-decoration: none;
}
.logo {
  display: flex;
  margin-left : 50px;
  width :50px;
  height: 50px;
}

.avatar {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}

.name-link {
  font-size: 18px;
  text-decoration: none;
  margin-top: 10px; /* 姓名与头像间距 */
  color : white;
}
</style>

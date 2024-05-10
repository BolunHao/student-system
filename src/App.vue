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
  flex-direction: column; 
  align-items: stretch; 

}
.content {
  display: flex;
  flex: 1; 
}

.sidebar {
  background-color: black; 
  color: white; 
  padding: 30px;
  min-width: 150px; 
  height: 100vh; 
}

.links {
  margin-bottom: 30px; 
}

.link {
  font-size: 20px;
  margin-bottom: 10px; 
  display: block; 
  text-decoration: none;
  color: white;
  margin-top : 20%;
}


.avatar-container {
  display: flex;
  flex-direction: column; 
  align-items: center; 
  
  margin-top: 100%; 

  margin-bottom: 20px; 
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
  margin-top: 10px; 
  color : white;
}
</style>

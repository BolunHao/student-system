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
  justify-content: center; /* Center align horizontally */
  align-items: center; /* Center vertically */
  position: relative; /* The logo can be absolutely positioned */
  flex-direction: column; /* Set to vertical */

}

.logo {
  position: absolute; /* Absolute positioning */
  right: 0; 
}

.logo-image {
  width: 100px; /* Set the logo image width */
}

.avatar-link {
  position: relative; /* Relative positioning */
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
  margin-top: 10px; /* Distance between name and profile picture */
}
</style>

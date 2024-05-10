<template>
  <div class="profile">
    <h2>Profile</h2>
    <!-- Upload Avatar button -->
    <div class="upload-button">
      <label for="avatar-upload" class="button">Upload Avatar</label>
      <input id="avatar-upload" type="file" accept="image/*" @change="handleAvatarUpload">
    </div>
    <!-- Add basic information here -->
    <p>Name: John Doe</p>
    <p>Email: johndoe@example.com</p>

    <!-- Add Change Status button -->
    <router-link to="/change-status" class="button">Change Status</router-link>

    <!-- Add Change Password button -->
    <router-link to="/change-password" class="button">Change Password</router-link>
  </div>
</template>

<script>
export default {
  name: 'Profile',
  methods: {
    handleAvatarUpload(event) {
      const file = event.target.files[0];
      // Check whether the file type is image
      if (!file.type.startsWith('image/')) {
        alert('Please select an image file.');
        return;
      }
      // To handle the logic of uploading avatars here, you can use third-party libraries or write your own upload logic
      console.log('Selected file:', file);
    }
  }
}
</script>

<style>
/* Import Google Fonts */
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans:ital,wght@0,100..900;1,100..900&display=swap');

/* profile style */
.profile {
  width: 400px;
  text-align: center;
  margin-top: 40%;
  margin-left: 120%;

}

/* Button style */
.button {
  flex: 0 0 calc(50% - 10px);
  margin-bottom: 20px;
  margin-right: 10px; 
  padding: 10px;
  border: 1px solid #ccc;
  box-sizing: border-box; /* Add the properties of the box model */
  background: rgb(239, 239, 239);
  color: black;
}

.button:hover {
  background-color: rgb(229, 229, 229);
}

/* Upload button style */
.upload-button {
  display: inline-block;
}

/* Hide upload button */
#avatar-upload {
  display: none;
}

</style>

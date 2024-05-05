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
      // 检查文件类型是否为图片
      if (!file.type.startsWith('image/')) {
        alert('Please select an image file.');
        return;
      }
      // 在这里处理上传头像的逻辑，可以使用第三方库或者自己写上传逻辑
      console.log('Selected file:', file);
    }
  }
}
</script>

<style>
/* 导入 Google Fonts */
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans:ital,wght@0,100..900;1,100..900&display=swap');

/* profile样式 */
.profile {
  width: 400px;
  text-align: center;
  margin-top: 40%;
  margin-left: 120%;

}

/* 按钮样式 */
.button {
  flex: 0 0 calc(50% - 10px);
  margin-bottom: 20px;
  margin-right: 10px; 
  padding: 10px;
  border: 1px solid #ccc;
  box-sizing: border-box; /* 添加盒模型的属性 */
  background: rgb(239, 239, 239);
  color: black;
}

.button:hover {
  background-color: rgb(229, 229, 229);
}

/* 上传按钮样式 */
.upload-button {
  display: inline-block;
}

/* 隐藏上传按钮 */
#avatar-upload {
  display: none;
}

</style>

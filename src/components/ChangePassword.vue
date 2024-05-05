<template>
    <div class="change-password">
      <h2>Change Password</h2>
  
      <!-- 修改密码表单 -->
      <form @submit.prevent="changePassword">
        <div class="form-group">
          <label for="current-password">Current Password:</label>
          <input type="password" id="current-password" v-model="currentPassword" required>
        </div>
        <div class="form-group">
          <label for="new-password">New Password:</label>
          <input type="password" id="new-password" v-model="newPassword" required>
        </div>
        <div class="form-group">
          <label for="confirm-password">Confirm New Password:</label>
          <input type="password" id="confirm-password" v-model="confirmPassword" required>
        </div>
        <button type="submit" class="button">Change Password</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    name: 'ChangePassword',
    data() {
      return {
        currentPassword: '',
        newPassword: '',
        confirmPassword: ''
      };
    },
    methods: {
      changePassword() {
        // 在这里处理修改密码逻辑
        console.log('Change Password clicked');
        console.log('Current Password:', this.currentPassword);
        console.log('New Password:', this.newPassword);
        console.log('Confirm Password:', this.confirmPassword);
      }
    }
  }
  </script>
  
  <style scoped>
  /* 导入 Google Fonts */
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans:ital,wght@0,100..900;1,100..900&display=swap');
  /* 新增的样式 */
  .change-password {
    text-align: center;
    width : 400px;
    margin-top: 20%;
    margin-left : 120%;

  }
  
  .form-group {
    margin-bottom: 20px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
  }
  
  input[type="password"] {
    width: 300px; 
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
  }
  
  button {
    background-color: #fefefe;
  border: 1px solid #888;
  }
  
  button:hover {
    background-color: #f2f2f2;
  }
  
  /* 原有的样式 */
  .course-detail {
    margin-top: 20px;
  }
  
  .panel-container {
    display: flex;
  }
  
  .left-panel {
    flex: 1;
  }
  
  .button-group {
    display: flex;
    flex-direction: column;
  }
  
  .button-group button {
    margin-bottom: 10px;
  }
  
  .right-panel {
    flex: 3;
    margin-left: 20px;
  }
  
  .content-item {
    width: 800px; /* 设置固定宽度 */
    overflow-x: auto; /* 添加水平滚动条 */
    overflow-y: auto; /* 添加垂直滚动条 */
  }
  
  .course-details h3 {
    margin-top: 30px;
  }
  
  .assignment-notification p {
    margin-bottom: 10px;
    text-align: left;
  }
  
  .assignment-notification p strong {
    font-weight: bold;
  }
  
  .assignment-notification button {
    padding: 8px 16px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  
  .assignment-notification button:hover {
    background-color: #0056b3;
  }
  
  .meeting-schedule table {
    width: 100%;
  }
  
  .meeting-schedule th,
  .meeting-schedule td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
  }
  
  .meeting-schedule th {
    background-color: #f2f2f2;
  }
  
  .meeting-schedule th:first-child {
    width: 50px; /* 时间列宽度 */
  }
  
  .meeting-schedule th:last-child {
    width: calc(100% - 50px); /* 课程列宽度 */
  }
  
  .meeting-schedule td:last-child {
    text-align: left; /* 课程列文本左对齐 */
  }
  
  select {
    margin-right: 10px;
  }
  </style>
  
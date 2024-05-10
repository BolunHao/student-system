<template>
  <!-- This section contains a Change Password form within a 'change-password' div. The form is designed to allow users to update their current password. It starts with a heading 'Change Password' for clarity. The form itself is structured into three parts: one for the current password, one for the new password, and another to confirm the new password. Each part is encapsulated in a 'form-group' div, which includes a label and a password input field. The input fields are bound to Vue data properties using the v-model directive, ensuring the form is reactive and the data entered by the user is immediately synchronized with the component's data. The form uses Vue's event modifier @submit.prevent to prevent the default form submission behavior, allowing the 'changePassword' method to handle the submission process. This method is expected to validate and process the password change request. A button at the end labeled 'Change Password' triggers the form submission. This setup ensures that the process is user-friendly and secure, requiring users to input their current password for verification before a new password can be set. -->
  <div class="change-password">
    <div class="change-password">
      <h2>Change Password</h2>
  
      <!-- Change Password Form -->
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
        // Handle the change password logic here.
        console.log('Change Password clicked');
        console.log('Current Password:', this.currentPassword);
        console.log('New Password:', this.newPassword);
        console.log('Confirm Password:', this.confirmPassword);
      }
    }
  }
  </script>
  
  <style scoped>
  /* Import Google Fonts */
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans:ital,wght@0,100..900;1,100..900&display=swap');
  /* Newly added styles. */
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
  
  /* original style */
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
    width: 800px; /* "set a fixed width */
    overflow-x: auto; /* add a horizontal scrollbar */
    overflow-y: auto; /* add a vertical scrollbar */
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
    width: 50px; /* time column width */
  }
  
  .meeting-schedule th:last-child {
    width: calc(100% - 50px); /* time column width */
  }
  
  .meeting-schedule td:last-child {
    text-align: left; /* course column text left-aligned */
  }
  
  select {
    margin-right: 10px;
  }
  </style>
  
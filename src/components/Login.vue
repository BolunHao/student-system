<template>
    <div class="login-container">
      <h2>Login</h2>
      <form @submit.prevent="submitLogin">
        <div class="form-group">
          <label for="username">Username:</label>
          <input type="text" id="username" v-model="credentials.username" required>
        </div>
        <div class="form-group">
          <label for="password">Password:</label>
          <input type="password" id="password" v-model="credentials.password" required>
        </div>
        <button type="submit">Login</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  export default {
    data() {
      return {
        credentials: {
          username: '',
          password: ''
        }
      };
    },
    methods: {
   submitLogin() {
    // Use Axios to send a POST request to the backend login endpoint
     axios.post('http://example.com/api/login', {
      // Send the username and password as part of the request body
       username: this.credentials.username,
       password: this.credentials.password
     })
     .then(response => {
       if (response.data.success) {
         alert('Login successful!');
       } else {
         alert('Invalid credentials.');
       }
     })
     .catch(error => {
      // Handle errors that occur during the request, such as network issues or server errors
       console.error('Login error:', error);
       alert('Login failed due to server error.');
     });
   }
 }
  };
  </script>
  
  <style scoped>
  .login-container {
    max-width: 300px;
    margin: 50px auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  .form-group {
    margin-bottom: 20px;
  }
  label {
    display: block;
  }
  input[type="text"],
  input[type="password"] {
    width: 100%;
    padding: 8px;
    margin-top: 5px;
  }
  button {
    width: 100%;
    padding: 10px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  button:hover {
    background-color: #0056b3;
  }
  </style>
  
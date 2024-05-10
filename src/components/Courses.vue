<template>
    <div class="courses">
      <h2>Courses</h2>
  
      <!-- Show course list -->
    <div class="course" v-for="course in courses" :key="course.code">
      <router-link :to="'/course/' + course.code"> <!-- Pass the course code as a parameter -->
        <h3>{{ course.name }}</h3>
        <p>{{ course.description }}</p>
      </router-link>
    </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Courses',
    data() {
      return {
        courses: [
          {
            code: 'CSC2011',
            name: 'Engineering Mathematics Systems Modelling',
            description: 'Description for Engineering Mathematics Systems Modelling.'
          },
          {
            code: 'CSC2012',
            name: 'Foundations of Programming',
            description: 'Description for Foundations of Programming.'
          },
          {
            code: 'CSC2013',
            name: 'Mechanics 1: Statics and Structures',
            description: 'Description for Mechanics 1: Statics and Structures.'
          }
        ]
      };
    }
  }
  </script>
  
  <style scoped>
  .courses {
    width : 600px;
    text-align: center;
    color : #333;
    margin-left : 40%;
  }
  
  .course {
    margin-top: 20px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
    color : #333;
  }

  .course h3 {
    margin-bottom: 10px;
    color : #333;

  }
  
  .course p {
    color: #666;
  }
  </style>
  
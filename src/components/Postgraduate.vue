<template>
    <div class="courses-selection">
      <!-- Left grade option -->
      <div class="year-options">
        <h2>Postgraduate</h2>
  
        <button class="year-button" @click="showCourses('Year1')">Year 1</button>
        <button class="year-button" @click="showCourses('Year2')">Year 2</button>
        <!-- Add more grade options here -->
      </div>
      <!-- On the right side, courses for the selected grade are displayed -->
      <div class="selected-courses">
        <h2>{{ selectedYear }}</h2>
        <!-- Course list -->
  
        <div class="courses-grid">
          <div
            v-for="course in courses[selectedYear]"
            :key="course.name"
            class="course"
            @click="handleCourseClick(course)"
          >
            <h3>{{ course.name }}</h3>
            <p>{{ course.description }}</p>
          </div>
        </div>
      </div>
       <!-- Course details modal box -->
       <div v-if="selectedCourse" class="modal">
        <div class="modal-content">
          <span class="close" @click="closeModal">&times;</span>
          <h2>{{ selectedCourse.name }}</h2>
          <p>{{ selectedCourse.description }}</p>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'CoursesSelection',
    data() {
      return {
        selectedYear: null,
        courses: {
          Year1: [
            { name: 'Course 1', description: 'Description for Course 1' },
            { name: 'Course 2', description: 'Description for Course 2' },
            { name: 'Course 3', description: 'Description for Course 3' },
            // Add Year1 classes here
          ],
          Year2: [
            { name: 'Course 3', description: 'Description for Course 3' },
            { name: 'Course 4', description: 'Description for Course 4' },
            { name: 'Course 5', description: 'Description for Course 5' },
            // Add Year2 classes here
          ],
          // Add more grade classes here
        },
        selectedCourse: null,
      };
    },
    methods: {
      showCourses(year) {
        this.selectedYear = year;
      },
      handleCourseClick(course) {
        this.selectedCourse = course;
      },
      closeModal() {
        this.selectedCourse = null;
      }
    }
  };
  </script>
  
  <style>
  .courses-selection {
    display: flex;
    justify-content: space-between; 
    align-items: flex-start; 
    margin-top: 20px;
  }
  
  .year-options {
    flex: 1; 
    margin-right: 20px; 
    
  
    display: flex; 
    flex-direction: column; 
  }
  
  .year-options h2 {
    margin-bottom: 10px; 
  }
  
  .year-button {
    margin-bottom: 10px; 
    padding: 8px 12px;
  }
  
  
  .selected-courses {
    flex: 2; 
  
  }
  
  .courses-grid {
    display: flex;
    flex-wrap: wrap;
  }
  
  .course {
    flex: 0 0 calc(50% - 10px);
    margin-bottom: 20px;
    margin-right: 10px; 
    padding: 10px;
    border: 1px solid #ccc;
    box-sizing: border-box; 
  }
  .course:hover {
    background-color: lightblue; 
    cursor: pointer; 
  }
  
  @media (max-width: 768px) {
    .course {
      flex: 0 0 calc(50% - 10px);
    }
  }
  
  @media (max-width: 480px) {
    .course {
      flex: 0 0 calc(100% - 20px);
    }
  
  }
  .modal {
    display: block;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.5);
  }
  
  .modal-content {
    background-color: #fefefe;
    margin: 15% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 50%;
  }
  
  .close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
  }
  
  .close:hover,
  .close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
  }
  </style>
  
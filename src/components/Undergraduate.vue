<template>
  <div class="courses-selection">
    <!-- 左侧年级选项 -->
    <div class="year-options">
      <h2></h2>

      <button class="year-button" @click="showCourses('Year1')">Year 1</button>
      <button class="year-button" @click="showCourses('Year2')">Year 2</button>
      <!-- 在这里添加更多年级的选项 -->
    </div>
    <!-- 右侧显示选定年级的课程 -->
    <div class="selected-courses">
      <h2>{{ selectedYear }}</h2>
      <!-- 课程列表 -->

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
     <!-- 课程详细信息模态框 -->
     <div v-if="selectedCourse" class="modal">
  <div class="modal-content">
    <span class="close" @click="closeModal">&times;</span>
    <h2>{{ selectedCourse.name }}</h2>
    <p>{{ selectedCourse.description }}</p>
    <button @click="applyCourse">Apply</button>
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
        ],
        Year2: [
          { name: 'Course 3', description: 'Description for Course 3' },
          { name: 'Course 4', description: 'Description for Course 4' },
          { name: 'Course 5', description: 'Description for Course 5' },

        ],
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
  },
  applyCourse() {
    // 在这里处理申请按钮的点击事件，可以发送申请请求等操作
    console.log('Course applied:', this.selectedCourse);
    // 这里只是示例，你可以根据实际情况进行相应的操作
    alert('Course applied!');
  }
}

};
</script>

<style>
.courses-selection {
  display: flex;
  justify-content: space-between; /* 左右分布 */
  align-items: flex-start; /* 上对齐 */
  margin-top: 20px;
}

.year-options {
  flex: 1; /* 左侧占据空间 */
  margin-right: 20px; /* 右侧留出间距 */
  display: flex; /* 使用 flex 布局 */
  flex-direction: column; /* 垂直排列 */
  align-items: center; /* 居中对齐 */
  margin-top : 20%;
}

.year-options h2 {
  margin-bottom: 10px; /* 设置标题的底部间距 */
}

.year-button {
  margin-bottom: 10px; /* 设置按钮之间的间距 */
  padding: 8px 12px;
  width : 100px;
}
/* 新的按钮样式 */
.year-button {
  margin-bottom: 30px;
  width: 150px;
  padding: 10px;
  border-radius: 4px;
  cursor: pointer;
  background-color: #4CAF50; /* 设置按钮背景色 */
  color: white; /* 设置按钮文字颜色 */
  border: none; /* 移除按钮边框 */
  outline: none; /* 移除按钮点击时的外边框 */
  transition: background-color 0.3s; /* 添加过渡效果 */
}

.year-button:hover {
  background-color: #45a049; /* 悬停时的背景色 */
}

.selected-courses {
  flex: 2; /* 右侧占据更多空间 */

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
  box-sizing: border-box; /* 添加盒模型的属性 */
}
.course:hover {

  cursor: pointer; /* 鼠标指针样式 */
}
/* 调整课程项的大小 */
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

<template>
  <div class="course-detail" v-if="course">
    <h2>{{ course.name }}</h2>
    <p>{{ course.description }}</p>

    <div class="panel-container">
      <!-- 左侧按钮 -->
      <div class="left-panel">
        <div class="button-group">
          <button @click="showContent('syllabus')">Syllabus</button>
          <button @click="showContent('assignments')">Assignments</button>
          <button @click="showContent('grade')">Grade</button>
          <button @click="showContent('meetingAttendance')">Meeting Attendance</button>
          <button @click="showContent('reflect')">Reflect</button>
        </div>
      </div>

      <!-- 右侧内容 -->
      <div class="right-panel">
        <div v-if="courseContent.syllabus" class="content-item" v-show="currentContent === 'syllabus'">
          <h3>Syllabus</h3>
          <p>{{ courseContent.syllabus }}</p>
        </div>

        <div v-if="courseContent.assignments" class="content-item" v-show="currentContent === 'assignments'">
          <h3>Assignments</h3>
          <div class="assignment-notification">
            <p><strong>Course Name:</strong> {{courseContent.assignments.courseName}}</p>
            <p><strong>Course ID:</strong> {{courseContent.assignments.courseID}}</p>
            <p><strong>Instructor:</strong> {{courseContent.assignments.instructor}}</p>
            <p><strong>Class Time:</strong> {{courseContent.assignments.classTime}}</p>
            <p><strong>Exam Type:</strong> {{courseContent.assignments.examType}}</p>
            <p><strong>Exam Time:</strong> {{courseContent.assignments.examTime}}</p>
            <button @click="submitAssignment">Feedback Submit</button>
          </div>
        </div>

        <div v-if="courseContent.grade" class="content-item" v-show="currentContent === 'grade'">
          <h3>Grade</h3>
          <table>
            <thead>
              <tr>
                <th>Course Name</th>
                <th>Score</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(score, courseName) in courseContent.grade" :key="courseName">
                <td>{{ courseName }}</td>
                <td>{{ score }}</td>
              </tr>
            </tbody>
          </table>
        </div>

        <div v-if="courseContent.meetingAttendance" class="content-item" v-show="currentContent === 'meetingAttendance'">
          <h3>Meeting Attendance</h3>
          <div v-if="meetingSchedule.length > 0" class="meeting-schedule">
            <table>
              <thead>
                <tr>
                  <th></th>
                  <th v-for="day in weekdays" :key="day">{{ day }}</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(timeSlot, index) in timeSlots" :key="index">
                  <td>{{ timeSlot }}</td>
                  <td v-for="day in weekdays" :key="day">
                    <template v-if="hasCourse(day, timeSlot)">
                      {{ getCourse(day, timeSlot) }}
                    </template>
                    <template v-else>
                      <select v-model="selectedMeeting[`${day}-${timeSlot}`]">
                        <option value="">No Course</option>
                        <option v-for="meeting in meetings" :key="meeting" :value="meeting">{{ meeting }}</option>
                      </select>
                      <button @click="applyMeeting(day, timeSlot)">Apply</button>
                      <p v-if="applying[`${day}-${timeSlot}`]">Applying...</p>
                      <p v-if="meetingApplied[`${day}-${timeSlot}`] && meetingStatus[`${day}-${timeSlot}`] === 'successed'">Successed</p>
                      <p v-if="meetingApplied[`${day}-${timeSlot}`] && meetingStatus[`${day}-${timeSlot}`] === 'failed'">Failed</p>
                    </template>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div v-else>
            <p>No meetings scheduled</p>
          </div>
        </div>

        <div v-if="courseContent.reflect" class="content-item" v-show="currentContent === 'reflect'">
          <h3>Reflect</h3>
          <p>{{ courseContent.reflect }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { fakeAPI } from '@/api/api'; // 引入 API 文件

export default {
  name: 'CourseDetail',
  props: ['code'],
  data() {
    return {
      course: null,

      courseContent: {
        
      },
      weekdays: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'],
      timeSlots: ['8:00 - 10:00', '10:00 - 12:00', '13:00 - 15:00', '15:00 - 17:00'],
      meetingSchedule: [], // 存储会议时间表
      meetings: ['Meeting 1', 'Meeting 2', 'Meeting 3'], // 可选的会议
      selectedMeeting: {}, // 用户选择的会议
      applying: {}, // 是否正在申请会议
      meetingApplied: {}, // 是否已申请会议
      meetingStatus: {}, // 会议申请状态：successed、failed
      currentContent: '' // 当前显示的内容
    };
  },
  created() {
    // 在组件创建时获取课程详细信息
    this.getCourseDetail(this.code);
    this.loadMeetingSchedule(); // 加载会议时间表
  },
  methods: {
    async getCourseDetail(code) {
      // 使用 fakeAPI 中定义的函数来获取课程信息
      const courses = await fakeAPI.getCourses();
      // 根据课程 code 查找对应的课程信息
      this.course = courses.find(course => course.code === code);
    },
    async showContent(content) {
      // 使用 fakeAPI 中定义的函数来获取课程内容
      const courseContent = await fakeAPI.getCourseContent(this.code);
      // 先重置 courseContent 对象
      this.courseContent = {};
      // 根据传入的 content 参数来设置课程内容
      switch (content) {
        case 'syllabus':
          this.courseContent = { syllabus: courseContent.syllabus };
          break;
        case 'assignments':
          // 单独使用fakeAPI的getAssignments
          this.courseContent = { assignments: await fakeAPI.getAssignments(this.code) };
          break;
        case 'grade':
          this.courseContent = { grade: await fakeAPI.getGrades(this.code) };
          break;
        case 'meetingAttendance':
          this.courseContent = { meetingAttendance: courseContent.meetingAttendance };
          break;
        case 'reflect':
          this.courseContent = { reflect: courseContent.reflect };
          break;
        default:
          this.courseContent = {};
          break;
      }
      this.currentContent = content; // 更新当前显示的内容
    },

    async loadMeetingSchedule() {
      // 模拟加载会议时间表，这里使用 setTimeout 模拟异步请求延迟
      setTimeout(() => {
        // 假设会议时间表数据
        this.meetingSchedule = [
          { day: 'Monday', time: '8:00 - 10:00', course: 'Physics' },
          { day: 'Tuesday', time: '10:00 - 12:00', course: 'Math' },
          { day: 'Wednesday', time: '13:00 - 15:00', course: 'Chemistry' },
          { day: 'Thursday', time: '15:00 - 17:00', course: 'Biology' },
          { day: 'Friday', time: '8:00 - 10:00', course: 'History' }
        ];

        // 初始化下拉菜单和申请状态数据
        for (let i = 0; i < this.meetingSchedule.length; i++) {
          const { day, time } = this.meetingSchedule[i];
          this.$set(this.selectedMeeting, `${day}-${time}`, '');
          this.$set(this.applying, `${day}-${time}`, false);
          this.$set(this.meetingApplied, `${day}-${time}`, false);
          this.$set(this.meetingStatus, `${day}-${time}`, null);
        }
      }, 2000); // 模拟2秒延迟
    },

    hasCourse(day, timeSlot) {
      return this.meetingSchedule.some(schedule => schedule.day === day && schedule.time === timeSlot);
    },

    getCourse(day, timeSlot) {
      const meeting = this.meetingSchedule.find(schedule => schedule.day === day && schedule.time === timeSlot);
      return meeting ? meeting.course : '';
    },

    applyMeeting(day, timeSlot) {
      this.$set(this.applying, `${day}-${timeSlot}`, true); // 设置申请状态为 true
      // 模拟申请会议的过程
      setTimeout(() => {
        // 随机生成会议申请状态
        const status = Math.random() < 0.5 ? 'successed' : 'failed';
        this.$set(this.meetingStatus, `${day}-${timeSlot}`, status);
        this.$set(this.applying, `${day}-${timeSlot}`, false);
        this.$set(this.meetingApplied, `${day}-${timeSlot}`, true);
      }, 2000); // 模拟2秒延迟
    },

    submitAssignment() {
      // 在这里添加提交作业的逻辑
      console.log('Submitting assignment:', this.courseContent.assignments);
    }
  }
};
</script>
<style scoped>
/* 导入 Google Fonts */
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans:ital,wght@0,100..900;1,100..900&display=swap');
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

.button-group button:hover {
  background-color: #45a049; /* 悬停时的背景色 */
}

.right-panel {
  flex: 3;
  margin-left: 100px;
}

.content-item {
  width: 800px; /* 设置固定宽度 */
  overflow-x: auto; /* 添加水平滚动条 */
  overflow-y: auto; /* 添加垂直滚动条 */
  display: flex;
  flex-direction: column;
  align-items: center;
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
  background: rgb(239, 239, 239);
  color: black;
  border: 1px solid #ccc;
  border-radius: 4px;
  cursor: pointer;
}

.assignment-notification button:hover {
  background-color: rgb(229, 229, 229);
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

/* 新增的表格样式 */
table {
  width: 80%; /* 根据需要设置表格宽度 */
}

th, td {
  border: 1px solid #ddd; /* 添加单元格边框 */
  padding: 8px; /* 添加单元格内边距 */
  text-align: center; /* 将单元格文本居中 */
}

th {
  background-color: #f2f2f2; /* 设置表头背景颜色 */
}
</style>

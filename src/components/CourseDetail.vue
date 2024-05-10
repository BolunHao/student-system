<template>
  <div class="course-detail" v-if="course">
    <h2>{{ course.name }}</h2>
    <p>{{ course.description }}</p>

    <div class="panel-container">
      <!-- Left button -->
      <div class="left-panel">
        <div class="button-group">
          <button @click="showContent('syllabus')">Syllabus</button>
          <button @click="showContent('assignments')">Assignments</button>
          <button @click="showContent('grade')">Grade</button>
          <button @click="showContent('meetingAttendance')">Meeting Attendance</button>
          <button @click="showContent('reflect')">Reflect</button>
        </div>
      </div>

      <!-- Right side content -->
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
import { fakeAPI } from '@/api/api'; // Import API file

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
      meetingSchedule: [], // Save meeting schedule
      meetings: ['Meeting 1', 'Meeting 2', 'Meeting 3'], // Optional meeting
      selectedMeeting: {}, // User selected meeting
      applying: {}, // Whether you are applying for a meeting
      meetingApplied: {}, // Whether you have requested a meeting
      meetingStatus: {}, // Meeting request status：successed、failed
      currentContent: '' // The content currently displayed
    };
  },
  created() {
    // Get course details at component creation time
    this.getCourseDetail(this.code);
    this.loadMeetingSchedule(); // Loading meeting schedule
  },
  methods: {
    async getCourseDetail(code) {
      // Use the functions defined in fakeAPI to get course information
      const courses = await fakeAPI.getCourses();
      // Find the corresponding course information according to the course code
      this.course = courses.find(course => course.code === code);
    },
    async showContent(content) {
      // Use the functions defined in fakeAPI to get the course content
      const courseContent = await fakeAPI.getCourseContent(this.code);
      // Reset the courseContent object first
      this.courseContent = {};
      // The course content is set according to the content parameter passed in
      switch (content) {
        case 'syllabus':
          this.courseContent = { syllabus: courseContent.syllabus };
          break;
        case 'assignments':
          // getAssignments using fakeAPI alone
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
      this.currentContent = content; // Update the current display
    },

    async loadMeetingSchedule() {
      // Simulate loading the meeting schedule, using setTimeout to simulate asynchronous request latency
      setTimeout(() => {
        // Assume meeting schedule data
        this.meetingSchedule = [
          { day: 'Monday', time: '8:00 - 10:00', course: 'Physics' },
          { day: 'Tuesday', time: '10:00 - 12:00', course: 'Math' },
          { day: 'Wednesday', time: '13:00 - 15:00', course: 'Chemistry' },
          { day: 'Thursday', time: '15:00 - 17:00', course: 'Biology' },
          { day: 'Friday', time: '8:00 - 10:00', course: 'History' }
        ];

        // Initialize the drop-down menu and request status data
        for (let i = 0; i < this.meetingSchedule.length; i++) {
          const { day, time } = this.meetingSchedule[i];
          this.$set(this.selectedMeeting, `${day}-${time}`, '');
          this.$set(this.applying, `${day}-${time}`, false);
          this.$set(this.meetingApplied, `${day}-${time}`, false);
          this.$set(this.meetingStatus, `${day}-${time}`, null);
        }
      }, 2000); // Simulate 2 second delay
    },

    hasCourse(day, timeSlot) {
      return this.meetingSchedule.some(schedule => schedule.day === day && schedule.time === timeSlot);
    },

    getCourse(day, timeSlot) {
      const meeting = this.meetingSchedule.find(schedule => schedule.day === day && schedule.time === timeSlot);
      return meeting ? meeting.course : '';
    },

    applyMeeting(day, timeSlot) {
      this.$set(this.applying, `${day}-${timeSlot}`, true); // Set the application status to true
      // Simulate the process of applying for a meeting
      setTimeout(() => {
        // The conference application status is randomly generated
        const status = Math.random() < 0.5 ? 'successed' : 'failed';
        this.$set(this.meetingStatus, `${day}-${timeSlot}`, status);
        this.$set(this.applying, `${day}-${timeSlot}`, false);
        this.$set(this.meetingApplied, `${day}-${timeSlot}`, true);
      }, 2000); // Simulate 2 second delay
    },

    submitAssignment() {
      // Add the logic for submitting the job here
      console.log('Submitting assignment:', this.courseContent.assignments);
    }
  }
};
</script>
<style scoped>
/* Import Google Fonts */
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
  background-color: #4CAF50; /* Set the button background color */
  color: white; /* Set the button text color */
  border: none; /* Remove button border */
  outline: none; /* Remove the outer border when the button is clicked */
  transition: background-color 0.3s; /* Add transition effect */
}

.button-group button:hover {
  background-color: #45a049; /* Hover background color */
}

.right-panel {
  flex: 3;
  margin-left: 100px;
}

.content-item {
  width: 800px; /* Set fixed width */
  overflow-x: auto; /* Add a horizontal scroll bar */
  overflow-y: auto; /* Add a vertical scroll bar */
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
  width: 50px; /* Time column width */
}

.meeting-schedule th:last-child {
  width: calc(100% - 50px); /* Course column width */
}

.meeting-schedule td:last-child {
  text-align: left; /* The course column text is left justified */
}

select {
  margin-right: 10px;
}

/* New table styles */
table {
  width: 80%; /* Set the table width as required */
}

th, td {
  border: 1px solid #ddd; /* Adds a cell border */
  padding: 8px; /* Adds margins inside cells */
  text-align: center; /* Center cell text */
}

th {
  background-color: #f2f2f2; /* Sets the header background color */
}
</style>

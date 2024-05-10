<template>
  <div>
    <!-- Course form -->
    <div class="timetable">
      <table>
        <!-- header -->
        <thead>
          <tr>
            <th></th>
            <th v-for="day in weekdays" :key="day">{{ day }}</th>
          </tr>
        </thead>
        <!-- Table content -->
        <tbody>
          <tr v-for="(timeSlot, index) in timeSlots" :key="index">
            <td>{{ timeSlot }}</td>
            <td v-for="day in weekdays" :key="day">
              <template v-if="hasCourse(day, timeSlot)">
                <!-- Show course names where courses are available -->
                <div>
                  {{ getEvent(day, timeSlot) }}
                  <template v-if="courseStatus[`${day}-${timeSlot}`] === 'applying'">
                    <div>
                      <div v-if="!successOrFailed[`${day}-${timeSlot}`]">Applying</div>
                      <div v-else>{{ successOrFailed[`${day}-${timeSlot}`] }}</div>
                    </div>
                  </template>
                  <template v-else>
                    <button @click="handleAbsence(day, timeSlot)">Absence</button>
                  </template>
                </div>
              </template>
              <template v-else>
                <!-- Add the Course Selection drop-down menu where there are no courses -->
                <router-link to="/selection">
                  <button> Course Selection </button>
                </router-link>
              </template>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {


      weekdays: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'],
      timeSlots: ['8:00 - 10:00', '10:00 - 12:00', '13:00 - 15:00', '15:00 - 17:00'],
      calendarEvents: [ // Calendar event
        // Example event, you need to set the event according to the actual situation
        { title: 'Math', day: 'Monday', timeSlot: '8:00 - 10:00' },
        { title: 'English', day: 'Tuesday', timeSlot: '10:00 - 12:00' },
        { title: 'Science', day: 'Wednesday', timeSlot: '13:00 - 15:00' },
        { title: 'History', day: 'Thursday', timeSlot: '15:00 - 17:00' },
        { title: 'Geography', day: 'Friday', timeSlot: '8:00 - 10:00' },
      ],
      courseStatus: {}, // Course status, default is empty object
      successOrFailed: {}, // Record the success or failure status for each time period
    };
  },
  methods: {
    // Determine whether classes are available during a certain time period
    hasCourse(day, timeSlot) {
      return !!this.calendarEvents.find(course => course.day === day && course.timeSlot === timeSlot);
    },
    // Get events for each time period (course)
    getEvent(day, timeSlot) {
      const course = this.calendarEvents.find(course => course.day === day && course.timeSlot === timeSlot);
      return course ? course.title : '';
    },
    // Handle clicking the "Absence" button
    handleAbsence(day, timeSlot) {
      this.$set(this.courseStatus, `${day}-${timeSlot}`, 'applying');
      setTimeout(() => {
        const isSuccessed = Math.random() < 0.5; // Use random numbers to determine success or failure
        this.$set(this.successOrFailed, `${day}-${timeSlot}`, isSuccessed ? 'Successed' : 'Failed');
      }, 1000); // Delay execution for one second
    },
  }
};
</script>

<style scoped>
body {
  font-family: 'Noto Sans', sans-serif;
  margin: 0;
  overflow-y: scroll;
  
}

.timetable {
  margin-top: 20px;
  text-align: center;


  margin-top : 10%;
  margin-left : 30%;
}

table {
  border-collapse: collapse;
  width: 150%;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: center;
}

th {
  background-color: #f2f2f2;
  color: #333;
}
</style>

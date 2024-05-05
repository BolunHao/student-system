<template>
  <div>
    <!-- 课程表格 -->
    <div class="timetable">
      <table>
        <!-- 表头 -->
        <thead>
          <tr>
            <th></th>
            <th v-for="day in weekdays" :key="day">{{ day }}</th>
          </tr>
        </thead>
        <!-- 表格内容 -->
        <tbody>
          <tr v-for="(timeSlot, index) in timeSlots" :key="index">
            <td>{{ timeSlot }}</td>
            <td v-for="day in weekdays" :key="day">
              <template v-if="hasCourse(day, timeSlot)">
                <!-- 有课程的地方显示课程名称 -->
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
                <!-- 没有课程的地方添加 Course Selection 下拉菜单 -->
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
      calendarEvents: [ // 日历事件
        // 示例事件，你需要根据实际情况设置事件
        { title: 'Math', day: 'Monday', timeSlot: '8:00 - 10:00' },
        { title: 'English', day: 'Tuesday', timeSlot: '10:00 - 12:00' },
        { title: 'Science', day: 'Wednesday', timeSlot: '13:00 - 15:00' },
        { title: 'History', day: 'Thursday', timeSlot: '15:00 - 17:00' },
        { title: 'Geography', day: 'Friday', timeSlot: '8:00 - 10:00' },
      ],
      courseStatus: {}, // 课程状态，默认为空对象
      successOrFailed: {}, // 记录每个时间段的成功或失败状态
    };
  },
  methods: {
    // 判断某个时间段是否有课程
    hasCourse(day, timeSlot) {
      return !!this.calendarEvents.find(course => course.day === day && course.timeSlot === timeSlot);
    },
    // 获取每个时间段的事件（课程）
    getEvent(day, timeSlot) {
      const course = this.calendarEvents.find(course => course.day === day && course.timeSlot === timeSlot);
      return course ? course.title : '';
    },
    // 处理点击 "Absence" 按钮
    handleAbsence(day, timeSlot) {
      this.$set(this.courseStatus, `${day}-${timeSlot}`, 'applying');
      setTimeout(() => {
        const isSuccessed = Math.random() < 0.5; // 使用随机数决定成功或失败
        this.$set(this.successOrFailed, `${day}-${timeSlot}`, isSuccessed ? 'Successed' : 'Failed');
      }, 1000); // 延迟一秒执行
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

<template>
  <div class="academic-guidance">
    <h2>Academic Guidance</h2>
    <table>
      <thead>
        <tr>
          <th>Subject</th>
          <th>Tutor</th>
          <th>Apply</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(application, index) in applications" :key="index">
          <td class="center">{{ application.subject }}</td>
          <td class="center">{{ application.tutor }}</td>
          <td class="center">
            <button v-if="!application.applied" @click="submitApplication(index)">Submit</button>
            <template v-else>
              <span v-if="application.status === 'pending'">Pending</span>
              <span v-else-if="application.status === 'success'">Success</span>
              <span v-else-if="application.status === 'failed'">Failed</span>
            </template>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      applications: [
        { subject: 'AI', tutor: 'A', applied: false, status: null },
        { subject: 'Data Science', tutor: 'B', applied: false, status: null }
      ]
    };
  },
  methods: {
    submitApplication(index) {
      // 模拟提交申请的异步过程
      setTimeout(() => {
        // 模拟申请成功或失败
        const success = Math.random() < 0.5;
        this.applications[index].status = success ? 'success' : 'failed';
        this.applications[index].applied = true;
      }, 1000); // 模拟1秒延迟
    }
  }
};
</script>

<style scoped>
/* 导入 Google Fonts */
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans:ital,wght@0,100..900;1,100..900&display=swap');
.academic-guidance {
  max-width: 1000px;
  width : 600px;
  margin: 0 auto;
  margin-left : 50%;
  margin-top : 20%;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ccc;
  padding: 8px;
  text-align: center;
}

th {
  background-color: #f2f2f2;
}

.center {
  text-align: center;
}

/* 新添加的样式 */
/* sidebar stylings */
.sidebar {
  background-color: rgb(0, 0, 0);
  width: 10%;
  height: 100%;
  padding: 0 1rem;
  position: fixed;
  top: 0;
  left: 0;
}

.sidebar a {
  color: white;
  text-decoration: none;
}

.sidebar .sidebartop {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: flex-start;
  padding-top: 1rem;
}

.sidebar .sidebartop .menu {
  color: white;
  text-align: end;
  width: 20%;
}

.sidebar .sidebartop .menu i {
  font-size: 1.75rem;
  cursor: pointer;
}

.sidebar .sidebartop .logo {
  width: 100%;
}

.sidebar .sidebartop .logo img {
  height: auto;
  width: 100%;
}

.sidebar nav ul {
  padding: 0;
  margin: 0;
  list-style: none;
}

.sidebar nav ul li {
  display: block;
  align-items: center;
  padding: 1.25rem 0;
  position: relative;
  background-color: transparent;
  transition: background-color .25s ease-in-out;
}

.sidebar nav ul li a {
  display: block;
}

.sidebar nav ul li a i {
  font-size: 1.25rem;
  width: 25px;
  height: 20px;
  text-align: center;
}

.sidebar nav ul li a .text {
  position: relative;
  left: 1rem;
  top: -0.20rem;
}

.sidebar .account {
  display: flex;
  justify-content: space-between;
  align-content: center;
  align-items: center;
  width: calc(100% - 2rem);
  position: absolute;
  bottom: 1rem;
}

.sidebar .account .avatar {
  margin-right: 1rem;
  width: 20%;
}

.sidebar .account .avatar img {
  border-radius: 50%;
  height: 50px;
  width: 50px;
}

.sidebar .account .name {
  flex: 1 1 auto;
  margin-left: 0.5rem;
}

.sidebar .account .name h4 {
  padding: 0;
  margin: 0;
}

.sidebar .account .logout {
  flex: 1 1 auto;
  text-align: end;
}

.sidebar .account .logout i {
  font-size: 1.5rem;
}
</style>


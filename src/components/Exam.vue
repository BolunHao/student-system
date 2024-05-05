<template>
  <div class="exam">
    <h2>Exam</h2>
    <table>
      <thead>
        <tr>
          <th>Courses</th>
          <th>Date</th>
          <th>Time</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td class="center">CSC2011</td>
          <td class="center">2024.3.13</td>
          <td class="center">1.5 hours</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.exam {
  max-width: 800px;
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
</style>

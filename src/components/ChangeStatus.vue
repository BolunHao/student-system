<template>
  <div class="change-status">
    <h2>Change Status</h2>

    <button class="button" @click="toggleInstructions('suspend')">Suspend</button>
    <button class="button" @click="toggleInstructions('withdraw')">Withdraw</button>

    <!-- 休学办理说明 -->
    <div v-if="showSuspendInstructions" class="instructions">
      <p>休学办理说明：</p>
      <p>xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</p>
      <button class="submit-button" @click="apply('suspend')">Submit</button>
    </div>

    <!-- 退学办理说明 -->
    <div v-if="showWithdrawInstructions" class="instructions">
      <p>退学办理说明：</p>
      <p>xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</p>
      <button class="submit-button" @click="apply('withdraw')">Submit</button>
    </div>
    
    <!-- Applying 状态 -->
    <div v-if="applying" class="applying-message">Applying...</div>
  </div>
</template>

<script>
export default {
  name: 'ChangeStatus',
  data() {
    return {
      showSuspendInstructions: false,
      showWithdrawInstructions: false,
      applying: false // 添加 applying 状态，用于控制提交状态显示
    };
  },
  methods: {
    toggleInstructions(type) {
      if (type === 'suspend') {
        this.showSuspendInstructions = !this.showSuspendInstructions;
        this.showWithdrawInstructions = false; // 隐藏退学说明文本
      } else if (type === 'withdraw') {
        this.showWithdrawInstructions = !this.showWithdrawInstructions;
        this.showSuspendInstructions = false; // 隐藏休学说明文本
      }
    },
    apply(type) {
      console.log(`Applying for ${type}`); // 输出申请类型  
      // 这里模拟提交状态
      this.applying = true;
      setTimeout(() => {
        // 模拟提交完成后的操作
        this.applying = false;
      }, 2000); // 2秒后恢复正常状态
    }
  }
}
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
  margin-bottom: 10px;
}

.right-panel {
  flex: 3;
  margin-left: 20px;
}

.content-item {
  width: 800px; /* 设置固定宽度 */
  overflow-x: auto; /* 添加水平滚动条 */
  overflow-y: auto; /* 添加垂直滚动条 */
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
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.assignment-notification button:hover {
  background-color: #0056b3;
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

/* 新增的样式 */
.change-status {
  text-align: center;
  width : 100%;
  margin-top: 80%;
  margin-left: 250%;
}

/* 按钮样式 */
.button {
  flex: 0 0 calc(50% - 10px);
  margin-bottom: 20px;
  margin-right: 10px; 
  padding: 10px;
  border: 1px solid #ccc;
  box-sizing: border-box; /* 添加盒模型的属性 */
  background: rgb(239, 239, 239);
  color: black;
}

.button:hover {
  background-color: rgb(229, 229, 229);
}
.instructions {
 
  position: relative; /* 添加相对定位 */
}

.submit-button {

  bottom: 0; /* 按钮位于底部 */
  right: 0; /* 按钮位于右侧 */
  margin: 10px; /* 添加一些外边距 */
  margin-left : 50%;
  padding: 8px 16px;
  background: rgb(239, 239, 239);
  color: black;
  border: 1px solid #ccc;
  border-radius: 4px;
  cursor: pointer;
}

.submit-button:hover {
  background-color: rgb(229, 229, 229);
}

.applying-message {
  margin-top: 10px;
  font-weight: bold;
}
</style>

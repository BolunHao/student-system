<template>
  <div class="change-status">
    <h2>Change Status</h2>

    <button class="button" @click="toggleInstructions('suspend')">Suspend</button>
    <button class="button" @click="toggleInstructions('withdraw')">Withdraw</button>

    <!-- leave of absence handling instructions -->
    <div v-if="showSuspendInstructions" class="instructions">
      <p>withdrawal handling instructions：</p>
      <p>xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</p>
      <button class="submit-button" @click="apply('suspend')">Submit</button>
    </div>

    <!-- withdrawal handling instructions -->
    <div v-if="showWithdrawInstructions" class="instructions">
      <p>withdrawal handling instructions：</p>
      <p>xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</p>
      <button class="submit-button" @click="apply('withdraw')">Submit</button>
    </div>
    
    <!-- Applying status -->
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
      applying: false // add applying status，used to control the display of submission status
    };
  },
  methods: {
    toggleInstructions(type) {
      if (type === 'suspend') {
        this.showSuspendInstructions = !this.showSuspendInstructions;
        this.showWithdrawInstructions = false; // hide the withdrawal instructions text
      } else if (type === 'withdraw') {
        this.showWithdrawInstructions = !this.showWithdrawInstructions;
        this.showSuspendInstructions = false; // hide the leave of absence instructions text
      }
    },
    apply(type) {
      console.log(`Applying for ${type}`); // output application type  
      // simulate submission status here
      this.applying = true;
      setTimeout(() => {
        // Simulate the action after the commit is complete
        this.applying = false;
      }, 2000); // It will return to normal after 2 seconds
    }
  }
}
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
  margin-bottom: 10px;
}

.right-panel {
  flex: 3;
  margin-left: 20px;
}

.content-item {
  width: 800px; /* Set fixed width */
  overflow-x: auto; /* Add a horizontal scroll bar */
  overflow-y: auto; /* Add a vertical scroll bar */
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

/* New style */
.change-status {
  text-align: center;
  width : 100%;
  margin-top: 80%;
  margin-left: 250%;
}

/* Button style */
.button {
  flex: 0 0 calc(50% - 10px);
  margin-bottom: 20px;
  margin-right: 10px; 
  padding: 10px;
  border: 1px solid #ccc;
  box-sizing: border-box; /* Add the properties of the box model */
  background: rgb(239, 239, 239);
  color: black;
}

.button:hover {
  background-color: rgb(229, 229, 229);
}
.instructions {
 
  position: relative; /* Add relative positioning */
}

.submit-button {

  bottom: 0; /* Button at bottom */
  right: 0; /* Button on the right */
  margin: 10px; /* Add some margins */
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

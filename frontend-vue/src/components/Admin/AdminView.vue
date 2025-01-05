<template>
  <div class="admin-dashboard">
    <h1>Admin Dashboard</h1>
    <div class="buttons">
      <router-link to="/productTable">
        <button class="dashboard-button">
          Go to Product Table
        </button>
      </router-link>
      <router-link to="/tagTable">
        <button class="dashboard-button">
          Go to Tag Table
        </button>
      </router-link>
    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>
.admin-dashboard {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 50px;
}

h1 {
  margin-bottom: 20px;
  font-size: 2rem;
  color: #333;
}

.buttons {
  display: flex;
  gap: 20px;
}

.dashboard-button {
  padding: 10px 20px;
  font-size: 1rem;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

</style>
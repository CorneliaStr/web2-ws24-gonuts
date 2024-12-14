<script setup>
import SharedHeader from './components/shared/SharedHeader.vue'
import SharedFooter from './components/shared/SharedFooter.vue'
import Shop from './components/Shop.vue'
</script>

<template>
  <shared-header></shared-header>

    <main class="app-container">
      <shop></shop>
    </main>

  <shared-footer></shared-footer>
</template>

<style scoped>
.app-container {
  justify-content: center;
  padding: 20px;
  width: 70%;

  background-color: #fff;
  padding: 20px;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}
</style>

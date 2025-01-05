<template>
    <button class="filter-button" >{{ tag.name }}</button>
</template>

<script setup>
    const props = defineProps(['tag'])
</script>

<style scoped>
.filter-button {
  width: 175px;
  height: 25px;
  padding: 0px 8px 0px 8px;
  background: #DEE4EB;
  color: #000000;
  border-radius: 10px 10px 10px 10px;
  font-weight: 600;
  font-size: 15px;
  line-height: 1;
  text-align: center;
  margin: 2px;
}
</style>
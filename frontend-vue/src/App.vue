<script setup>
import SharedHeader from './components/shared/SharedHeader.vue'
import SharedFooter from './components/shared/SharedFooter.vue'
import Shop from './components/Shop.vue'
</script>

<template>
  <shared-header></shared-header>


  <shop></shop>

  <shared-footer></shared-footer>
</template>

<style scoped>
header {
  line-height: 1.5;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}
</style>

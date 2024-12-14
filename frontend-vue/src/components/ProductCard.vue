<template>
  <a href="../product/product.html" class="product">
    <!-- Tags oben rechts -->
    <div class="product-tags">
      <span v-for="tag in product.tags" :key="tag.id">{{ tag.name }}</span>
    </div>

    <img :src="'/src/assets'+ product.image" class="image-product" alt="...">
    <div>
      <h5>{{ product.name }}</h5>
      <p>{{ product.price }} €</p>
    </div>
  </a>
</template>

<script setup>
const props = defineProps(["product"]);
</script>

<style scoped>
.product {
  flex: 1 1 calc(20% - 20px);
  box-sizing: border-box;
  background-color: #ffffff;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  text-align: left;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-decoration: none;
  color: inherit;

  width: 280px;
  height: 500px;

  max-width: 280px;
  max-height: 300px;
  position: relative;
}

.image-product {
  width: 189px;
  height: 221px;
}

.product:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.product-tags {
  position: absolute;
  top: 10px;
  right: 10px;
  display: flex;
  gap: 5px; /* Abstand zwischen den Tags */
  flex-wrap: wrap;
}

.product-tags span {
  background-color: #f1c40f;
  color: #fff;
  font-size: 12px;
  padding: 5px 8px;
  border-radius: 12px;
  white-space: nowrap;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
}

</style>
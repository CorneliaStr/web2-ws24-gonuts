<template>


    <section id="tag-list" class="filter-button-container">
        <filter-tag v-for="tag in tags" :name="tag.name"></filter-tag>
    </section>

    <div class="heading">
        <h2>Ergebnisse</h2>
    </div>

    <section id="products-list" class="products-list">

    </section>

</template>

<script setup>
import FilterTag from './FilterTag.vue'
import { onMounted } from "vue";
import { ref } from "vue";

let tags = ref([]);

const fetchTags = async () => {
    let response = await fetch("http://localhost:8080/api/tag");
    console.log(response)
    tags.value = await response.json();
};

onMounted(() => {
    fetchTags();

    console.log(tags)
    
});
</script>

<style>
    .shop-container {
    justify-content: center;
    padding: 20px;
    width: 70%;

    background-color: #fff;
    padding: 20px;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

.filter-button-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-evenly;
    padding: 20px;
}

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
}


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

    position: relative;
}

.product:hover{
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
.image-product {
    width: 189px;
    height: 221px;
}
</style>
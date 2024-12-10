<template>
    <a href="../product/product.html" class="product">
            <!-- Tags oben rechts -->
            <div class="product-tags">
                ${tagsHtml}
            </div>
            <img src="../${product.image}" class="image-product" alt="...">
            <div>
                <h5>${product.name}</h5>
                <p>${product.price} €</p>
            </div>
        </a>
</template>
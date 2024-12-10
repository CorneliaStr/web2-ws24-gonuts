let fetchProducts = function() {
    fetch("http://localhost:8080/api/product")
        .then(response => response.json())
        .then(json => buildProductList(json))
}

let fetchTags = function() {
    fetch("http://localhost:8080/api/tag")
        .then(response => response.json())
        .then(json => buildTagsList(json))
}

let buildProductList = function(products) {
    let productList = document.querySelector("#products-list");
    productList.innerHTML = "";
    for (let product of products) {
        let tagsHtml = ""
        
        for(let tag of product.tags){
            console.log(tag.name)
             tagsHtml += `<span>${tag.name}</span>`
        }
        
        productList.innerHTML += 
        `
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
        `
    }
}

let buildTagsList = function(tags) {
    let tagId = document.querySelector("#tag-list");
    tagId.innerHTML = "";
    for (let tag of tags) {
        tagId.innerHTML += 
        `
            <button class="filter-button" >${tag.name}</button>
        `
    }
}

fetchTags();
fetchProducts();

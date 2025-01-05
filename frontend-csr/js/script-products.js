let useGraphQl = () => window.location.search.includes("graphql");

let fetchProducts = function () {
    if (useGraphQl()) fetchProductsGraphQl();
    else fetchProductsRest();
}

let fetchProductsRest = function () {
    fetch("http://localhost:8080/api/product")
        .then(response => response.json())
        .then(json => buildProductList(json))
}
let fetchProductsGraphQl = async () => {
    let query = `{
            products {
                id
                name
                description
                price
                image
                tags {
                    id
                    name
                }
            }
        }`

    let response = await fetch("http://localhost:8080/graphql", {
        method: "post",
        headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
        },
        body: JSON.stringify({ query }),
    });

    let json = await response.json();

    buildProductList(json.data.products);

}


let buildProductList = function (products) {
    let productList = document.querySelector("#products-list");
    productList.innerHTML = "";
    for (let product of products) {
        let tagsHtml = ""

        for (let tag of product.tags) {
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

fetchProducts();

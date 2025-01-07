let useGraphQl = () => window.location.search.includes("graphql");

let fetchFavorites = function () {
    console.log("Favorites");
    if (useGraphQl()) fetchFavoritesGraphQl(); else fetchFavoritesRest();
}

let fetchFavoritesRest = function () {
    fetch("http://localhost:8080/api/favorites/1")
        .then(response => response.json())
        .then(json => buildFavorites(json))
}

let fetchFavoritesGraphQl = async () => {
    let query = `{
      favorites(customerid: "1") {
        id
        products {
          description
          id
          image
          name
          price
          tags {
            id
            name
          }
        }
      }
    }`

    let response = await fetch("http://localhost:8080/graphql", {
        method: "post", headers: {
            "Content-Type": "application/json", Accept: "application/json",
        }, body: JSON.stringify({query}),
    });

    let json = await response.json();

    buildFavorites(json.data.favorites);

}


let buildFavorites = function (favorites) {
    let favoritesList = document.querySelector("#favorites-list");
    favoritesList.innerHTML = "";

    for (let product of favorites.products) {

        favoritesList.innerHTML += `
        <section class="product">
            <a href="../../clickable-prototype/product/product.html" class="element-group">
                <img src="../${product.image}" class="product-image" alt="Profil">
                <div class="element-group-v">
                    <p class="product-name">${product.name}</p>
                    <p>${product.description}</p>
                    <p>${product.price} €</p>
                </div>
            </a>

            <div class="element-group">
                <input type="number" class="number-step" name="points" step="1" min="0" max="50">
                <button class="cart-button">Zum Warenkorb hinzufügen</button>
            </div>
        </section>
        `
    }
}

fetchFavorites();

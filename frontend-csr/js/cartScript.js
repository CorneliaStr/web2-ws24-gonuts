let fetchCart = function(){
    fetch("http://localhost:8080/api/order/cart/1")
         .then(response => response.json())
         .then(json => buildCartProducts(json))
}

let fetchCartGraphQL = function() {
fetch( "http://localhost:8080/graphql", {
    method : `post`,
    headers: {
        'Content-Type': `application/json`,
    },
    body: JSON.stringify( {
        query: `
       query Warenkorb{
            cart {id date orderPosition { id quantity  product { name }}}
        }
        `
    } )
} )
.then( response => response.json() )
.then( response => console.log( response ) );
};

let printGraphQL = function(order){
    console.log(order);
}


let gesamtPreis = 0;

let buildCartProducts = function(order) {
    let productList = document.querySelector("#cartProducts");
    let cartTotal = document.querySelector("#cartTotal");
    let cartGesamt = document.querySelector("#cartGesamt");


    productList.innerHTML = "";
    for (let orderPosition of order.orderPosition) {
            quantityOptions = 0;
           for (let i = 0; i <= 100; i++) {
                   quantityOptions += `<option value="${i}" ${i === orderPosition.quantity ? 'selected' : ''}>${i} Stück</option>`;
                 }


        gesamtPreis += orderPosition.product.price;

        productList.innerHTML +=
                `
                    <article aria-label="${orderPosition.product.name}">
                        <img class="image-product" src="../${orderPosition.product.image}" alt="Produktbild">
                        <div class="product-details">
                            <h4>${orderPosition.product.name}</h4>
                            <p>${orderPosition.product.description}</p>
                            <span class="product-price">${orderPosition.product.price}</span>
                        </div>
                    <select class="quantity-select" aria-label="Anzahl">
                        ${quantityOptions}
                    </select>
                    </article>
                `
        
    }
    cartTotal.innerHTML += 
    `
    <span>${gesamtPreis.toFixed(2)}</span>
    `

    cartGesamt.innerHTML += 
    `
    <span>${gesamtPreis.toFixed(2)}</span>
    `

 
    
}

fetchCart();
fetchCartGraphQL();
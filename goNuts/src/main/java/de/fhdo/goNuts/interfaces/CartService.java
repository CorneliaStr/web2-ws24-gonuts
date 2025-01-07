package de.fhdo.goNuts.interfaces;

import de.fhdo.goNuts.dto.ProductDTO;

import java.util.HashMap;
import java.util.Map;

public interface CartService {

    final Map<ProductDTO, Integer> cartItems = new HashMap<>();

    public void addProduct(ProductDTO product);

    public void removeProduct(ProductDTO product);

    public Map<ProductDTO, Integer> getCartItems();

    public void clearCart();
}

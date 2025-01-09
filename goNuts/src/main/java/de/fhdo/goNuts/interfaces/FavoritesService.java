package de.fhdo.goNuts.interfaces;

import de.fhdo.goNuts.dto.FavoritesDTO;
import de.fhdo.goNuts.dto.ProductDTO;

public interface FavoritesService {
    public FavoritesDTO getFavorites(Long customerid);

    public FavoritesDTO getFavorites(String token);

    public FavoritesDTO addProductToFavorites(String token, ProductDTO product);

    public boolean addProductToFavoritesById(Long customerId, ProductDTO product);
}

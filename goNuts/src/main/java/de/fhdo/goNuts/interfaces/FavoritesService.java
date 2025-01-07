package de.fhdo.goNuts.interfaces;

import de.fhdo.goNuts.dto.FavoritesDTO;
import de.fhdo.goNuts.dto.ProductDTO;

public interface FavoritesService {
    public FavoritesDTO getFavorites(Long customerid);

    public boolean addProductToFavorites(Long customerid, ProductDTO product);
}

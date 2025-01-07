package de.fhdo.goNuts.services;

import de.fhdo.goNuts.domain.Favorites;
import de.fhdo.goNuts.domain.Product;
import de.fhdo.goNuts.dto.FavoritesDTO;
import de.fhdo.goNuts.dto.ProductDTO;
import de.fhdo.goNuts.interfaces.FavoritesService;
import de.fhdo.goNuts.mapper.FavoritesMapper;
import de.fhdo.goNuts.mapper.ProductMapper;
import de.fhdo.goNuts.repository.FavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoritesServiceImpl implements FavoritesService {
    private final FavoritesRepository favoritesRepository;
    private final FavoritesMapper favoritesMapper;
    private final ProductMapper productMapper;

    @Autowired
    public FavoritesServiceImpl(final FavoritesRepository favoritesRepository, FavoritesMapper favoritesMapper, ProductMapper productMapper) {
        this.favoritesRepository = favoritesRepository;
        this.favoritesMapper = favoritesMapper;
        this.productMapper = productMapper;
    }

    @Override
    public FavoritesDTO getFavorites(Long customerid) {
        Optional<Favorites> favorites = favoritesRepository.findById(customerid);
        return favorites.map(f -> favoritesMapper.mapEntityToDto(f)).orElse(null);
    }

    @Override
    public boolean addProductToFavorites(Long customerid, ProductDTO productDTO) {
        if (productDTO == null) {
            return false;
        }

        Optional<Favorites> favorites = favoritesRepository.findById(customerid);
        if (favorites.isEmpty()) {
            return false;
        }

        Product product = productMapper.mapDtoToEntity(productDTO);

        favorites.get().getProducts().add(product);
        favoritesRepository.save(favorites.get());
        return true;
    }
}

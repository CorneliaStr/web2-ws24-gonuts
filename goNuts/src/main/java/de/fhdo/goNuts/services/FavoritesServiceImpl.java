package de.fhdo.goNuts.services;

import de.fhdo.goNuts.domain.Favorites;
import de.fhdo.goNuts.dto.FavoritesDTO;
import de.fhdo.goNuts.interfaces.FavoritesService;
import de.fhdo.goNuts.mapper.FavoritesMapper;
import de.fhdo.goNuts.repository.FavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoritesServiceImpl implements FavoritesService {
    private final FavoritesRepository favoritesRepository;
    private final FavoritesMapper favoritesMapper;

    @Autowired
    public FavoritesServiceImpl(final FavoritesRepository favoritesRepository, FavoritesMapper favoritesMapper) {
        this.favoritesRepository = favoritesRepository;
        this.favoritesMapper = favoritesMapper;
    }

    @Override
    public FavoritesDTO getFavorites(Long id) {
        Optional<Favorites> favorites = favoritesRepository.findById(id);
        return favorites.map(f -> favoritesMapper.mapEntityToDto(f)).orElse(null);
    }
}

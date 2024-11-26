package de.fhdo.goNuts.services;

import de.fhdo.goNuts.domain.Favorites;
import de.fhdo.goNuts.interfaces.FavoritesService;
import de.fhdo.goNuts.repository.FavoritesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoritesServiceImpl implements FavoritesService {
    private final FavoritesRepository favoritesRepository;

    public FavoritesServiceImpl(final FavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
    }

    @Override
    public Favorites getFavorites(Long id) {
        Optional<Favorites> favorites = favoritesRepository.findById(id);
        return favorites.orElse(null);
    }
}

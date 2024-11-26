package de.fhdo.goNuts.interfaces;

import de.fhdo.goNuts.domain.Favorites;
import de.fhdo.goNuts.domain.Product;
import de.fhdo.goNuts.repository.FavoritesRepository;
import de.fhdo.goNuts.services.FavoritesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface FavoritesService {
    public Favorites getFavorites(Long id);
}

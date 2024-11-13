package de.fhdo.goNuts.repository;

import de.fhdo.goNuts.domain.Favorites;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritesRepository extends CrudRepository<Favorites,Long> {
}

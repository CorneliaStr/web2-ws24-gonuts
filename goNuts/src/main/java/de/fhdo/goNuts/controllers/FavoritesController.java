package de.fhdo.goNuts.controllers;

import de.fhdo.goNuts.dto.FavoritesDTO;
import de.fhdo.goNuts.interfaces.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/favorites")
@RestController
public class FavoritesController {
    private final FavoritesService favoritesService;

    @Autowired
    public FavoritesController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @GetMapping("/{id}")
    public FavoritesDTO getFavorites(@PathVariable Long id) {
        return favoritesService.getFavorites(id);
    }
}

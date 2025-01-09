package de.fhdo.goNuts.controllers;

import de.fhdo.goNuts.dto.FavoritesDTO;
import de.fhdo.goNuts.interfaces.FavoritesService;
import de.fhdo.goNuts.requestObjects.AddProductToFavoritesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @GetMapping(path = "/{customerid}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public FavoritesDTO getFavorites(@PathVariable Long customerid) {
        return favoritesService.getFavorites(customerid);
    }

    @PostMapping(path = "/{addProduct}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public FavoritesDTO addProduct(@RequestBody AddProductToFavoritesRequest request) {
        return favoritesService.addProductToFavorites(request.getToken(), request.getProduct());
    }
}

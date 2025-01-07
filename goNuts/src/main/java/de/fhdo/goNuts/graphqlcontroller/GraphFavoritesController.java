package de.fhdo.goNuts.graphqlcontroller;

import de.fhdo.goNuts.dto.FavoritesDTO;
import de.fhdo.goNuts.dto.ProductDTO;
import de.fhdo.goNuts.interfaces.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphFavoritesController {
    private final FavoritesService favoritesService;

    @Autowired
    public GraphFavoritesController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @QueryMapping("favorites")
    public FavoritesDTO getFavorites(@Argument("customerid") Long userid) {
        return favoritesService.getFavorites(userid);
    }

    @MutationMapping("addProductToFavorites")
    public boolean addProduct(@Argument("customerId") Long customerId, @Argument("product") ProductDTO product) {
        return favoritesService.addProductToFavorites(customerId, product);
    }
}

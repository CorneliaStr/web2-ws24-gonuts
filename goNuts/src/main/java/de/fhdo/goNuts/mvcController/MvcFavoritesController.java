package de.fhdo.goNuts.mvcController;

import de.fhdo.goNuts.domain.Order;
import de.fhdo.goNuts.domain.OrderPosition;
import de.fhdo.goNuts.domain.Product;
import de.fhdo.goNuts.interfaces.FavoritesService;
import de.fhdo.goNuts.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("favorites")
public class MvcFavoritesController {
    private final FavoritesService favoritesService;

    @Autowired
    public MvcFavoritesController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @GetMapping
    public String showFavorites(Model model, @RequestParam long id) {
        model.addAttribute("favorites", favoritesService.getFavorites(id));
        return "favorites";
    }
}

package de.fhdo.goNuts.mapper;

import de.fhdo.goNuts.domain.Favorites;
import de.fhdo.goNuts.domain.Product;
import de.fhdo.goNuts.dto.FavoritesDTO;
import de.fhdo.goNuts.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FavoritesMapper implements Mapper<Favorites, FavoritesDTO> {
    private final ProductMapper productMapper;

    public FavoritesMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public FavoritesDTO mapEntityToDto(Favorites entity) {
        if (entity == null) return null;

        List<ProductDTO> productDTOs = entity.getProducts().stream().map(p -> productMapper.mapEntityToDto(p)).collect(Collectors.toList());
        return new FavoritesDTO(entity.getId(), null, productDTOs);
    }

    @Override
    public Favorites mapDtoToEntity(FavoritesDTO dto) {
        if (dto == null) return null;

        List<Product> products = dto.getProducts().stream().map(p -> productMapper.mapDtoToEntity(p)).collect(Collectors.toList());
        Favorites favorites = new Favorites(products);
        favorites.setId(dto.getId());

        return favorites;
    }
}

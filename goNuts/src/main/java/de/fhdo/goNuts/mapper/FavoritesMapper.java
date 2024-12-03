package de.fhdo.goNuts.mapper;

import de.fhdo.goNuts.domain.Customer;
import de.fhdo.goNuts.domain.Favorites;
import de.fhdo.goNuts.domain.Product;
import de.fhdo.goNuts.dto.CustomerDTO;
import de.fhdo.goNuts.dto.FavoritesDTO;
import de.fhdo.goNuts.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FavoritesMapper implements Mapper<Favorites, FavoritesDTO> {
    private final ProductMapper productMapper;
    private final CustomerMapper customerMapper;

    public FavoritesMapper(ProductMapper productMapper, CustomerMapper customerMapper) {
        this.productMapper = productMapper;
        this.customerMapper = customerMapper;
    }

    @Override
    public FavoritesDTO mapEntityToDto(Favorites entity) {
        if (entity == null) return null;

        CustomerDTO customerDTOs = customerMapper.mapEntityToDto(entity.getCustomer());
        List<ProductDTO> productDTOs = entity.getProducts().stream().map(p -> productMapper.mapEntityToDto(p)).collect(Collectors.toList());
        return new FavoritesDTO(entity.getId(), customerDTOs, productDTOs);
    }

    @Override
    public Favorites mapDtoToEntity(FavoritesDTO dto) {
        if (dto == null) return null;

        Customer customers = customerMapper.mapDtoToEntity(dto.getCustomer());
        List<Product> products = dto.getProducts().stream().map(p -> productMapper.mapDtoToEntity(p)).collect(Collectors.toList());
        Favorites favorites = new Favorites(customers, products);
        favorites.setId(dto.getId());

        return favorites;
    }
}

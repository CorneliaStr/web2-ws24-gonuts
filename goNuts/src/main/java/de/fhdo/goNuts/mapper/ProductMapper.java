package de.fhdo.goNuts.mapper;

import de.fhdo.goNuts.domain.Product;
import de.fhdo.goNuts.domain.Tag;
import de.fhdo.goNuts.dto.ProductDTO;
import de.fhdo.goNuts.dto.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper implements Mapper<Product, ProductDTO> {
    private final TagMapper tagMapper;

    @Autowired
    public ProductMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Override
    public ProductDTO mapEntityToDto(Product entity) {
        if (entity == null) return null;

        List<TagDTO> tagDTOs = entity.getTags().stream().map(t -> tagMapper.mapEntityToDto(t)).collect(Collectors.toList());
        return new ProductDTO(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice(), entity.getImage(), tagDTOs);
    }

    @Override
    public Product mapDtoToEntity(ProductDTO dto) {
        if (dto == null) return null;

        List<Tag> tags = dto.getTags().stream().map(t -> tagMapper.mapDtoToEntity(t)).collect(Collectors.toList());
        Product entity = new Product(dto.getName(), dto.getDescription(), dto.getPrice(), dto.getImage(), tags);
        entity.setId(dto.getId());

        return entity;
    }
}

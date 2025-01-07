package de.fhdo.goNuts.mapper;

import de.fhdo.goNuts.domain.Order;
import de.fhdo.goNuts.domain.OrderPosition;
import de.fhdo.goNuts.domain.Product;
import de.fhdo.goNuts.dto.CustomerDTO;
import de.fhdo.goNuts.dto.OrderDTO;
import de.fhdo.goNuts.dto.OrderPositionDTO;
import de.fhdo.goNuts.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderPositionMapper implements Mapper<OrderPosition, OrderPositionDTO> {
    private final ProductMapper productMapper;

    @Autowired
    public OrderPositionMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public OrderPositionDTO mapEntityToDto(OrderPosition entity) {
        if (entity == null) return null;

        ProductDTO productDTO = productMapper.mapEntityToDto(entity.getProduct());
        return new OrderPositionDTO(entity.getId(), null, productDTO,entity.getQuantity());
    }

    @Override
    public OrderPosition mapDtoToEntity(OrderPositionDTO dto) {
        if (dto == null) return null;

        Product product = productMapper.mapDtoToEntity(dto.getProduct());
        OrderPosition orderPosition = new OrderPosition(null, product);
        orderPosition.setId(dto.getId());
        orderPosition.setQuantity(dto.getQuantity());
        return orderPosition;
    }
}

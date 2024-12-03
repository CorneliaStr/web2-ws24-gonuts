package de.fhdo.goNuts.mapper;

import de.fhdo.goNuts.domain.Customer;
import de.fhdo.goNuts.domain.Order;
import de.fhdo.goNuts.domain.OrderPosition;
import de.fhdo.goNuts.dto.CustomerDTO;
import de.fhdo.goNuts.dto.OrderDTO;
import de.fhdo.goNuts.dto.OrderPositionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper implements Mapper<Order, OrderDTO> {
    private final OrderPositionMapper orderPositionMapper;
    private final CustomerMapper customerMapper;

    @Autowired
    public OrderMapper(CustomerMapper customerMapper, OrderPositionMapper orderPositionMapper) {
        this.customerMapper = customerMapper;
        this.orderPositionMapper = orderPositionMapper;
    }

    @Override
    public OrderDTO mapEntityToDto(Order entity) {
        if (entity == null) return null;

        List<OrderPositionDTO> orderPositionDTOs = entity.getOrderPosition().stream().map(o -> orderPositionMapper.mapEntityToDto(o)).collect(Collectors.toList());
        CustomerDTO customerDTO = customerMapper.mapEntityToDto(entity.getCustomer());
        return new OrderDTO(entity.getId(), entity.getDate(), orderPositionDTOs, customerDTO);
    }

    @Override
    public Order mapDtoToEntity(OrderDTO dto) {
        if (dto == null) return null;

        List<OrderPosition> orderPositions = dto.getOrderPosition().stream().map(o -> orderPositionMapper.mapDtoToEntity(o)).collect(Collectors.toList());
        Customer customer = customerMapper.mapDtoToEntity(dto.getCustomer());
        Order order = new Order(dto.getDate(), orderPositions, customer);
        order.setId(dto.getId());

        return order;
    }
}
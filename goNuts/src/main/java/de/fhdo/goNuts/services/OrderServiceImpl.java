package de.fhdo.goNuts.services;

import de.fhdo.goNuts.domain.Customer;
import de.fhdo.goNuts.domain.Order;
import de.fhdo.goNuts.domain.OrderPosition;
import de.fhdo.goNuts.dto.OrderDTO;
import de.fhdo.goNuts.dto.OrderPositionDTO;
import de.fhdo.goNuts.dto.ProductDTO;
import de.fhdo.goNuts.interfaces.CustomerService;
import de.fhdo.goNuts.interfaces.OrderService;
import de.fhdo.goNuts.mapper.OrderMapper;
import de.fhdo.goNuts.mapper.ProductMapper;
import de.fhdo.goNuts.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final CustomerService customerService;

    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, CustomerService customerService, ProductMapper productMapper) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.orderMapper = orderMapper;
        this.productMapper = productMapper;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(o -> orderMapper.mapEntityToDto(o)).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(o -> orderMapper.mapEntityToDto(o)).orElse(null);
    }

    @Override
    public OrderDTO getCart(String token) {
        Customer customer = customerService.getCustomerEntityByToken(token);
        if (customer == null) {
            return null;
        }

        Order cart = getCart(customer);
        return orderMapper.mapEntityToDto(cart);
    }

    public OrderDTO getCart(Long id) {
        Customer customer = customerService.getCustomerEntityById(id);
        if (customer == null) {
            logger.error("Es konnte kein Kunde zur ID gefunden werden.");
            return null;
        }

        Order cart = getCart(customer);
        return orderMapper.mapEntityToDto(cart);
    }

    @Override
    public List<OrderDTO> getOrders(String token) {
        Customer customer = this.customerService.getCustomerEntityByToken(token);
        Optional<List<Order>> orderOptional = orderRepository.findByCustomerAndDateIsNotNull(customer);
        if(orderOptional.isPresent()){
            List<OrderDTO> orders = new ArrayList<>();
            for(Order order : orderOptional.get()){
                OrderDTO orderDTO = new OrderDTO();
                orderDTO = orderMapper.mapEntityToDto(order);
                orders.add(orderDTO);
            }
            return orders;
        }
        return null;
    }

    private Order getCart(Customer customer) {
        Optional<Order> orderOptional = orderRepository.findByCustomerAndDateIsNull(customer);

        if (orderOptional.isPresent()) {
            return orderOptional.get();
        }
        Order newCart = new Order();
        newCart.setCustomer(customer);
        return orderRepository.save(newCart);
    }

    @Override
    public OrderDTO addProductToOrder(ProductDTO productDTO, Long quantity, String token) {
        Customer customer = customerService.getCustomerEntityByToken(token);
        if (customer == null) {
            return null;
        }

        return addProductToOrder(productDTO, quantity, customer);
    }

    @Override
    public OrderDTO addProductToOrderWithoutToken(ProductDTO productDTO, Long quantity) {
        Customer customer = customerService.getCustomerEntityById(1);
        if (customer == null) {
            return null;
        }

        return addProductToOrder(productDTO, quantity, customer);
    }

    private OrderDTO addProductToOrder(ProductDTO productDTO, Long quantity, Customer customer) {
        // Wenn kein Warenkrob (Order ohne Datum) existiert, wird ein neuer angelegt
        Optional<Order> orderOptional = orderRepository.findByCustomerAndDateIsNull(customer);
        Order order;
        if (orderOptional.isPresent()) {
            order = orderOptional.get();
        } else {
            Order newOrder = new Order();
            newOrder.setCustomer(customer);
            order = orderRepository.save(newOrder);
        }

        //Falls schon vorhanden +quantity
        for (OrderPosition positon : order.getOrderPosition()) {
            if (positon.getProduct().getId().equals(productDTO.getId())) {
                positon.setQuantity(positon.getQuantity() + quantity);
                order = orderRepository.save(order);
                return orderMapper.mapEntityToDto(order);
            }
        }

        //Ansonsten neue Position anlegen
        OrderPosition newOrderPosition = new OrderPosition();
        newOrderPosition.setOrder(order);
        newOrderPosition.setProduct(productMapper.mapDtoToEntity(productDTO));
        newOrderPosition.setQuantity(quantity);
        List<OrderPosition> orderPositionList = order.getOrderPosition();
        orderPositionList.add(newOrderPosition);
        order.setOrderPosition(orderPositionList);
        order = orderRepository.save(order);
        return orderMapper.mapEntityToDto(order);
    }

    @Override
    public void updateOrder(OrderDTO orderDTO) {
        Order order = orderMapper.mapDtoToEntity(orderDTO);
        for (OrderPosition orderPosition : order.getOrderPosition()) {
            orderPosition.setOrder(order);
        }
        orderRepository.save(order);
    }

    @Override
    public OrderDTO deleteOrderPosition(OrderPositionDTO orderPositionDTO, Long orderID) {
        System.out.println();
        Order order = orderRepository.findById(orderID).get();
        order.getOrderPosition().removeIf(op -> op.getId().equals(orderPositionDTO.getId()));
        orderRepository.save(order);
        return orderMapper.mapEntityToDto(orderRepository.findById(order.getId()).get());

    }

}

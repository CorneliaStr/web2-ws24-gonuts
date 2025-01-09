package de.fhdo.goNuts.services;

import de.fhdo.goNuts.domain.Customer;
import de.fhdo.goNuts.domain.Order;
import de.fhdo.goNuts.domain.OrderPosition;
import de.fhdo.goNuts.dto.OrderDTO;
import de.fhdo.goNuts.dto.ProductDTO;
import de.fhdo.goNuts.interfaces.AuthService;
import de.fhdo.goNuts.interfaces.OrderService;
import de.fhdo.goNuts.mapper.OrderMapper;
import de.fhdo.goNuts.mapper.ProductMapper;
import de.fhdo.goNuts.repository.CustomerRepository;
import de.fhdo.goNuts.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    private final AuthService authService;

    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, CustomerRepository customerRepository, ProductMapper productMapper, AuthService authService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderMapper = orderMapper;
        this.productMapper = productMapper;
        this.authService = authService;
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
    public OrderDTO getCart() {
        Optional<Customer> customer = customerRepository.findById(1L);
        if (customer.isEmpty()) {
            logger.error("Email konnte nicht aus dem Token gelesen werden.");
            return null;
        }

        Order cart = getCart(customer.get());
        return orderMapper.mapEntityToDto(cart);
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
        Optional<String> email = authService.extractEmail(token);
        if (email.isEmpty()) {
            logger.error("Email konnte nicht aus dem Token gelesen werden.");
            return null;
        }

        Optional<Customer> customer = customerRepository.findByAccount_Email(email.get());
        if (customer.isEmpty()) {
            logger.error("Es wurde kein passender Kunde zum Account " + email.get() + " gefunden.");
            return null;
        }

        // Wenn kein Warenkrob (Order ohne Datum) existiert, wird ein neuer angelegt
        Optional<Order> orderOptional = orderRepository.findByCustomerAndDateIsNull(customer.get());
        Order order;
        if (orderOptional.isPresent()) {
            order = orderOptional.get();
        } else {
            Order newOrder = new Order();
            newOrder.setCustomer(customer.get());
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

}

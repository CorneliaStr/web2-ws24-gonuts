package de.fhdo.goNuts.services;

import de.fhdo.goNuts.domain.Customer;
import de.fhdo.goNuts.domain.Order;
import de.fhdo.goNuts.domain.OrderPosition;
import de.fhdo.goNuts.domain.Product;
import de.fhdo.goNuts.dto.OrderDTO;
import de.fhdo.goNuts.dto.OrderPositionDTO;
import de.fhdo.goNuts.dto.ProductDTO;
import de.fhdo.goNuts.interfaces.OrderService;
import de.fhdo.goNuts.mapper.OrderMapper;
import de.fhdo.goNuts.mapper.ProductMapper;
import de.fhdo.goNuts.repository.CustomerRepository;
import de.fhdo.goNuts.repository.OrderPositionRepository;
import de.fhdo.goNuts.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    private final OrderPositionRepository orderPositionRepository;
    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, CustomerRepository customerRepository, ProductMapper productMapper, OrderPositionRepository orderPositionRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderMapper = orderMapper;
        this.productMapper = productMapper;
        this.orderPositionRepository = orderPositionRepository;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(o -> orderMapper.mapEntityToDto(o)).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrder(Long id) {
        Optional<Order> order = this.orderRepository.findById(id);
        return order.map(o -> orderMapper.mapEntityToDto(o)).orElse(null);
    }

    @Override
    public OrderDTO getCart(){
        Optional<Customer> customer = this.customerRepository.findById(1L);
        //Optional<Order> order = this.orderRepository.findById(1L);
        Optional<Order> order = this.orderRepository.findByCustomerAndDateIsNull(customer);

        if(order.isEmpty()){
            Order newCart = new Order();
            newCart.setCustomer(customer.get());
            this.orderRepository.save(newCart);
            order = this.orderRepository.findByCustomerAndDateIsNull(customer);
        }
        return order.map(o -> orderMapper.mapEntityToDto(o)).orElse(null);
    }

    @Override
    public void  addProductToOrder(ProductDTO productDTO) {

        Optional<Order> order = this.orderRepository.findByCustomerAndDateIsNull(this.customerRepository.findById(1L));

        //Falls schon vorhanden +1
        for(OrderPosition positon : order.get().getOrderPosition()){
            if(positon.getProduct().getId().equals(productDTO.getId())){
                positon.setQuantity(positon.getQuantity()+1);
                this.orderRepository.save(order.get());
                return;
            }
        }

        //Ansonsten neue Position anlegen
        OrderPosition newOrderPosition = new OrderPosition();
        newOrderPosition.setOrder(order.get());
        newOrderPosition.setProduct(this.productMapper.mapDtoToEntity(productDTO));
        newOrderPosition.setQuantity(1L);
        List<OrderPosition> orderPositionList = order.get().getOrderPosition();
        orderPositionList.add(newOrderPosition);
        order.get().setOrderPosition(orderPositionList);
        this.orderRepository.save(order.get());

    }

    @Override
    public void updateOrder(OrderDTO orderDTO) {
        Order order = orderMapper.mapDtoToEntity(orderDTO);
        for(OrderPosition orderPosition : order.getOrderPosition()){
            orderPosition.setOrder(order);
        }
        this.orderRepository.save(order);
    }

}

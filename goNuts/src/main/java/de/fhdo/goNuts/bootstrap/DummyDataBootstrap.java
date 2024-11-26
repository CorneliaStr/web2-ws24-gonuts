package de.fhdo.goNuts.bootstrap;

import de.fhdo.goNuts.domain.Order;
import de.fhdo.goNuts.domain.OrderPosition;
import de.fhdo.goNuts.domain.Product;
import de.fhdo.goNuts.dto.ProductDTO;
import de.fhdo.goNuts.repository.OrderRepository;
import de.fhdo.goNuts.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class DummyDataBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    @Autowired
    public DummyDataBootstrap(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData(){
        Product product1 = new Product("Product1", "Produkt1", 1.50, "/images/placeholder.jpg");
        Product product2 = new Product("Product2", "Produkt2", 24.99, "/images/placeholder.jpg");
        Product product3 = new Product("Product3", "Produkt3", 120.0, "/images/placeholder.jpg");
        Product product4 = new Product("Product4", "Produkt4", 0.25, "/images/placeholder.jpg");
        Product product5 = new Product("Product5", "Produkt5", 1111.99, "/images/placeholder.jpg");
        Product product6 = new Product("Product6", "Produkt6", 0.99, "/images/placeholder.jpg");
        Product product7 = new Product("Product7", "Produkt7", 1.50, "/images/placeholder.jpg");
        Product product8 = new Product("Product7", "Produkt8", 1.50, "/images/placeholder.jpg");

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);
        productRepository.save(product7);
        productRepository.save(product8);


        // Bestellung erstellen
        Order order1 = new Order();
        order1.setDate(new Date());

        // Bestellpositionen erstellen
        OrderPosition orderPosition1 = new OrderPosition();
        orderPosition1.setProduct(product1);
        orderPosition1.setOrder(order1);

        OrderPosition orderPosition2 = new OrderPosition();
        orderPosition2.setProduct(product2);
        orderPosition2.setOrder(order1);

        // Bestellpositionen der Bestellung hinzufügen
        order1.setOrderPosition(Arrays.asList(orderPosition1, orderPosition2));

        // Bestellung speichern (kaskadiert Bestellpositionen)
        orderRepository.save(order1);
    }
}

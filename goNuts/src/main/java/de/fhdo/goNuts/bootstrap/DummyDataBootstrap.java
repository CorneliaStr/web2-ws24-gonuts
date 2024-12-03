package de.fhdo.goNuts.bootstrap;

import de.fhdo.goNuts.domain.*;
import de.fhdo.goNuts.interfaces.FavoritesService;
import de.fhdo.goNuts.mapper.*;
import de.fhdo.goNuts.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

import java.util.Arrays;
import java.util.Date;

@Component
public class DummyDataBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final TagRepository tagRepository;
    private final FavoritesRepository favoritesRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public DummyDataBootstrap(ProductRepository productRepository, TagRepository tagRepository, OrderRepository orderRepository, FavoritesRepository favoritesRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.tagRepository = tagRepository;
        this.favoritesRepository = favoritesRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        Tag tag1 = tagRepository.saveAndFlush(new Tag("Nuss-Mix"));
        Tag tag2 = tagRepository.saveAndFlush(new Tag("Probierpaket"));
        Tag tag3 = tagRepository.saveAndFlush(new Tag("Gesalzen"));
        Tag tag4 = tagRepository.saveAndFlush(new Tag("Süß"));
        Tag tag5 = tagRepository.saveAndFlush(new Tag("Weihnachtlich"));
        Tag tag6 = tagRepository.saveAndFlush(new Tag("Schokolade"));

        Product product1 = new Product("Product1", "Produkt1", 1.50, "/images/placeholder.jpg", List.of(tag1));
        Product product2 = new Product("Product2", "Produkt2", 24.99, "/images/placeholder.jpg", List.of(tag2, tag3));
        Product product3 = new Product("Product3", "Produkt3", 120.0, "/images/placeholder.jpg", List.of(tag3, tag4));
        Product product4 = new Product("Product4", "Produkt4", 0.25, "/images/placeholder.jpg", List.of(tag4, tag5));
        Product product5 = new Product("Product5", "Produkt5", 1111.99, "/images/placeholder.jpg", List.of(tag5, tag6));
        Product product6 = new Product("Product6", "Produkt6", 0.99, "/images/placeholder.jpg", List.of(tag5, tag1));
        Product product7 = new Product("Product7", "Produkt7", 1.50, "/images/placeholder.jpg", List.of(tag2, tag6));
        Product product8 = new Product("Product8", "Produkt8", 1.50, "/images/placeholder.jpg", List.of(tag4));

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);
        productRepository.save(product7);
        productRepository.save(product8);

        Customer customer1 = new Customer("Bernd", "Bunt", null, "Dortmund", null);
        customerRepository.save(customer1);

        // Bestellung erstellen
        Order order1 = new Order();
        order1.setDate(new Date());
        order1.setCustomer(customer1);

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

        Favorites favorites1 = new Favorites();
        favorites1.setCustomer(customer1);
        favorites1.setProducts(Arrays.asList(product1, product2, product4, product7));

        favoritesRepository.save(favorites1);
    }
}

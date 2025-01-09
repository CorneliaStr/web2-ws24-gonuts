package de.fhdo.goNuts.bootstrap;

import de.fhdo.goNuts.domain.*;
import de.fhdo.goNuts.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class DummyDataBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final TagRepository tagRepository;
    private final FavoritesRepository favoritesRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public DummyDataBootstrap(ProductRepository productRepository, TagRepository tagRepository, OrderRepository orderRepository, FavoritesRepository favoritesRepository, AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.tagRepository = tagRepository;
        this.favoritesRepository = favoritesRepository;
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        Tag tag1 = tagRepository.saveAndFlush(new Tag("Mandeln"));
        Tag tag2 = tagRepository.saveAndFlush(new Tag("Nuss-Mix"));
        Tag tag3 = tagRepository.saveAndFlush(new Tag("Macadamia"));
        Tag tag4 = tagRepository.saveAndFlush(new Tag("Erdnüsse"));
        Tag tag5 = tagRepository.saveAndFlush(new Tag("Luxus"));
        Tag tag6 = tagRepository.saveAndFlush(new Tag("Haselnüsse"));
        Tag tag7 = tagRepository.saveAndFlush(new Tag("Walnüsse"));
        Tag tag8 = tagRepository.saveAndFlush(new Tag("Cashews"));
        Tag tag9 = tagRepository.saveAndFlush(new Tag("Karamellisiert"));
        Tag tag10 = tagRepository.saveAndFlush(new Tag("Geschenkbox"));

        Product product1 = new Product("Klassische Mandeln", "Knackige Mandeln in Premium-Qualität", 1.50, "/images/placeholder.jpg", List.of(tag1));
        Product product2 = new Product("Deluxe Nussmix", "Exquisite Mischung aus Mandeln, Cashews und Haselnüssen", 24.99, "/images/placeholder.jpg", List.of(tag2, tag6));
        Product product3 = new Product("Goldene Macadamia", "Edle Macadamia-Nüsse für besondere Momente", 120.0, "/images/placeholder.jpg", List.of(tag3, tag5));
        Product product4 = new Product("Erdnuss-Genuss", "Geröstete und leicht gesalzene Erdnüsse", 0.25, "/images/placeholder.jpg", List.of(tag4));
        Product product5 = new Product("Ultimativer Nuss-Schatz", "Luxuriöses Nuss-Set mit seltenen und exklusiven Sorten", 1111.99, "/images/placeholder.jpg", List.of(tag5, tag2));
        Product product6 = new Product("Cashew-Knusper", "Cremige Cashewkerne mit feiner Röstnote", 0.99, "/images/placeholder.jpg", List.of(tag2, tag1));
        Product product7 = new Product("Haselnuss-Himmel", "Aromatische Haselnüsse für den perfekten Snack", 1.50, "/images/placeholder.jpg", List.of(tag6));
        Product product8 = new Product("Walnuss-Wunder", "Herzhafte Walnüsse voller Geschmack und Energie", 1.50, "/images/placeholder.jpg", List.of(tag7));

        Product product9 = new Product("Cashew Deluxe", "Große, geröstete Cashewkerne in Premium-Qualität", 3.50, "/images/placeholder.jpg", List.of(tag8, tag5));
        Product product10 = new Product("Karamell-Mix", "Karamellisierte Nüsse: Mandeln, Haselnüsse und Cashews", 5.99, "/images/placeholder.jpg", List.of(tag9, tag2));
        Product product11 = new Product("Schokoladen-Nüsse", "Feinste Nüsse mit Vollmilch- und Zartbitterschokolade überzogen", 7.50, "/images/placeholder.jpg", List.of(tag6, tag10));
        Product product12 = new Product("Weihnachtlicher Nussmix", "Festlicher Mix mit Mandeln, Haselnüssen und Walnüssen, verfeinert mit Zimt", 6.99, "/images/placeholder.jpg", List.of(tag5, tag10));
        Product product13 = new Product("Nuss-Geschenkbox", "Edle Geschenkbox mit einer Auswahl an Nüssen und Trockenfrüchten", 29.99, "/images/placeholder.jpg", List.of(tag5, tag10));
        Product product14 = new Product("Pikante Erdnüsse", "Erdnüsse mit einer scharfen Paprika-Würzmischung", 2.50, "/images/placeholder.jpg", List.of(tag4));
        Product product15 = new Product("Crunchy Walnüsse", "Geröstete Walnüsse mit einem Hauch von Salz", 3.00, "/images/placeholder.jpg", List.of(tag7, tag5));
        Product product16 = new Product("Protein-Mix", "Energiegeladene Mischung aus Nüssen und Kernen", 4.99, "/images/placeholder.jpg", List.of(tag2, tag8));


        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);
        productRepository.save(product7);
        productRepository.save(product8);
        productRepository.save(product9);
        productRepository.save(product10);
        productRepository.save(product11);
        productRepository.save(product12);
        productRepository.save(product13);
        productRepository.save(product14);
        productRepository.save(product15);
        productRepository.save(product16);

        Account account1 = new Account("admin@gmail.com", "admin", true);
        accountRepository.save(account1);
        Account account2 = new Account("user@gmail.com", "user", false);
        accountRepository.save(account2);

        Favorites favorites1 = new Favorites();
        favorites1.setProducts(Arrays.asList(product1, product2, product4, product7));
        Favorites favorites2 = new Favorites();

        Customer customer1 = new Customer("Bernd", "Bunt", LocalDate.of(1990, 2, 12), "Dortmund", account1, favorites1);
        customerRepository.save(customer1);
        Customer customer2 = new Customer("Anna", "Adler", LocalDate.of(1980, 5, 17), "Bochum", account2, favorites2);
        customerRepository.save(customer2);

        // Bestellung erstellen
        Order order1 = new Order();
        order1.setDate(new Date());
        order1.setCustomer(customer1);

        // Bestellpositionen erstellen
        OrderPosition orderPosition1 = new OrderPosition();
        orderPosition1.setProduct(product1);
        orderPosition1.setOrder(order1);
        orderPosition1.setQuantity(1L);

        OrderPosition orderPosition2 = new OrderPosition();
        orderPosition2.setProduct(product2);
        orderPosition2.setOrder(order1);
        orderPosition2.setQuantity(2L);

        // Bestellpositionen der Bestellung hinzufügen
        order1.setOrderPosition(Arrays.asList(orderPosition1, orderPosition2));

        // Bestellung speichern (kaskadiert Bestellpositionen)
        orderRepository.save(order1);


        // Bestellung erstellen
        Order order2 = new Order();
        order2.setCustomer(customer1);

        // Bestellpositionen erstellen
        OrderPosition orderPosition3 = new OrderPosition();
        orderPosition3.setProduct(product1);
        orderPosition3.setOrder(order2);
        orderPosition3.setQuantity(3L);

        OrderPosition orderPosition4 = new OrderPosition();
        orderPosition4.setProduct(product2);
        orderPosition4.setOrder(order2);
        orderPosition4.setQuantity(4L);

        // Bestellpositionen der Bestellung hinzufügen
        order2.setOrderPosition(Arrays.asList(orderPosition3, orderPosition4));

        // Bestellung speichern (kaskadiert Bestellpositionen)
        orderRepository.save(order2);
    }
}

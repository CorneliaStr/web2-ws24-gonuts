package de.fhdo.goNuts.bootstrap;

import de.fhdo.goNuts.domain.Product;
import de.fhdo.goNuts.domain.Tag;
import de.fhdo.goNuts.repository.ProductRepository;
import de.fhdo.goNuts.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DummyDataBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final ProductRepository productRepository;
    private final TagRepository tagRepository;

    @Autowired
    public DummyDataBootstrap(ProductRepository productRepository, TagRepository tagRepository) {
        this.productRepository = productRepository;
        this.tagRepository = tagRepository;
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
        Product product8 = new Product("Product7", "Produkt8", 1.50, "/images/placeholder.jpg", List.of(tag4));

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);
        productRepository.save(product7);
        productRepository.save(product8);
    }

}

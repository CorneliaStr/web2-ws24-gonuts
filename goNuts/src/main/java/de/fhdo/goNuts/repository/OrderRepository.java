package de.fhdo.goNuts.repository;

import de.fhdo.goNuts.domain.Customer;
import de.fhdo.goNuts.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByCustomerAndDateIsNull(Customer customer);
}

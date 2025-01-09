package de.fhdo.goNuts.services;

import de.fhdo.goNuts.domain.Customer;
import de.fhdo.goNuts.dto.CustomerDTO;
import de.fhdo.goNuts.interfaces.AuthService;
import de.fhdo.goNuts.interfaces.CustomerService;
import de.fhdo.goNuts.mapper.CustomerMapper;
import de.fhdo.goNuts.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AuthService authService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, AuthService authService) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.authService = authService;
    }

    @Override
    public CustomerDTO getCustomerById(long id) {
        Customer customer = getCustomerEntityById(id);
        if (customer == null) {
            return null;
        }
        return customerMapper.mapEntityToDto(customer);
    }

    @Override
    public CustomerDTO getCustomerByToken(String token) {
        Customer customer = getCustomerEntityByToken(token);
        if (customer == null) {
            return null;
        }
        return customerMapper.mapEntityToDto(customer);

    }

    @Override
    public Customer getCustomerEntityById(long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer getCustomerEntityByToken(String token) {
        Optional<String> email = authService.extractEmail(token);
        if (email.isEmpty()) {
            logger.error("Es konnte keine Email zum Token extrahiert werden.");
            return null;
        }
        return customerRepository.findByAccount_Email(email.get()).orElse(null);
    }
}

package de.fhdo.goNuts.services;

import de.fhdo.goNuts.domain.Customer;
import de.fhdo.goNuts.dto.CustomerDTO;
import de.fhdo.goNuts.interfaces.CustomerService;
import de.fhdo.goNuts.mapper.CustomerMapper;
import de.fhdo.goNuts.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDTO getCustomer(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        System.out.println(customer.get().getName());
        return customer.stream()
                .map(c -> customerMapper.mapEntityToDto(c))
                .findAny()
                .orElse(null);
    }
}

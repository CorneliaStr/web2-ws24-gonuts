package de.fhdo.goNuts.services;

import de.fhdo.goNuts.dto.CustomerDTO;
import de.fhdo.goNuts.interfaces.CustomerService;
import de.fhdo.goNuts.mapper.CustomerMapper;
import de.fhdo.goNuts.repository.CustomerRepository;
import de.fhdo.goNuts.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDTO getCustomerById(long id) {
        return customerRepository.findById(id)
                .stream()
                .map(customerMapper::mapEntityToDto)
                .findAny()
                .orElse(null);
    }

    @Override
    public CustomerDTO getCustomerByToken(String token) {
        if (jwtUtil.validateToken(token)) {
            String email = jwtUtil.extractEmail(token);

            return customerRepository.findByAccount_Email(email)
                    .stream()
                    .map(customerMapper::mapEntityToDto)
                    .findAny()
                    .orElse(null);
        }
        return null;
    }
}

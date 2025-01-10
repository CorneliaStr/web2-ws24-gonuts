package de.fhdo.goNuts.interfaces;

import de.fhdo.goNuts.domain.Customer;
import de.fhdo.goNuts.dto.CustomerDTO;

public interface CustomerService {
    public CustomerDTO getCustomerById(long id);

    public CustomerDTO getCustomerByToken(String token);

    public Customer getCustomerEntityById(long id);

    public Customer getCustomerEntityByToken(String token);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);
}

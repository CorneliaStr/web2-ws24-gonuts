package de.fhdo.goNuts.interfaces;

import de.fhdo.goNuts.dto.CustomerDTO;

public interface CustomerService {
    public CustomerDTO getCustomerById(long id);

    public CustomerDTO getCustomerByToken(String token);
}

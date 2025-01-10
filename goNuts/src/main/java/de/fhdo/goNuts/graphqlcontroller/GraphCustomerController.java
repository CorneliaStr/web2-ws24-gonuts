package de.fhdo.goNuts.graphqlcontroller;

import de.fhdo.goNuts.dto.CustomerDTO;
import de.fhdo.goNuts.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
public class GraphCustomerController {
    private final CustomerService customerService;

    @Autowired
    public GraphCustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @QueryMapping("customer")
    public CustomerDTO getCustomer(@Argument Long id) {
        return customerService.getCustomerById(id);
    }

    @MutationMapping("updateCustomer")
    public CustomerDTO updateCustomer(@Argument CustomerDTO customerDTO) {
        return customerService.updateCustomer(customerDTO);
    }
}

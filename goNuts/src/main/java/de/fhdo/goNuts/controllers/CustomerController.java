package de.fhdo.goNuts.controllers;

import de.fhdo.goNuts.dto.CustomerDTO;
import de.fhdo.goNuts.interfaces.CustomerService;
import de.fhdo.goNuts.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/customer")
@RestController
public class CustomerController {
    private final CustomerService customerService;
    private final JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public CustomerDTO getCustomer(@RequestHeader("Authorization") String token) {
        if (jwtUtil.validateToken(token)) {
            String email = jwtUtil.extractEmail(token);

            return customerService.getCustomer(1);
        }

        System.out.println("Validierung fehlgeschlagen");
        return null;
    }
}

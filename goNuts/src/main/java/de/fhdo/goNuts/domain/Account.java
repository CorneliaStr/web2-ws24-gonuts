package de.fhdo.goNuts.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Account {
    @Id
    private String email;
    
    private String password;
    
    private boolean admin;
    
    
    
}

package de.fhdo.goNuts.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String surname;
    private String name;
    private Date birthday;
    private String adress;
    
    @OneToOne
    private Account account;
}

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

    public Customer() {}

    public Customer(String surname, String name, Date birthday, String adress, Account account) {
        this.surname = surname;
        this.name = name;
        this.birthday = birthday;
        this.adress = adress;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

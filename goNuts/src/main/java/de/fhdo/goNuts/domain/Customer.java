package de.fhdo.goNuts.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String surname;
    private String name;
    private LocalDate birthday;
    private String adress;
    
    @OneToOne
    private Account account;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Favorites favorites;

    public Customer() {}

    public Customer(String surname, String name, LocalDate birthday, String adress, Account account, Favorites favorites) {
        this.surname = surname;
        this.name = name;
        this.birthday = birthday;
        this.adress = adress;
        this.account = account;
        setFavorites(favorites);
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
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

    public Favorites getFavorites() { return favorites; }

    public void setFavorites(Favorites favorites) {
        this.favorites = favorites;
        if (favorites != null) {
            favorites.setCustomer(this);
        }
    }
}

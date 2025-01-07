package de.fhdo.goNuts.dto;

import java.time.LocalDate;

public class CustomerDTO {
    private Long id;
    private String surname;
    private String name;
    private LocalDate birthday;
    private String adress;

    private AccountDTO account;

    private FavoritesDTO favorites;

    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String surname, String name, LocalDate birthday, String adress, AccountDTO account, FavoritesDTO favorites) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.birthday = birthday;
        this.adress = adress;
        this.account = account;
        this.favorites = favorites;
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

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public FavoritesDTO getFavorites() { return favorites; }

    public void setFavorites(FavoritesDTO favorites) {this.favorites = favorites;}
}

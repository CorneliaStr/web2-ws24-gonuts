package de.fhdo.goNuts.dto;

import java.util.Objects;

public class AccountDTO {
    private String email;

    private String password;

    private boolean admin;

    public AccountDTO() {}

    public AccountDTO(String email, String password, boolean admin) {
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountDTO account)) return false;

        return admin == account.admin && Objects.equals(email, account.email) && Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, admin);
    }
}

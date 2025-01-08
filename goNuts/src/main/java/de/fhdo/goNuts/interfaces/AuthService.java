package de.fhdo.goNuts.interfaces;

import de.fhdo.goNuts.dto.AccountDTO;

import java.util.Optional;

public interface AuthService {
    /**
     * @return JSON Web Token
     */
    public String authenticate(String email, String password);

    public AccountDTO getAccountByToken(String token);

    public boolean isAdmin(String token);

    public Optional<String> extractEmail(String token);
}

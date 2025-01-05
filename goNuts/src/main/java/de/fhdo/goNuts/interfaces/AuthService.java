package de.fhdo.goNuts.interfaces;

public interface AuthService {
    /**
     * @return JSON Web Token
     */
    public String authenticate(String email, String password);
}

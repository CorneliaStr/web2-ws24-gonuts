package de.fhdo.goNuts.interfaces;

import de.fhdo.goNuts.dto.AccountDTO;

public interface AccountService {
    public AccountDTO getAccount(String email);
}

package de.fhdo.goNuts.services;

import de.fhdo.goNuts.domain.Account;
import de.fhdo.goNuts.dto.AccountDTO;
import de.fhdo.goNuts.interfaces.AccountService;
import de.fhdo.goNuts.mapper.AccountMapper;
import de.fhdo.goNuts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountDTO getAccount(String email) {
        Optional<Account> account = accountRepository.findById(email);
        return account.stream()
                .map(a -> accountMapper.mapEntityToDto(a))
                .findAny()
                .orElse(null);
    }
}

package de.fhdo.goNuts.services;

import de.fhdo.goNuts.domain.Account;
import de.fhdo.goNuts.dto.AccountDTO;
import de.fhdo.goNuts.interfaces.AuthService;
import de.fhdo.goNuts.mapper.AccountMapper;
import de.fhdo.goNuts.repository.AccountRepository;
import de.fhdo.goNuts.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    public AuthServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public String authenticate(String email, String password) {
        Optional<Account> account = accountRepository.findById(email);
        if (account.isPresent()) {
            AccountDTO dto = accountMapper.mapEntityToDto(account.get());
            if (dto.getPassword().equals(password)) {
                return jwtUtil.generateToken(email);
            }
        }

        return null;
    }
}

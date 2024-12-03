package de.fhdo.goNuts.mapper;

import de.fhdo.goNuts.domain.Account;
import de.fhdo.goNuts.dto.AccountDTO;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements Mapper<Account, AccountDTO> {
    @Override
    public AccountDTO mapEntityToDto(Account entity) {
        if (entity == null) return null;

        return new AccountDTO(entity.getEmail(), entity.getPassword(), entity.isAdmin());
    }

    @Override
    public Account mapDtoToEntity(AccountDTO dto) {
        if (dto == null) return null;

        return new Account(dto.getEmail(), dto.getPassword(), dto.isAdmin());
    }
}

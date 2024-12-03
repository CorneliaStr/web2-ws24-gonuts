package de.fhdo.goNuts.mapper;

import de.fhdo.goNuts.domain.Account;
import de.fhdo.goNuts.domain.Customer;
import de.fhdo.goNuts.dto.AccountDTO;
import de.fhdo.goNuts.dto.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements Mapper<Customer, CustomerDTO> {
    private final AccountMapper accountMapper;

    public CustomerMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public CustomerDTO mapEntityToDto(Customer entity) {
        if (entity == null) return null;

        AccountDTO accountDTO = accountMapper.mapEntityToDto(entity.getAccount());
        return new CustomerDTO(entity.getId(),entity.getSurname(), entity.getName(), entity.getBirthday(), entity.getAdress(), accountDTO);
    }

    @Override
    public Customer mapDtoToEntity(CustomerDTO dto) {
        if (dto == null) return null;

        Account account = accountMapper.mapDtoToEntity(dto.getAccount());
        Customer customer = new Customer(dto.getSurname(), dto.getName(), dto.getBirthday(), dto.getAdress(), account);
        customer.setId(dto.getId());

        return customer;
    }
}

package de.fhdo.goNuts.mapper;

import de.fhdo.goNuts.domain.Account;
import de.fhdo.goNuts.domain.Customer;
import de.fhdo.goNuts.domain.Favorites;
import de.fhdo.goNuts.dto.AccountDTO;
import de.fhdo.goNuts.dto.CustomerDTO;
import de.fhdo.goNuts.dto.FavoritesDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements Mapper<Customer, CustomerDTO> {
    private final AccountMapper accountMapper;
    private final FavoritesMapper favoritesMapper;

    public CustomerMapper(AccountMapper accountMapper, FavoritesMapper favoritesMapper) {
        this.accountMapper = accountMapper;
        this.favoritesMapper = favoritesMapper;
    }

    @Override
    public CustomerDTO mapEntityToDto(Customer entity) {
        if (entity == null) return null;

        AccountDTO accountDTO = accountMapper.mapEntityToDto(entity.getAccount());
        FavoritesDTO favoritesDTO = favoritesMapper.mapEntityToDto(entity.getFavorites());
        return new CustomerDTO(entity.getId(),entity.getSurname(), entity.getName(), entity.getBirthday(), entity.getAdress(), accountDTO, favoritesDTO);
    }

    @Override
    public Customer mapDtoToEntity(CustomerDTO dto) {
        if (dto == null) return null;

        Account account = accountMapper.mapDtoToEntity(dto.getAccount());
        Favorites favorites = favoritesMapper.mapDtoToEntity(dto.getFavorites());
        Customer customer = new Customer(dto.getSurname(), dto.getName(), dto.getBirthday(), dto.getAdress(), account, favorites);
        customer.setId(dto.getId());

        return customer;
    }
}

package de.fhdo.goNuts.mapper;

import org.springframework.stereotype.Component;

public interface Mapper <E, D>{
    public D mapEntityToDto(E entity);

    public E mapDtoToEntity(D dto);
}

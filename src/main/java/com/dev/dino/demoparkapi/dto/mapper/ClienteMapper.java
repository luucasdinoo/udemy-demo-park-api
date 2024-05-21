package com.dev.dino.demoparkapi.dto.mapper;

import com.dev.dino.demoparkapi.dto.ClienteCreateDto;
import com.dev.dino.demoparkapi.dto.ClienteResponseDto;
import com.dev.dino.demoparkapi.entity.Cliente;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteMapper {

    public static Cliente toCliente(ClienteCreateDto dto){
        return new ModelMapper().map(dto, Cliente.class);
    }

    public static ClienteResponseDto toDto(Cliente cliente){
        return new ModelMapper().map(cliente, ClienteResponseDto.class);
    }
}

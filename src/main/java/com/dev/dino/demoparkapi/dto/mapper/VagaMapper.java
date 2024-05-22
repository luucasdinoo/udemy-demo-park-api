package com.dev.dino.demoparkapi.dto.mapper;

import com.dev.dino.demoparkapi.dto.VagaCreateDto;
import com.dev.dino.demoparkapi.dto.VagaResponseDto;
import com.dev.dino.demoparkapi.entity.Vaga;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VagaMapper {

    public static Vaga toVaga(VagaCreateDto dto){
        return  new ModelMapper().map(dto, Vaga.class);
    }

    public static VagaResponseDto toDto(Vaga vaga){
        return new ModelMapper().map(vaga, VagaResponseDto.class);
    }
}

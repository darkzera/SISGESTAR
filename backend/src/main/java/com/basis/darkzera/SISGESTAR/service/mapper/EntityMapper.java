package com.basis.darkzera.SISGESTAR.service.mapper;

public interface EntityMapper<ENTIDADE, DTO> {
    DTO toDTO(ENTIDADE entidade);
    ENTIDADE toEntity(DTO dto);
}

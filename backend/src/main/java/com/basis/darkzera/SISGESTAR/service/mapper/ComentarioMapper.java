package com.basis.darkzera.SISGESTAR.service.mapper;

import com.basis.darkzera.SISGESTAR.domain.Comentario;
import com.basis.darkzera.SISGESTAR.service.dto.ComentarioDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ComentarioMapper {
    ComentarioDTO toDTO(Comentario comentario);
    Comentario toEntity(ComentarioDTO comentarioDTO);
}

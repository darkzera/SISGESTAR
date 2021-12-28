package com.basis.darkzera.SISGESTAR.service.mapper;

import com.basis.darkzera.SISGESTAR.domain.Comentario;
import com.basis.darkzera.SISGESTAR.service.dto.ComentarioDTO;
import com.basis.darkzera.SISGESTAR.service.dto.ComentarioListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {UsuarioMapper.class, TarefaMapper.class})
public interface ComentarioMapper extends EntityMapper<Comentario, ComentarioDTO>{

    @Mapping(source = "usuario.id", target = "idUsuario")
    @Mapping(source = "tarefa.id", target = "idTarefa")
    ComentarioDTO toDTO(Comentario comentario);

    @Mapping(source = "idUsuario", target = "usuario.id")
    @Mapping(source = "idTarefa", target = "tarefa.id")
    Comentario toEntity(ComentarioDTO comentarioDTO);

    @Mapping(source = "usuario.id", target = "idUsuario")
    ComentarioListDTO toListDTO(Comentario comentario);

}

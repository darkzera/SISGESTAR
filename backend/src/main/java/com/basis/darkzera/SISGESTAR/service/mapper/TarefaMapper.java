package com.basis.darkzera.SISGESTAR.service.mapper;

import com.basis.darkzera.SISGESTAR.domain.Tarefa;
import com.basis.darkzera.SISGESTAR.domain.Usuario;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaDTO;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaListDTO;
import org.apache.logging.log4j.spi.ObjectThreadContextMap;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Objects;
import java.util.Optional;

@Mapper(uses = { UsuarioMapper.class })
public interface TarefaMapper extends EntityMapper<Tarefa, TarefaDTO> {

    @Mapping(source = "responsavel.id", target = "idResponsavel")
    TarefaDTO toDTO(Tarefa tarefa);

    @Mapping(source = "idResponsavel", target = "responsavel.id")
    Tarefa toEntity(TarefaDTO tarefaDTO);

    TarefaListDTO toListDTO(Tarefa tarefa);

    @AfterMapping
    default void verificarElementosNulos(@MappingTarget Tarefa tarefa){
        tarefa.setResponsavel(
                Optional.ofNullable(tarefa.getResponsavel()).orElseGet(Usuario::new)
        );

        if (Objects.isNull(tarefa.getResponsavel().getId())) {
            tarefa.setResponsavel(null);
        }
    }

}

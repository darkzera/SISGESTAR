package com.basis.darkzera.SISGESTAR.service.mapper;

import com.basis.darkzera.SISGESTAR.domain.Tarefa;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaDTO;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaListDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

    TarefaDTO toDTO(Tarefa tarefa);
    TarefaListDTO toListDTO(Tarefa tarefa);
    Tarefa toEntity(TarefaDTO tarefaDTO);

}

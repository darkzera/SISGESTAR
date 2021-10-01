package com.basis.darkzera.SISGESTAR.service.mapper;

import com.basis.darkzera.SISGESTAR.domain.Tarefa;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaDTO;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { UsuarioMapper.class })
public interface TarefaMapper extends EntityMapper<Tarefa, TarefaDTO> {

    @Mapping(source = "responsavel.id", target = "idResponsavel")
    TarefaDTO toDTO(Tarefa tarefa);

    @Mapping(source = "idResponsavel", target = "responsavel.id")
    Tarefa toEntity(TarefaDTO tarefaDTO);

    TarefaListDTO toListDTO(Tarefa tarefa);



}

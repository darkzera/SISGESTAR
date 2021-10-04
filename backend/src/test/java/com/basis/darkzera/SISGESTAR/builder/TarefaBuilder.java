package com.basis.darkzera.SISGESTAR.builder;

import com.basis.darkzera.SISGESTAR.domain.Tarefa;
import com.basis.darkzera.SISGESTAR.repository.TarefaRepository;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaDTO;
import com.basis.darkzera.SISGESTAR.service.mapper.TarefaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TarefaBuilder {
    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    TarefaMapper tarefaMapper;

    public TarefaDTO createTarefaDTO() {
        TarefaDTO tarefaDTO = new TarefaDTO();
        tarefaDTO.setNome("Nome Teste");
        tarefaDTO.setDescricao("Descrição Teste");
        return tarefaDTO;
    }

    public TarefaDTO persistirTarefa(TarefaDTO tarefaDTO) {
        Tarefa tarefa = tarefaMapper.toEntity(tarefaDTO);

        if (tarefa.getResponsavel().getId() == null) {
            tarefa.setResponsavel(null);
        }
        tarefa = tarefaRepository.save(tarefa);
        return tarefaMapper.toDTO(tarefa);
    }
}

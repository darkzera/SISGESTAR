package com.basis.darkzera.SISGESTAR.builder;

import com.basis.darkzera.SISGESTAR.domain.Tarefa;
import com.basis.darkzera.SISGESTAR.repository.TarefaRepository;
import com.basis.darkzera.SISGESTAR.service.TarefaService;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaDTO;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioDTO;
import com.basis.darkzera.SISGESTAR.service.mapper.TarefaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.SchemaOutputResolver;

@Component
public class TarefaBuilder {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaMapper tarefaMapper;

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    public TarefaDTO createTarefaDTO() {
        TarefaDTO tarefaDTO = new TarefaDTO();
        tarefaDTO.setNome("Nome Teste");
        tarefaDTO.setDescricao("Descrição Teste");
        UsuarioDTO usuarioDTO = usuarioBuilder.persistirUsuario(
                usuarioBuilder.createUsuarioDTO()
        );
        tarefaDTO.setIdResponsavel(usuarioDTO.getId());
        return persistirTarefa(tarefaDTO);
    }

    private TarefaDTO persistirTarefa(TarefaDTO tarefaDTO) {
        Tarefa tarefa = tarefaMapper.toEntity(tarefaDTO);
        tarefa = tarefaRepository.save(tarefa);
        return tarefaMapper.toDTO(tarefa);
    }

    public TarefaDTO createTarefaDTONovoStatus(){
        // TODO FIXME
        // Apenas alterar o status da tarefa
        TarefaDTO tarefaDTO = new TarefaDTO();
        tarefaDTO.setIdStatus(2L);
        return tarefaDTO;

    }

}

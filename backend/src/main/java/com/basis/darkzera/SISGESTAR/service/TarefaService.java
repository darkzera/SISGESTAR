package com.basis.darkzera.SISGESTAR.service;

import com.basis.darkzera.SISGESTAR.domain.Tarefa;
import com.basis.darkzera.SISGESTAR.domain.enumerations.StatusTarefaEnum;
import com.basis.darkzera.SISGESTAR.repository.TarefaRepository;
import com.basis.darkzera.SISGESTAR.repository.UsuarioRepository;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaDTO;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaListDTO;
import com.basis.darkzera.SISGESTAR.service.error.TarefaNaoEncontradaException;
import com.basis.darkzera.SISGESTAR.service.error.UsuarioNaoAutorizadoException;
import com.basis.darkzera.SISGESTAR.service.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;

    private final UsuarioRepository usuarioRepository;

    public List<TarefaListDTO> findAll(){
        return tarefaRepository.findAll().stream()
                .map(tarefaMapper::toListDTO)
                .collect(Collectors.toList());
    }

    public TarefaDTO save(TarefaDTO tarefaDTO){
        Tarefa tarefa = tarefaMapper.toEntity(tarefaDTO);
        tarefa.setIdStatus(StatusTarefaEnum.A_FAZER.getId());
        tarefaRepository.save(tarefa);
        return tarefaMapper.toDTO(tarefa);
    }

    public Optional<TarefaDTO> findById(Long id){
        return tarefaRepository.findById(id).map(tarefaMapper::toDTO);
    }

    public void deleteById(Long id){
        tarefaRepository.deleteById(id);
    }

    public TarefaDTO atualizarStatus(Tarefa tarefa, String hash) {
        Tarefa tarefaEmBanco = tarefaRepository.findById(tarefa.getId())
                .orElseThrow(TarefaNaoEncontradaException::new);
        validarResponsavel(tarefaEmBanco, hash);
        tarefaEmBanco.setIdStatus(tarefa.getIdStatus());
        tarefaRepository.save(tarefaEmBanco);
        return tarefaMapper.toDTO(tarefaEmBanco);
    }

    private void validarResponsavel(Tarefa tarefa, String hash){
        if (!tarefa.getResponsavel().getHash().equals(hash)){
            throw new UsuarioNaoAutorizadoException();
        }
    }

}

package com.basis.darkzera.SISGESTAR.service;

import com.basis.darkzera.SISGESTAR.domain.Tarefa;
import com.basis.darkzera.SISGESTAR.domain.Usuario;
import com.basis.darkzera.SISGESTAR.domain.enumerations.StatusTarefaEnum;
import com.basis.darkzera.SISGESTAR.repository.TarefaRepository;
import com.basis.darkzera.SISGESTAR.repository.UsuarioRepository;
import com.basis.darkzera.SISGESTAR.service.dto.EmailDTO;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaDTO;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaFilterDTO;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaListDTO;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioDTO;
import com.basis.darkzera.SISGESTAR.service.error.TarefaNaoEncontradaException;
import com.basis.darkzera.SISGESTAR.service.error.UsuarioNaoAutorizadoException;
import com.basis.darkzera.SISGESTAR.service.error.UsuarioNaoEncontradoException;
import com.basis.darkzera.SISGESTAR.service.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;

    private final UsuarioService usuarioService;

    private final SendMailService sendMailService;

    public Page<TarefaListDTO> findAll(TarefaFilterDTO tarefaDTO, Pageable page){
        return tarefaRepository.filtrarTarefas(tarefaDTO, page);
    }

    public TarefaDTO save(TarefaDTO tarefaDTO){
        validarResponsavel(tarefaDTO);
        definirStatusInicial(tarefaDTO);
        Tarefa tarefa = tarefaMapper.toEntity(tarefaDTO);
        tarefa.getAcompanhadores().add(tarefa.getResponsavel());
        tarefa = tarefaRepository.save(tarefa);
        return tarefaMapper.toDTO(tarefa);
    }

    public Optional<TarefaDTO> findById(Long id){
        return tarefaRepository.findById(id).map(tarefaMapper::toDTO);
    }

    public void deleteById(Long id){
        tarefaRepository.deleteById(id);
    }

    private void validarResponsavel(TarefaDTO tarefaDTO){
        if (Objects.nonNull(tarefaDTO.getIdResponsavel())){
            usuarioService.obterUsuarioPorId(tarefaDTO.getIdResponsavel());
        }
    }

    private void validarResponsavel(Tarefa tarefa, String hash){
        UsuarioDTO responsavel = obterResponsavelPelaTarefa(tarefa);
        if (!responsavel.getHash().equals(hash)){
            throw new UsuarioNaoAutorizadoException();
        }
    }

    private UsuarioDTO obterResponsavelPelaTarefa(Tarefa tarefa){
        // Usar DTO aqui ou n?
        Usuario responsavel = tarefa.getResponsavel();
        return usuarioService.obterUsuarioPorId(responsavel.getId());
    }

    public TarefaDTO atualizarStatus(Tarefa tarefa, String hash) {
        Tarefa tarefaEmBanco = tarefaRepository.findById(tarefa.getId())
                .orElseThrow(TarefaNaoEncontradaException::new);
        validarResponsavel(tarefaEmBanco, hash);
        tarefaEmBanco.setIdStatus(tarefa.getIdStatus());
        tarefaRepository.save(tarefaEmBanco);
        construirEmail(tarefaEmBanco, tarefaEmBanco.getResponsavel());

//        TODO FIX ME
//        notificarAcompanhadores(tarefaEmBanco);

        return tarefaMapper.toDTO(tarefaEmBanco);
    }

    private void definirStatusInicial(TarefaDTO tarefa) {
        tarefa.setIdStatus(StatusTarefaEnum.A_FAZER.getId());
    }

    private void notificarAcompanhadores(Tarefa tarefa){
        // TODO: FIXME - Nao ha acompanhadores sendo adicionados.
        // O unico usuario vinculado as tarefas ta sendo o Usuario::Responsavel
        tarefa.getAcompanhadores().forEach(acompanhador -> {
            EmailDTO emailDTO = construirEmail(tarefa, acompanhador);
            System.out.println(emailDTO.getCorpo() + " --- " + emailDTO.getDestinatario());
            sendMailService.sendMail(emailDTO);
        });
    }

    private EmailDTO construirEmail(Tarefa tarefa, Usuario acompanhador) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setAssunto("Movimentaçao em Tarefa " + tarefa.getNome() + " :" + tarefa.getId());
        emailDTO.setCorpo("O novo status da tarefa é " +
                StatusTarefaEnum.obterPorId(tarefa.getIdStatus()).getDescricao());
        emailDTO.setDestinatario(acompanhador.getEmail());

        // This sendMail should be removed after we fix ::acompanhadores ADD;;
        sendMailService.sendMail(emailDTO);
        return emailDTO;
    }

}

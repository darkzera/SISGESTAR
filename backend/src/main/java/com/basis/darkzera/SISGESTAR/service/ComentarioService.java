package com.basis.darkzera.SISGESTAR.service;

import com.basis.darkzera.SISGESTAR.domain.Comentario;
import com.basis.darkzera.SISGESTAR.domain.Tarefa;
import com.basis.darkzera.SISGESTAR.domain.Usuario;
import com.basis.darkzera.SISGESTAR.repository.ComentarioRepository;
import com.basis.darkzera.SISGESTAR.service.dto.ComentarioDTO;
import com.basis.darkzera.SISGESTAR.service.dto.ComentarioListDTO;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioDTO;
import com.basis.darkzera.SISGESTAR.service.mapper.ComentarioMapper;
import com.basis.darkzera.SISGESTAR.service.mapper.UsuarioMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    public Page<ComentarioListDTO> findAllByIdUsuario(Long idUsuario, Pageable page){
        UsuarioDTO u = usuarioService.obterUsuarioPorId(idUsuario);
        return comentarioRepository.findByIdUsuario(idUsuario, page).map(comentarioMapper::toListDTO);

    }

    public ComentarioDTO save(ComentarioDTO comentarioDTO){
        Comentario comentario = comentarioMapper.toEntity(comentarioDTO);
        comentario.setData_comentario(LocalDateTime.now());
        comentarioRepository.save(comentario);
        Tarefa t = comentario.getTarefa();
        t.getAcompanhadores().add(comentario.getUsuario());
        // tarefaService.tarefaRepository.flush();
        return comentarioMapper.toDTO(comentario);
    }

    public Optional<ComentarioDTO> findById(Long id){
        return comentarioRepository.findById(id)
                .map(comentarioMapper::toDTO);
    }

    public void deleteById(Long id){
        comentarioRepository.deleteById(id);
    }
}

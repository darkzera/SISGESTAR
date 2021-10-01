package com.basis.darkzera.SISGESTAR.service;

import com.basis.darkzera.SISGESTAR.domain.Comentario;
import com.basis.darkzera.SISGESTAR.domain.Usuario;
import com.basis.darkzera.SISGESTAR.repository.ComentarioRepository;
import com.basis.darkzera.SISGESTAR.service.dto.ComentarioDTO;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioDTO;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioListDTO;
import com.basis.darkzera.SISGESTAR.service.mapper.ComentarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;

    public List<ComentarioDTO> findAll(){
        return comentarioRepository.findAll().stream()
                .map(comentarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ComentarioDTO save(ComentarioDTO comentarioDTO){
        Comentario comentario = comentarioMapper.toEntity(comentarioDTO);
        comentarioRepository.save(comentario);
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

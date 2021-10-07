package com.basis.darkzera.SISGESTAR.service;

import com.basis.darkzera.SISGESTAR.domain.Usuario;
import com.basis.darkzera.SISGESTAR.repository.UsuarioRepository;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioDTO;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioListDTO;
import com.basis.darkzera.SISGESTAR.service.error.UsuarioNaoEncontradoException;
import com.basis.darkzera.SISGESTAR.service.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public List<UsuarioDTO> findAll(){
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());

    }

    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setHash(UUID.randomUUID().toString());
        usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    public Optional<UsuarioDTO> findById(Long id){
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDTO);
    }

    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO obterUsuarioPorId(Long id){
        Usuario usuarioFound = usuarioRepository.findById(id)
                .orElseThrow(UsuarioNaoEncontradoException::new);
        return usuarioMapper.toDTO(usuarioFound);
    }

    public Optional<UsuarioDTO> obterPorHash(String hash){
        return usuarioRepository.findByHash(hash).map(usuarioMapper::toDTO);
    }


}

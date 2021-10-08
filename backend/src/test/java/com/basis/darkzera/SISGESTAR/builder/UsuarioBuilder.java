package com.basis.darkzera.SISGESTAR.builder;

import com.basis.darkzera.SISGESTAR.domain.Usuario;
import com.basis.darkzera.SISGESTAR.repository.UsuarioRepository;
import com.basis.darkzera.SISGESTAR.service.UsuarioService;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioDTO;
import com.basis.darkzera.SISGESTAR.service.error.UsuarioNaoAutorizadoException;
import com.basis.darkzera.SISGESTAR.service.error.UsuarioNaoEncontradoException;
import com.basis.darkzera.SISGESTAR.service.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioBuilder {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioMapper usuarioMapper;

    public UsuarioDTO createUsuarioDTO() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setName("Nome Teste");
        usuarioDTO.setEmail("email@teste.com");
        return usuarioDTO;
    }

    public UsuarioDTO persistirUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        return usuarioService.save(usuarioDTO);
    }

    public String getHashValido(UsuarioDTO usuarioDTO){
        // Preciso do getHash -> uso diretamente o repositorio
        return usuarioRepository.findById(usuarioDTO.getId())
                .orElseThrow(UsuarioNaoEncontradoException::new).getHash();
    }

}

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
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public UsuarioDTO createUsuarioDTO() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setName("Nome - Usuario - Teste");
        usuarioDTO.setEmail("email@gmail.com");
        return usuarioDTO;
    }

    public UsuarioDTO persistirUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        return usuarioService.save(usuarioDTO);
    }

    public String getHashValido(Long responsavelId){
        return usuarioRepository.findById(responsavelId)
                .map(Usuario::getHash)
                .get();
    }

}

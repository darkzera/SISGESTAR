package com.basis.darkzera.SISGESTAR.service;

import com.basis.darkzera.SISGESTAR.domain.Tarefa;
import com.basis.darkzera.SISGESTAR.domain.Usuario;
import com.basis.darkzera.SISGESTAR.domain.enumerations.StatusTarefaEnum;
import com.basis.darkzera.SISGESTAR.repository.UsuarioRepository;
import com.basis.darkzera.SISGESTAR.service.dto.EmailDTO;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioDTO;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioListDTO;
import com.basis.darkzera.SISGESTAR.service.error.EmailEmUsoException;
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

    private final SendMailService sendMailService;

    public List<UsuarioDTO> findAll(){
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());

    }

    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        if (!emailExists(usuario)) {
            usuario.setHash(UUID.randomUUID().toString());
            usuarioRepository.save(usuario);
            construirEmailEnvioHashPwd(usuario);
            return usuarioMapper.toDTO(usuario);
        } else {
            throw new EmailEmUsoException();
        }
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

    private boolean emailExists(Usuario usuario){
        boolean exist =
                usuarioRepository.findByEmail(usuario.getEmail()).isPresent() ?  true : false;

        return exist;
    }

    public Optional<UsuarioDTO> obterPorHash(String hash){
        return usuarioRepository.findByHash(hash).map(usuarioMapper::toDTO);
    }

    private EmailDTO construirEmailEnvioHashPwd(Usuario usuario) {
        EmailDTO emailDTO = new EmailDTO();
//        Usuario usuarioBanco = usuarioRepository.findById(usuario.getId())
//                .orElseThrow(UsuarioNaoEncontradoException::new);
        emailDTO.setAssunto("" + usuario.getName() + ", nova conta criada:");
        emailDTO.setCorpo("Nova conta criada para:  " + usuario.getEmail() + "(ID: " + usuario.getName() + " )" +
                "\nHash password: " + usuario.getHash());
        emailDTO.setDestinatario(usuario.getEmail());

        // This sendMail should be removed after we fix ::acompanhadores ADD;;
        sendMailService.sendMail(emailDTO);
        return emailDTO;
    }
}

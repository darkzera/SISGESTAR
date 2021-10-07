package com.basis.darkzera.SISGESTAR.web.rest;

import com.basis.darkzera.SISGESTAR.domain.Usuario;
import com.basis.darkzera.SISGESTAR.service.UsuarioService;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioDTO;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioListDTO;
import com.basis.darkzera.SISGESTAR.service.mapper.ComentarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios/")
@RequiredArgsConstructor
public class UsuarioResource {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuario) {
        return ResponseEntity.ok(usuarioService.save(usuario));
    }

    @GetMapping
    ResponseEntity<List<UsuarioDTO>> findAll(){
        //TODO: Should not return hash from user - FIXME
        return ResponseEntity.ok(
                usuarioService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.of(
                usuarioService.findById(id)
        );
    }

    @GetMapping("/obter-por-hash/{hash}")
    public ResponseEntity<UsuarioDTO> obterPorHash(@PathVariable("hash") String hash){
        return ResponseEntity.of(
                usuarioService.obterPorHash(hash)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable("id") Long id,
                                             @RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO.setId(id);
        return ResponseEntity.ok(
                usuarioService.save(usuarioDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

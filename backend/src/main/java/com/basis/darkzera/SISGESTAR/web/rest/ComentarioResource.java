package com.basis.darkzera.SISGESTAR.web.rest;

import com.basis.darkzera.SISGESTAR.service.ComentarioService;
import com.basis.darkzera.SISGESTAR.service.dto.ComentarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comentarios/")
@RequiredArgsConstructor
public class ComentarioResource {
    private final ComentarioService comentarioService;

    @PostMapping
    public ResponseEntity<ComentarioDTO> create(@RequestBody ComentarioDTO comentarioDTO){
        return ResponseEntity.ok(
                comentarioService.save(comentarioDTO)
        );
    }

    @GetMapping
    ResponseEntity<List<ComentarioDTO>> findAll(){
        return ResponseEntity.ok(
                comentarioService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.of(
                comentarioService.findById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDTO> update(@PathVariable("id") Long id,
                                                @RequestBody ComentarioDTO comentarioDTO) {
        comentarioDTO.setId(id);
        return ResponseEntity.ok(
                comentarioService.save(comentarioDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        comentarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
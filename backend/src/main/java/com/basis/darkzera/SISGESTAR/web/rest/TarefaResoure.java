package com.basis.darkzera.SISGESTAR.web.rest;

import com.basis.darkzera.SISGESTAR.service.TarefaService;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaDTO;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor
public class TarefaResoure {

    private final TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<TarefaListDTO>> findAll(){
        return ResponseEntity.ok(
                tarefaService.findAll()
        );
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> create(@RequestBody TarefaDTO tarefaDTO){
        return ResponseEntity.ok(
                tarefaService.save(tarefaDTO)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.of(
                tarefaService.findById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> update(@PathVariable("id") Long id,
                                            @RequestBody TarefaDTO tarefaDTO){
        tarefaDTO.setId(id);
        return ResponseEntity.ok(
                tarefaService.save(tarefaDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        tarefaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

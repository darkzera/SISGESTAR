package com.basis.darkzera.SISGESTAR.web.rest;

import com.basis.darkzera.SISGESTAR.domain.Tarefa;
import com.basis.darkzera.SISGESTAR.service.SendMailService;
import com.basis.darkzera.SISGESTAR.service.TarefaService;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaDTO;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaFilterDTO;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaListDTO;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioDTO;
import com.basis.darkzera.SISGESTAR.service.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor
public class TarefaResource {

    private final TarefaService tarefaService;
    private final TarefaMapper tarefaMapper;
    private final SendMailService sendMailService;

    @GetMapping
    public ResponseEntity<Page<TarefaListDTO>> obterTodosComFiltro(@ModelAttribute TarefaFilterDTO tarefaFilterDTO,
                                                                   Pageable p) {
        // TODO: FIXME - Nao ta retornando os resultados esperados
        //               o service ta usando o obterComFiltro. Trocar por findall generico
        return  ResponseEntity.ok(
                tarefaService.findAll(tarefaFilterDTO, p));
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

    @PatchMapping("/{id}")
    public ResponseEntity<TarefaDTO> updateStatus(@PathVariable("id") Long id,
                                                  @RequestBody Tarefa tarefa,
                                                  @RequestParam("hash") String hash){
        tarefa.setId(id);
        return ResponseEntity.ok(tarefaService.atualizarStatus(tarefa, hash));

    }
}

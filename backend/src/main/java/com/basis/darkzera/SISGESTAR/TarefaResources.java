package com.basis.darkzera.SISGESTAR;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tarefa")
public class TarefaResources {

    List<Tarefa> tarefas = new ArrayList<>();

    @GetMapping
    public List<Tarefa> recuperarTodos() {
        return tarefas;
    }

    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa novaTarefa) {
        tarefas.add(novaTarefa);
        return novaTarefa;
    }


}

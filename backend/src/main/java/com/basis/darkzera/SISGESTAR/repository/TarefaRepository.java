package com.basis.darkzera.SISGESTAR.repository;

import com.basis.darkzera.SISGESTAR.domain.Tarefa;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaDTO;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaFilterDTO;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query(
            "SELECT new com.basis.darkzera.SISGESTAR.service.dto.TarefaListDTO(" +
                    "   tarefa.id, " +
                    "   tarefa.nome " +
                    ") FROM " +
                    "   Tarefa tarefa " +
                    "WHERE " +
                    "   tarefa.nome LIKE %:#{#filtro.nome}%")
    Page<TarefaListDTO> filtrarTarefas(@Param("filtro") TarefaFilterDTO tarefaFilterDTO, Pageable page);

//
//    @Query(
//            "SELECT new com.basis.darkzera.SISGESTAR.service.dto.TarefaListDTO(" +
//                    " tarefa.id, " + " tarefa.nome )" +
//                    " FROM  " + " Tarefa tarefa " +
//                    " WHERE " +
//                    " tarefa.,  LIKE %:#{#filtro.nome}%"
//    )
//    Page<TarefaListDTO> find(@Param("filtro") TarefaFilterDTO tarefaFilterDTO, Pageable page);


}

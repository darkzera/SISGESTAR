package com.basis.darkzera.SISGESTAR.web.rest;

import com.basis.darkzera.SISGESTAR.builder.TarefaBuilder;
import com.basis.darkzera.SISGESTAR.builder.UsuarioBuilder;
import com.basis.darkzera.SISGESTAR.domain.Tarefa;
import com.basis.darkzera.SISGESTAR.service.dto.TarefaDTO;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioDTO;
import com.basis.darkzera.SISGESTAR.util.BaseIntTest;
import com.basis.darkzera.SISGESTAR.util.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@Transactional
public class TarefaResourceTest extends BaseIntTest {

    @Autowired
    private TarefaBuilder tarefaBuilder;
    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Test
    public void listarTarefasComSucesso() throws Exception {
        tarefaBuilder.createTarefaDTO();

        mockMvc.perform(get("/api/tarefas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]", hasSize(1)));
    }

    @Test
    public void inserirTarefaComResponsavelInexistente() throws Exception {
        TarefaDTO tarefaDTO = tarefaBuilder.createTarefaDTO();
        tarefaDTO.setIdResponsavel(Long.MAX_VALUE);

        ResultActions resultActions = mockMvc.perform(
                post("/api/tarefas/")
                        .content(TestUtil.convertObjectToJsonBytes(tarefaDTO))
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        );

        resultActions.andExpect(status().isBadRequest());
        Assertions.assertEquals(
                resultActions.andReturn().getResponse().getErrorMessage(),
                "O usuário buscado não possui registro em banco."
        );
    }
    @Test
    public void atualizaStatusInexistente() throws Exception{
        TarefaDTO tarefaDTO = tarefaBuilder.createTarefaDTO();

    }
    @Test
    public void inserirTarefaComSucesso() throws Exception {
        TarefaDTO tarefaDTO = tarefaBuilder.createTarefaDTO();

        mockMvc.perform(post("/api/tarefas")
                        .content(TestUtil.convertObjectToJsonBytes(tarefaDTO))
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isOk());
    }

    // TODO FIXME
    @Test
    public void encontrarTarefaPorIdComSucesso() throws Exception {
        TarefaDTO tarefaDTO = tarefaBuilder.createTarefaDTO();
        ResultActions resultAction =
                mockMvc.perform(get("/api/tarefas/" + tarefaDTO.getId()))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(1))
                        .andExpect(jsonPath("$.nome").value("Nome teste"));
    }

    @Test
    public void encontrarTarefaSemSucesso() throws Exception {
        mockMvc.perform(get("/api/tarefas/" + Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    // TODO FIXME
    @Test
    public void atualizaStatusTarefaComSucess() throws Exception{
        // TODO - Revisar
        // Resumir
        TarefaDTO tarefaDTO = tarefaBuilder.createTarefaDTO();
//        tarefaDTO.setIdResponsavel(usuarioResponsavel.getId());

        String responsavelHash = usuarioBuilder.getHashValido(tarefaDTO.getIdResponsavel());
        TarefaDTO tarefaNovoStatus = tarefaBuilder.createTarefaDTONovoStatus();

        String requestUrl = "/api/tarefas/" + tarefaDTO.getId() + "?hash=" + responsavelHash;
        log.error("Req: " + requestUrl);
        log.error("TAref: " + tarefaDTO.getDescricao() + " ID : " + tarefaDTO.getId());
        log.error("NOvo status para tarefa: " + tarefaNovoStatus.getIdStatus());
        mockMvc.perform(patch(requestUrl)
                        .content(TestUtil.convertObjectToJsonBytes(tarefaNovoStatus))
                        .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }


}

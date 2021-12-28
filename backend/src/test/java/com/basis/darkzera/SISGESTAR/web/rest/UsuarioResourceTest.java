package com.basis.darkzera.SISGESTAR.web.rest;

import com.basis.darkzera.SISGESTAR.builder.UsuarioBuilder;
import com.basis.darkzera.SISGESTAR.domain.Usuario;
import com.basis.darkzera.SISGESTAR.repository.UsuarioRepository;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioDTO;
import com.basis.darkzera.SISGESTAR.service.mapper.UsuarioMapper;
import com.basis.darkzera.SISGESTAR.util.BaseIntTest;
import com.basis.darkzera.SISGESTAR.util.TestUtil;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
public class UsuarioResourceTest extends BaseIntTest {

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Test
    public void listarUsuariosComSucesso() throws Exception {
        usuarioBuilder.persistirUsuario(usuarioBuilder.createUsuarioDTO());

        mockMvc.perform(get("/api/usuarios/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]", hasSize(1)));
    }

    @Test
    public void inserirUsuarioComEmailExistente() throws Exception {
        UsuarioDTO usuarioDTO = usuarioBuilder.createUsuarioDTO();
        usuarioDTO.setEmail("email@teste.com");
        usuarioBuilder.persistirUsuario(usuarioDTO);

        ResultActions resultActions = mockMvc.perform(
                post("/api/usuarios/")
                        .content(TestUtil.convertObjectToJsonBytes(usuarioDTO))
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        );
        resultActions.andExpect(status().isBadRequest());
        Assertions.assertEquals(
                resultActions.andReturn().getResponse().getErrorMessage(),
                 "O email ja esta registrado por outro usuario");
    }

    @Test
    public void inserirUsuarioComSucesso() throws Exception {
        UsuarioDTO tarefaDTO = usuarioBuilder.createUsuarioDTO();

        mockMvc.perform(post("/api/tarefas")
                        .content(TestUtil.convertObjectToJsonBytes(tarefaDTO))
                        .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void encontrarUsuarioComSucesso() throws Exception {
        UsuarioDTO tarefaDTO = usuarioBuilder.persistirUsuario(usuarioBuilder.createUsuarioDTO());

        // TODO: nao deveria comparar o resultado com algum modelo pre-inserido?

        mockMvc.perform(get("/api/usuarios/" + tarefaDTO.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void encontrarUsuarioSemSucesso() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/usuarios/" + Long.MAX_VALUE))
                .andExpect(status().isNotFound());
        Assertions.assertEquals(
                resultActions.andReturn().getResponse().getErrorMessage(),
                "O usuário buscado não possui registro em banco."
        );
    }

    @Test
    public void encontraUsuarioPorHashComSucesso() throws Exception {
        String hashValido = usuarioBuilder.getHashValido(usuarioBuilder.persistirUsuario(
                usuarioBuilder.createUsuarioDTO()).getId());

        ResultActions resultActions =
                mockMvc.perform(get("/api/usuarios/obter-por-hash/" +hashValido))
                        .andExpect(status().isOk());
    }

}

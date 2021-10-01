package com.basis.darkzera.SISGESTAR.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.UNAUTHORIZED,
        reason = "Usuario nao autorizado para alterar status da tarefa."
)
public class UsuarioNaoAutorizadoException extends RuntimeException{
}

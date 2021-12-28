package com.basis.darkzera.SISGESTAR.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.NOT_FOUND,
        reason = "Tarefa buscada nao encontrada"
)
public class TarefaNaoEncontradaException extends RuntimeException{
}

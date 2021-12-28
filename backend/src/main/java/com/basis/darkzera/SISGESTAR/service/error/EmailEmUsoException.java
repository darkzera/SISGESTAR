package com.basis.darkzera.SISGESTAR.service.error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.BAD_REQUEST,
        reason = "O email ja esta registrado por outro usuario")
public class EmailEmUsoException extends RuntimeException {

}

package com.basis.darkzera.SISGESTAR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SisgestarApplication {

	public static void main(String[] args) {
		SpringApplication.run(SisgestarApplication.class, args);
	}

	/* TODO:
		- Corrigir escopo dos acomapanhantes (acompanhadores)
			. email
			. lista/tabela no banco de dados (responsavel e quem faz comentario)
			. nova regra de negocio: Tem como acompanhar uma tarefa sem comentar e sem ser o responsavel?

		- Implementar DateTime::type para Tarefa e Comentario
			. usando LocalDateTime, preciso formatar e/ou migrar para outro

		- Endpoint para Upload de imagem
			. biblioteca ???

		- Implementar Pageable em todos os contexto de endpoint com lista
			. comentarios
			. usuarios

		- Corrigir os comentarios no PR

	 */
}

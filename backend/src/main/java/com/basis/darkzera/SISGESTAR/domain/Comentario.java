package com.basis.darkzera.SISGESTAR.domain;

import liquibase.pro.packaged.F;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_comentario")
    private LocalDateTime data_comentario;

    @JoinColumn(name = "id_tarefa")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tarefa tarefa;

    @JoinColumn(name = "id_usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

}

package com.basis.darkzera.SISGESTAR.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario_responsavel")
    private Usuario responsavel;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "rel_tarefa_usuario",
            joinColumns = { @JoinColumn (name = "id_tarefa")},
            inverseJoinColumns = { @JoinColumn (name = "id_usuario")})
    private List<Usuario> acompanhadores = new ArrayList<>();

    @Column(name = "id_st_tarefa")
    private Long idStatus;

    @OneToMany(mappedBy = "tarefa")
    private List<Comentario> comentarios = new ArrayList<>();


}

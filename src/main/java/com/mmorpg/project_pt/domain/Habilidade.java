package com.mmorpg.project_pt.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "habilidade")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Habilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHabilidade;

    @Column(nullable = false, length = 100)
    @NotNull
    private String nome;

    @Column(nullable = false, length = 50)
    @NotNull
    private String elemento;

    @Column(nullable = false, name = "Descrição")
    private String descrição;

    @Column(nullable = false, length = 50)
    @NotNull
    private String alvo;

    @Column(nullable = false, name = "valor_dano_recuperacao")
    private int valorDanoRecuperacao = 0;

    @OneToMany(mappedBy = "habilidade", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PersonagemHabilidade> personagemHabilidades = new ArrayList<>();
}
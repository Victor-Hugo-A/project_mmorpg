package com.mmorpg.project_pt.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Habilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHabilidade;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String alvo;

    @Column(nullable = false)
    private String elemento;

    @OneToMany(mappedBy = "habilidade", cascade = CascadeType.ALL)
    private Set<PersonagemHabilidade> personagens;
}
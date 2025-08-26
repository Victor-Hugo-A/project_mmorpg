package com.mmorpg.project_pt.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(PersonagemHabilidadeId.class)
@Table(name = "personagem_habilidade")
public class PersonagemHabilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersonagem", nullable = false)
    private Personagem personagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idHabilidade", nullable = false)
    private Habilidade habilidade;
}
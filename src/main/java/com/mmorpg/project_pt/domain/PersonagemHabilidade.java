package com.mmorpg.project_pt.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "personagem_habilidade")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(PersonagemHabilidadeId.class)
public class PersonagemHabilidade {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_personagem", nullable = false)
    @NotNull
    private Personagem personagem;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_habilidade", nullable = false)
    @NotNull
    private Habilidade habilidade;
}
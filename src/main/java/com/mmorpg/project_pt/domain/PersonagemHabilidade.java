package com.mmorpg.project_pt.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "personagem_habilidade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PersonagemHabilidade {

    @EmbeddedId
    private PersonagemHabilidadeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personagemId") // mapeia a FK dentro da chave composta
    @JoinColumn(name = "id_personagem", nullable = false)
    private Personagem personagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("habilidadeId") // mapeia a FK dentro da chave composta
    @JoinColumn(name = "id_habilidade", nullable = false)
    private Habilidade habilidade;
}

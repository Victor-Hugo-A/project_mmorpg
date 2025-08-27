package com.mmorpg.project_pt.domain;

import lombok.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonagemHabilidadeId implements Serializable {
    private Long personagem;
    private Long habilidade;
}
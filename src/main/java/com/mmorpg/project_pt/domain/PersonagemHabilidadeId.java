package com.mmorpg.project_pt.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
public class PersonagemHabilidadeId implements Serializable {
    private Long personagemId;
    private Long habilidadeId;

    public PersonagemHabilidadeId() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonagemHabilidadeId that)) return false;
        return Objects.equals(personagemId, that.personagemId) &&
                Objects.equals(habilidadeId, that.habilidadeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personagemId, habilidadeId);
    }
}

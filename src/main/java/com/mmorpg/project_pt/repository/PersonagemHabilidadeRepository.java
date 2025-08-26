package com.mmorpg.project_pt.repository;

import com.mmorpg.project_pt.domain.PersonagemHabilidade;
import com.mmorpg.project_pt.domain.PersonagemHabilidadeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonagemHabilidadeRepository extends JpaRepository<PersonagemHabilidade, PersonagemHabilidadeId> {
}

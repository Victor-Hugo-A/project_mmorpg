package com.mmorpg.project_pt.repository;

import com.mmorpg.project_pt.domain.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
}

package com.mmorpg.project_pt.repository;

import com.mmorpg.project_pt.domain.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    Jogador findByEmail(String email);
}

package com.mmorpg.project_pt.service;

import com.mmorpg.project_pt.domain.Jogador;
import com.mmorpg.project_pt.repository.JogadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public List<Jogador> findAll() {
        return jogadorRepository.findAll();
    }

    public Optional<Jogador> findById(Long id) {
        return jogadorRepository.findById(id);
    }

    public Jogador save(Jogador jogador) {
        return jogadorRepository.save(jogador);
    }

    public void deleteById(Long id) {
        jogadorRepository.deleteById(id);
    }

    public Jogador findByEmail(String email) {
        return jogadorRepository.findByEmail(email);
    }
}
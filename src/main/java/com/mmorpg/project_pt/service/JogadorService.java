package com.mmorpg.project_pt.service;

import com.mmorpg.project_pt.domain.Jogador;
import com.mmorpg.project_pt.dto.CriarJogadorDTO;
import com.mmorpg.project_pt.repository.JogadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public Jogador criar(CriarJogadorDTO dto) {
        Jogador jogador = new Jogador();
        jogador.setNome(dto.getNome());
        jogador.setEmail(dto.getEmail());
        return jogadorRepository.save(jogador);
    }

    public List<Jogador> listarTodos() {
        return jogadorRepository.findAll();
    }
}

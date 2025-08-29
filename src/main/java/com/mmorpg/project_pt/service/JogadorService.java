package com.mmorpg.project_pt.service;

import com.mmorpg.project_pt.domain.Jogador;
import com.mmorpg.project_pt.dto.CriarJogadorDTO;
import com.mmorpg.project_pt.dto.UpdateJogadorDTO;
import com.mmorpg.project_pt.repository.JogadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public Jogador criar(CriarJogadorDTO dto) {
        // Verifica se o email já existe antes de ser salvo
        if (JogadorRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }
        Jogador jogador = new Jogador();
        jogador.setNome(dto.getNome());
        jogador.setEmail(dto.getEmail());

        try {
            return jogadorRepository.save(jogador);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Email já cadastrado");
        }
    }

    public void deletar(Long id) {
        if (!jogadorRepository.existsById(id)) {
            throw new RuntimeException("Jogador não encontrado com ID: " + id);
        }
        try {
            jogadorRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Não é possível excluir Jogador com ID: " + id + "ele pode ter personagens associados");
        }
    }

    public Jogador atualizar(Long id, UpdateJogadorDTO dto) {
        Jogador jogador = jogadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado com ID: " + id));

        // Verifica se o novo email já existe (se foi fornecido e é diferente do atual)
        if (dto.getEmail() != null && !dto.getEmail().equals(jogador.getEmail())) {
            if (JogadorRepository.existsByEmail((dto.getEmail()))) {
                throw new RuntimeException("Email já cadastrado: " + dto.getEmail());
            }
        }

        // Atualiza apenas os campos que foram fornecidos no DTO
        if (dto.getNome() != null) {
            jogador.setNome(dto.getNome());
        }

        if (dto.getEmail() != null) {
            jogador.setEmail(dto.getEmail());
        }

        try {
            return jogadorRepository.save(jogador);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro de integridade de dados: " + e.getMessage());
        }
    }

    public List<Jogador> listarTodos() {
        return jogadorRepository.findAll();
    }
}

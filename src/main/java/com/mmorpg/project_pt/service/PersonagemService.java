package com.mmorpg.project_pt.service;

import com.mmorpg.project_pt.domain.Cla;
import com.mmorpg.project_pt.domain.Equipamento;
import com.mmorpg.project_pt.domain.Jogador;
import com.mmorpg.project_pt.domain.Personagem;
import com.mmorpg.project_pt.dto.CriarPersonagemDTO;
import com.mmorpg.project_pt.dto.UpdatePersonagemDTO;
import com.mmorpg.project_pt.repository.ClaRepository;
import com.mmorpg.project_pt.repository.EquipamentoRepository;
import com.mmorpg.project_pt.repository.JogadorRepository;
import com.mmorpg.project_pt.repository.PersonagemRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonagemService {

    private final PersonagemRepository personagemRepository;
    private final JogadorRepository jogadorRepository;
    private final EquipamentoRepository equipamentoRepository;
    private final ClaRepository claRepository;

    // Listar todos
    public List<Personagem> listarTodos() {
        return personagemRepository.findAll();
    }

    // Editar Personagem
    public Personagem editar(Long id, UpdatePersonagemDTO dto) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        // Atualiza só os campos enviados no DTO
        if (dto.getClasse() != null) personagem.setClasse(dto.getClasse());
        if (dto.getNivel() != null) personagem.setNivel(dto.getNivel());
        if (dto.getAtaque() != null) personagem.setAtaque(dto.getAtaque());
        if (dto.getDefesa() != null) personagem.setDefesa(dto.getDefesa());
        if (dto.getMagia() != null) personagem.setMagia(dto.getMagia());
        if (dto.getEspirito() != null) personagem.setEspirito(dto.getEspirito());

        return personagemRepository.save(personagem);
    }

    // Criar personagem a partir do DTO
    public Personagem criar(CriarPersonagemDTO dto) {
        Jogador jogador = jogadorRepository.findById(dto.getIdJogador())
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        Personagem personagem = new Personagem();
        personagem.setClasse(dto.getClasse());
        personagem.setNivel(1);
        personagem.setAtaque(5);
        personagem.setDefesa(3);
        personagem.setMagia(2);
        personagem.setEspirito(10);
        personagem.setJogador(jogador);

        return personagemRepository.save(personagem);
    }

    public void deletar(Long id) {
        personagemRepository.deleteById(id);
    }
}

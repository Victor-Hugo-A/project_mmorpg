package com.mmorpg.project_pt.service;

import com.mmorpg.project_pt.domain.Personagem;
import com.mmorpg.project_pt.repository.PersonagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonagemService {
    private final PersonagemRepository personagemRepository;
    public List<Personagem> listarTodos() {
        return personagemRepository.findAll();
    }

    public Personagem salvar(Personagem personagem) {
        return personagemRepository.save(personagem);
    }

    public void deletar(Long id) {
        personagemRepository.deleteById(id);
    }
}

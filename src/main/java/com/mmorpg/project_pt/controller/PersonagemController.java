package com.mmorpg.project_pt.controller;

import com.mmorpg.project_pt.domain.Personagem;
import com.mmorpg.project_pt.dto.CriarPersonagemDTO;
import com.mmorpg.project_pt.dto.UpdatePersonagemDTO;
import com.mmorpg.project_pt.service.PersonagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/personagens")
@RequiredArgsConstructor
public class PersonagemController {

    private final PersonagemService personagemService;

    @GetMapping
    public ResponseEntity<List<Personagem>> listar() {
        return ResponseEntity.ok(personagemService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Personagem> criar(@RequestBody CriarPersonagemDTO dto) {
        Personagem personagem = personagemService.criar(dto);
        return ResponseEntity.ok(personagem);
    }

    @PutMapping("/{id}")
    public Personagem editar(@PathVariable Long id, @RequestBody UpdatePersonagemDTO dto) {
        return personagemService.editar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        personagemService.deletar(id);
    }
}
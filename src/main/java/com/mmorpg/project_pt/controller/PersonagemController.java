package com.mmorpg.project_pt.controller;

import com.mmorpg.project_pt.domain.Personagem;
import com.mmorpg.project_pt.service.PersonagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/personagens")
@RequiredArgsConstructor
public class PersonagemController {
    private final PersonagemService personagemService;
    @GetMapping
    public List<Personagem> listar() {
        return personagemService.listarTodos();
    }

    @PostMapping
    public Personagem criar(@RequestBody Personagem personagem) {
        return personagemService.salvar(personagem);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        personagemService.deletar(id);
    }
}
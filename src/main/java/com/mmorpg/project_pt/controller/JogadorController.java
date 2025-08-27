package com.mmorpg.project_pt.controller;

import com.mmorpg.project_pt.domain.Jogador;
import com.mmorpg.project_pt.dto.CriarJogadorDTO;
import com.mmorpg.project_pt.service.JogadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogadores")
@RequiredArgsConstructor
public class JogadorController {

    private final JogadorService jogadorService;

    @PostMapping
    public ResponseEntity<Jogador> criar(@RequestBody CriarJogadorDTO jogador) {
        return ResponseEntity.ok(jogadorService.criar(jogador));
    }

    @GetMapping
    public ResponseEntity<List<Jogador>> listar() {
        return ResponseEntity.ok(jogadorService.listarTodos());
    }
}

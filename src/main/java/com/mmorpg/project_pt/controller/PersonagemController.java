package com.mmorpg.project_pt.controller;

import com.mmorpg.project_pt.domain.Personagem;
import com.mmorpg.project_pt.dto.CriarPersonagemDTO;
import com.mmorpg.project_pt.dto.UpdatePersonagemDTO;
import com.mmorpg.project_pt.service.PersonagemService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/personagens")
@RequiredArgsConstructor
public class PersonagemController {

    private final PersonagemService personagemService;

    @GetMapping
    public ResponseEntity<Object> listar() {
        List<Personagem> personagens = personagemService.listarTodos();

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now().toString());

        if (personagens.isEmpty()) {
            response.put("success", false);
            response.put("message", "Nenhum personagem encontrado no sistema");
            response.put("count", 0);
            response.put("data", Collections.emptyList());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.put("success", true);
        response.put("message", "Personagens encontrados com sucesso");
        response.put("count", personagens.size());
        response.put("data", personagens);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> criar(@RequestBody CriarPersonagemDTO dto) {
        try {
            personagemService.criar(dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(@PathVariable Long id, @RequestBody UpdatePersonagemDTO dto) {
        try {
            personagemService.editar(id, dto);
            return ResponseEntity.ok().body("Personagem atualizado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            personagemService.deletar(id);
            return ResponseEntity.ok().body("Personagem deletado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
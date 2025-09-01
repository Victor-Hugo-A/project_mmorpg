package com.mmorpg.project_pt.controller;

import com.mmorpg.project_pt.domain.Personagem;
import com.mmorpg.project_pt.dto.CriarPersonagemDTO;
import com.mmorpg.project_pt.dto.UpdatePersonagemDTO;
import com.mmorpg.project_pt.service.PersonagemService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Object> criar(@RequestBody CriarPersonagemDTO dto) {
        try {
            Personagem personagemCriado = personagemService.criar(dto);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Personagem criado com sucesso");
            response.put("data", personagemCriado);
            response.put("timestamp", LocalDateTime.now().toString());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", e.getMessage());
            errorResponse.put("timestamp", LocalDateTime.now().toString());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(@PathVariable Long id, @RequestBody UpdatePersonagemDTO dto) {

            Personagem personagemAtualizado = personagemService.editar(id, dto);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Personagem atualizado com sucesso");
            response.put("data", personagemAtualizado);
            response.put("timestamp", LocalDateTime.now().toString());

            return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
            personagemService.deletar(id);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Personagem deletado com sucesso");
            response.put("deletedId", id);
            response.put("timestamp", LocalDateTime.now().toString());

            return ResponseEntity.ok().body(response);
        }
    }















/*
package com.mmorpg.project_pt.controller;

import com.mmorpg.project_pt.domain.Personagem;
import com.mmorpg.project_pt.dto.CriarPersonagemDTO;
import com.mmorpg.project_pt.dto.UpdatePersonagemDTO;
import com.mmorpg.project_pt.service.PersonagemService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Object> criar(@RequestBody CriarPersonagemDTO dto) {
        try {
            Personagem personagemCriado = personagemService.criar(dto);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Personagem criado com sucesso");
            response.put("data", personagemCriado);
            response.put("timestamp", LocalDateTime.now().toString());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (RuntimeException e) {
            return handleRuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(@PathVariable Long id, @RequestBody UpdatePersonagemDTO dto) {
        try {
            Personagem personagemAtualizado = personagemService.editar(id, dto);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Personagem atualizado com sucesso");
            response.put("data", personagemAtualizado);
            response.put("timestamp", LocalDateTime.now().toString());

            return ResponseEntity.ok().body(response);

        } catch (RuntimeException e) {
            return handleRuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        try {
            personagemService.deletar(id);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Personagem deletado com sucesso");
            response.put("deletedId", id);
            response.put("timestamp", LocalDateTime.now().toString());

            return ResponseEntity.ok().body(response);

        } catch (RuntimeException e) {
            return handleRuntimeException(e);
        }
    }

    // Método auxiliar para lidar com exceções
    private ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("success", false);
        errorResponse.put("message", e.getMessage());
        errorResponse.put("timestamp", LocalDateTime.now().toString());

        if (e.getMessage().contains("não encontrado")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
*/
package com.mmorpg.project_pt.controller;

import com.mmorpg.project_pt.domain.Jogador;
import com.mmorpg.project_pt.dto.CriarJogadorDTO;
import com.mmorpg.project_pt.dto.UpdateJogadorDTO;
import com.mmorpg.project_pt.service.JogadorService;
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
@RequestMapping("/jogadores")
@RequiredArgsConstructor
public class JogadorController {

    private final JogadorService jogadorService;

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody CriarJogadorDTO jogadorDTO) {
        try {
            Jogador jogadorCriado = jogadorService.criar(jogadorDTO);

            // Retorna uma resposta JSON mais completa
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Jogador criado com sucesso");
            response.put("data", jogadorCriado);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (RuntimeException e) {
            // Verifica se é erro de email duplicado
            if (e.getMessage().contains("Email já cadastrado")) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("error", "CONFLITO_EMAIL");
                errorResponse.put("message", e.getMessage());
                errorResponse.put("email", jogadorDTO.getEmail());

                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
            }

            // Outros erros
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "ERRO_INTERNO");
            errorResponse.put("message", "Erro ao criar jogador: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        try {
            jogadorService.deletar(id);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Jogador deletado com sucesso");
            response.put("deletedId", id);
            response.put("timestamp", LocalDateTime.now().toString());

            return ResponseEntity.ok().body(response);

        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("timestamp", LocalDateTime.now().toString());

            if (e.getMessage().contains("Jogador não encontrado")) {
                errorResponse.put("error", "JOGADOR_NAO_ENCONTRADO");
                errorResponse.put("message", e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            } else if (e.getMessage().contains("personagens associados")) {
                errorResponse.put("error", "RESTRICAO_INTEGRIDADE");
                errorResponse.put("message", e.getMessage());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
            } else {
                errorResponse.put("error", "ERRO_INTERNO");
                errorResponse.put("message", "Erro ao deletar jogador: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarCompleto(@PathVariable Long id, @RequestBody UpdateJogadorDTO dto) {
        try {
            Jogador jogadorAtualizado = jogadorService.atualizar(id, dto);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Jogador atualizado com sucesso");
            response.put("data", jogadorAtualizado);
            response.put("timestamp", LocalDateTime.now().toString());

            return ResponseEntity.ok().body(response);

        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("timestamp", LocalDateTime.now().toString());

            if (e.getMessage().contains("Jogador não encontrado")) {
                errorResponse.put("error", "JOGADOR_NAO_ENCONTRADO");
                errorResponse.put("message", e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            } else if (e.getMessage().contains("Email já cadastrado")) {
                errorResponse.put("error", "EMAIL_DUPLICADO");
                errorResponse.put("message", e.getMessage());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
            } else {
                errorResponse.put("error", "ERRO_INTERNO");
                errorResponse.put("message", "Erro ao atualizar jogador: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
            }
        }
    }

    @GetMapping
    public ResponseEntity<Object> listarJogadores() {
        List<Jogador> jogadores = jogadorService.listarTodos();

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now().toString());

        if (jogadores.isEmpty()) {
            response.put("success", false);
            response.put("message", "Nenhum jogador encontrado no sistema");
            response.put("count", 0);
            response.put("data", Collections.emptyList());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.put("success", true);
        response.put("message", "Jogadores encontrados com sucesso");
        response.put("count", jogadores.size());
        response.put("data", jogadores);

        return ResponseEntity.ok(response);
    }
}

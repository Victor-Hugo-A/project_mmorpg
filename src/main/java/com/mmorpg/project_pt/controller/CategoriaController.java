package com.mmorpg.project_pt.controller;

import com.mmorpg.project_pt.domain.Categoria;
import com.mmorpg.project_pt.dto.CategoriaDTO;
import com.mmorpg.project_pt.dto.CategoriaResponseCompletoDTO;
import com.mmorpg.project_pt.dto.CategoriaResponseDTO;
import com.mmorpg.project_pt.dto.UpdateCategoriaDTO;
import com.mmorpg.project_pt.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criar(@RequestBody CategoriaDTO dto) {
        return ResponseEntity.ok(categoriaService.criarCategoria(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody UpdateCategoriaDTO dto) {
        return ResponseEntity.ok(categoriaService.atualizarCategoria(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listar() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    // Busca por id usando DTO completo
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseCompletoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorIdCompleto(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}

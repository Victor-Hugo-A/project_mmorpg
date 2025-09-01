package com.mmorpg.project_pt.controller;

import com.mmorpg.project_pt.domain.Categoria;
import com.mmorpg.project_pt.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        return categoriaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@Valid @RequestBody Categoria categoria) {
        if (categoriaService.existsByNome(categoria.getNome())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(categoriaService.save(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
        if (categoriaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoria.setIdCategoria(id);
        return ResponseEntity.ok(categoriaService.save(categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        if (categoriaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
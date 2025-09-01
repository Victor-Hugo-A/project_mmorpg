package com.mmorpg.project_pt.service;

import com.mmorpg.project_pt.domain.Categoria;
import com.mmorpg.project_pt.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }

    public boolean existsByNome(String nome) {
        return categoriaRepository.existsByNome(nome);
    }

    public boolean existsById(Long id) {
        return !categoriaRepository.existsById(id);
    }
}
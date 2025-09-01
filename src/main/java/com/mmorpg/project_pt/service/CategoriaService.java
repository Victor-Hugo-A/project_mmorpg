package com.mmorpg.project_pt.service;

import com.mmorpg.project_pt.domain.Categoria;
import com.mmorpg.project_pt.dto.*;
import com.mmorpg.project_pt.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    private CategoriaResponseDTO toResponseDTO(Categoria categoria) {
        return new CategoriaResponseDTO(
                categoria.getIdCategoria(),
                categoria.getNome(),
                categoria.getDescricao()
        );
    }

    // Conversão para resposta completa com equipamentos
    private CategoriaResponseCompletoDTO toResponseCompletoDTO(Categoria categoria) {
        List<EquipamentoResumoDTO> equipamentos = categoria.getEquipamentos()
                .stream()
                .map(eq -> new EquipamentoResumoDTO(eq.getIdItem(), eq.getNome()))
                .toList();

        return new CategoriaResponseCompletoDTO(
                categoria.getIdCategoria(),
                categoria.getNome(),
                categoria.getDescricao(),
                equipamentos
        );
    }


    // Criar Categoria
    public CategoriaResponseDTO criarCategoria(CategoriaDTO dto) {
        Categoria categoria = Categoria.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .build();
        return toResponseDTO(categoriaRepository.save(categoria));
    }

    // Atualizar Categoria
    public Categoria atualizarCategoria(Long id, UpdateCategoriaDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());

        return categoriaRepository.save(categoria);
    }

    // Listar todas as categorias (resposta simples)
    public List<CategoriaResponseDTO> listarCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Buscar categoria por id (resposta completa)
    public CategoriaResponseCompletoDTO buscarPorIdCompleto(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
        return toResponseCompletoDTO(categoria);
    }

    public void deletarCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoria não encontrada");
        }
        categoriaRepository.deleteById(id);
    }
}

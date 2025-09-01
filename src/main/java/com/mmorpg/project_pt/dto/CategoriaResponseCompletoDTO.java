package com.mmorpg.project_pt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoriaResponseCompletoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private List<EquipamentoResumoDTO> equipamentos;
}

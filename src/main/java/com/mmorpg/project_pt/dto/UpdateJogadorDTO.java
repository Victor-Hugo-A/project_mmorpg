package com.mmorpg.project_pt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateJogadorDTO {

    @NotBlank(message = "Nome é obrigatório")
    @NotNull(message = "Nome não pode ser nulo")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @NotNull(message = "Email não pode ser nulo")
    private String email;
}
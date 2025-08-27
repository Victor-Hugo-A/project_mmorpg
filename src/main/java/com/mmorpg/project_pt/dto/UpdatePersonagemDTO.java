package com.mmorpg.project_pt.dto;

import lombok.Data;

@Data
public class UpdatePersonagemDTO {
    private String classe;
    private Integer nivel;
    private Integer ataque;
    private Integer defesa;
    private Integer magia;
    private Integer espirito;
    private Long idCla;
    private Long idArma;
    private Long idArmadura;
    private Long idAcessorio1;
    private Long idAcessorio2;
}

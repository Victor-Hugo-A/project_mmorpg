package com.mmorpg.project_pt.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "idItem") // Liga a tabela de Equipamento à tabela de Item
public class Equipamento extends Item {

    @Column(nullable = false)
    private int ataque;

    @Column(nullable = false)
    private int defesa;

    @Column(nullable = false)
    private int magia;

    @Column(nullable = false)
    private int espirito;

    @Column(nullable = false)
    private String categoria;
}
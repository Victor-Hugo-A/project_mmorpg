package com.mmorpg.project_pt.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipamento")
@PrimaryKeyJoinColumn(name = "idEquipamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipamento extends Item {

    @Column(nullable = false)
    private int ataque;

    @Column(nullable = false)
    private int defesa;

    @Column(nullable = false)
    private int magia;

    @Column(nullable = false)
    private int espirito;

    @Column(nullable = false, length = 50)
    @NotNull
    private String categoria; // arma, armadura, acessório

    @OneToMany(mappedBy = "arma", fetch = FetchType.LAZY)
    private List<Personagem> personagensComArma = new ArrayList<>();

    @OneToMany(mappedBy = "armadura", fetch = FetchType.LAZY)
    private List<Personagem> personagensComArmadura = new ArrayList<>();

    @OneToMany(mappedBy = "acessorio1", fetch = FetchType.LAZY)
    private List<Personagem> personagensComAcessorio1 = new ArrayList<>();

    @OneToMany(mappedBy = "acessorio2", fetch = FetchType.LAZY)
    private List<Personagem> personagensComAcessorio2 = new ArrayList<>();
}
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "arma", fetch = FetchType.LAZY)
    private List<Personagem> personagensComArma = new ArrayList<>();

    @OneToMany(mappedBy = "armadura", fetch = FetchType.LAZY)
    private List<Personagem> personagensComArmadura = new ArrayList<>();

    @OneToMany(mappedBy = "acessorio1", fetch = FetchType.LAZY)
    private List<Personagem> personagensComAcessorio1 = new ArrayList<>();

    @OneToMany(mappedBy = "acessorio2", fetch = FetchType.LAZY)
    private List<Personagem> personagensComAcessorio2 = new ArrayList<>();
}
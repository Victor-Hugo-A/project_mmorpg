package com.mmorpg.project_pt.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "bolsa_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(BolsaItemId.class)
public class BolsaItem {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_personagem", nullable = false)
    @NotNull
    private Personagem personagem;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_item", nullable = false)
    @NotNull
    private Item item;

    @Column(nullable = false)
    private int quantidade = 1;
}
package com.mmorpg.project_pt.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(BolsaItemId.class)
@Table(name = "bolsa_item")
public class BolsaItem {
    @Id
    @ManyToOne
    @JoinColumn(name = "idPersonagem", nullable = false)
    private Personagem personagem;

    @Id
    @ManyToOne
    @JoinColumn(name = "IdItem", nullable = false)
    private Item item;

    @Column(nullable = false)
    private int quantidade;
}
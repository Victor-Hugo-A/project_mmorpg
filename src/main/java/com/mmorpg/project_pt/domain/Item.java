package com.mmorpg.project_pt.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    @Column(nullable = false, length = 100)
    @NotNull
    private String nome;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotNull
    private String descricao;

    @Column(nullable = false)
    private double valor;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BolsaItem> bolsaItens = new ArrayList<>();
}
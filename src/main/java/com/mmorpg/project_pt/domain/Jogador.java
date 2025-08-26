package com.mmorpg.project_pt.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJogador;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "jogador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Personagem> personagens = new ArrayList<>();
}

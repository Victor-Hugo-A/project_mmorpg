package com.mmorpg.project_pt.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personagem")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersonagem;

    @Column(nullable = false, length = 50)
    @NotNull
    private String classe;

    @Column(nullable = false)
    private int nivel = 1;

    @Column(nullable = false)
    private int ataque = 0;

    @Column(nullable = false)
    private int defesa = 0;

    @Column(nullable = false)
    private int magia = 0;

    @Column(nullable = false)
    private int espirito = 0;

    // Relacionamentos ManyToOne
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_jogador", nullable = false)
    @NotNull
    private Jogador jogador;

    @ManyToOne
    @JoinColumn(name = "id_cla")
    private Cla cla;

    @ManyToOne
    @JoinColumn(name = "id_arma")
    private Equipamento arma;

    @ManyToOne
    @JoinColumn(name = "id_armadura")
    private Equipamento armadura;

    @ManyToOne
    @JoinColumn(name = "id_acessorio1")
    private Equipamento acessorio1;

    @ManyToOne
    @JoinColumn(name = "id_acessorio2")
    private Equipamento acessorio2;

    // Relacionamentos OneToMany
    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BolsaItem> bolsaItens = new ArrayList<>();

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PersonagemHabilidade> personagemHabilidades = new ArrayList<>();
}
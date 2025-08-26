package com.mmorpg.project_pt.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersonagem;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String classe;

    @Column(nullable = false)
    private int ataque;

    @Column(nullable = false)
    private int defesa;

    @Column(nullable = false)
    private int magia;

    @Column(nullable = false)
    private int espirito;

    @Column(nullable = false)
    private int nivel;


    // Cardinalidade ManyToOne (obrigatórias)
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_jogador", nullable = false)
    private Jogador jogador;

    @ManyToOne
    @JoinColumn(name = "id_cla")
    private Cla cla;

    // Equipamentos (opcionais - personagem pode não ter equipado)
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

    // Cardinalidades OneToMany (bolsa de itens)
    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BolsaItem> bolsaItens = new ArrayList<>();

    // Cardinalidades OneToMany (habilidades)
    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PersonagemHabilidade> personagemHabilidades = new ArrayList<>();
}
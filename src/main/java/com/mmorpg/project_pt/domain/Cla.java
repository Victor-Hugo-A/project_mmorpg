package com.mmorpg.project_pt.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCla;

    @Column(unique = true, nullable = false)
    private String nome;

    @Column(nullable = false)
    private String brasao;

    @OneToMany(mappedBy = "cla", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Personagem> personagens = new ArrayList<>();
}
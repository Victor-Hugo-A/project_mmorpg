package com.mmorpg.project_pt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cla")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCla;

    @Column(unique = true, nullable = false, length = 100)
    @NotNull
    private String nome;

    @Column(nullable = false, length = 100)
    @NotNull
    private String brasao;

    @OneToMany(mappedBy = "cla", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Personagem> personagens = new ArrayList<>();
}
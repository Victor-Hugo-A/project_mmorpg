package com.mmorpg.project_pt.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "personagem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"jogador", "cla", "bolsaItens", "personagemHabilidades"})
@EqualsAndHashCode(of = "idPersonagem")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersonagem;

    @Column(nullable = false, length = 50)
    @NotNull(message = "A classe do personagem é obrigatória")
    @Size(min = 2, max = 50, message = "A classe deve ter entre 2 e 50 caracteres")
    private String classe;

    @Column(nullable = false)
    @Min(value = 1, message = "O nível mínimo é 1")
    @Builder.Default
    private int nivel = 1;

    @Column(nullable = false)
    @Min(value = 0, message = "O ataque não pode ser negativo")
    @Builder.Default
    private int ataque = 0;

    @Column(nullable = false)
    @Min(value = 0, message = "A defesa não pode ser negativa")
    @Builder.Default
    private int defesa = 0;

    @Column(nullable = false)
    @Min(value = 0, message = "A magia não pode ser negativa")
    @Builder.Default
    private int magia = 0;

    @Column(nullable = false)
    @Min(value = 0, message = "O espírito não pode ser negativo")
    @Builder.Default
    private int espirito = 0;

    // Relacionamentos ManyToOne
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_jogador", nullable = false)
    @NotNull(message = "O jogador é obrigatório")
    private Jogador jogador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cla")
    private Cla cla;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_arma")
    private Equipamento arma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_armadura")
    private Equipamento armadura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_acessorio1")
    private Equipamento acessorio1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_acessorio2")
    private Equipamento acessorio2;

    // Relacionamentos OneToMany
    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<BolsaItem> bolsaItens = new ArrayList<>();

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<PersonagemHabilidade> personagemHabilidades = new ArrayList<>();

    // Métodos de negócio
    public int getAtaqueTotal() {
        int ataqueEquipamento = 0;
        if (arma != null) ataqueEquipamento += arma.getAtaque();
        if (armadura != null) ataqueEquipamento += armadura.getAtaque();
        if (acessorio1 != null) ataqueEquipamento += acessorio1.getAtaque();
        if (acessorio2 != null) ataqueEquipamento += acessorio2.getAtaque();

        return this.ataque + ataqueEquipamento;
    }

    public int getDefesaTotal() {
        int defesaEquipamento = 0;
        if (arma != null) defesaEquipamento += arma.getDefesa();
        if (armadura != null) defesaEquipamento += armadura.getDefesa();
        if (acessorio1 != null) defesaEquipamento += acessorio1.getDefesa();
        if (acessorio2 != null) defesaEquipamento += acessorio2.getDefesa();

        return this.defesa + defesaEquipamento;
    }

    public void adicionarItem(BolsaItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo");
        }
        if (bolsaItens.size() >= 20) {
            throw new IllegalStateException("Bolsa cheia! Máximo 20 itens.");
        }
        if (item.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva");
        }

        // Verifica se item já existe na bolsa
        Optional<BolsaItem> existing = bolsaItens.stream()
                .filter(i -> i.getItem().getIdItem().equals(item.getItem().getIdItem()))
                .findFirst();

        if (existing.isPresent()) {
            // Atualiza quantidade se já existir
            existing.get().setQuantidade(existing.get().getQuantidade() + item.getQuantidade());
        } else {
            // Adiciona novo item
            this.bolsaItens.add(item);
            item.setPersonagem(this);
        }
    }

    public void removerItem(BolsaItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo");
        }
        if (!bolsaItens.contains(item)) {
            throw new IllegalArgumentException("Item não encontrado na bolsa");
        }

        this.bolsaItens.remove(item);
        item.setPersonagem(null);
    }

    public void equiparArma(Equipamento novaArma) {
        if (novaArma == null) {
            throw new IllegalArgumentException("Arma não pode ser nula");
        }
        if (!"ARMA".equals(novaArma.getCategoria())) {
            throw new IllegalArgumentException("Equipamento não é uma arma");
        }

        this.arma = novaArma;
    }

    public void adicionarHabilidade(PersonagemHabilidade habilidade) {
        personagemHabilidades.add(habilidade);
        habilidade.setPersonagem(this);
    }

    public void removerHabilidade(PersonagemHabilidade habilidade) {
        personagemHabilidades.remove(habilidade);
        habilidade.setPersonagem(null);
    }

    public void desequiparArma() {
        this.arma = null;
    }

    public void adicionarItem(Item item, int quantidade) {

    }
}
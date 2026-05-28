package com.freitasbrandao.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "evolucoes")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evolucao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(length = 255)
    private String responsavel;
}
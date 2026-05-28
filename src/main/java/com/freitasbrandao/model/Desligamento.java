package com.freitasbrandao.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "desligamentos")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Desligamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @Column(nullable = false)
    private LocalDate data;

    @Column(columnDefinition = "TEXT")
    private String motivo;

    @Column(nullable = false)
    @Builder.Default
    private Boolean devolveuRoupas = false;

    @Column(nullable = false)
    @Builder.Default
    private Boolean levouDocumentos = false;

    @Column(nullable = false)
    @Builder.Default
    private Boolean temLesoes = false;

    @Column(length = 255)
    private String tecnicoResponsavel;

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}
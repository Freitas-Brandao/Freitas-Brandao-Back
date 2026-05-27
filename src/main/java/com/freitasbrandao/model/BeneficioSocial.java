package com.freitasbrandao.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "beneficios_sociais")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeneficioSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", nullable = false, unique = true)
    private Pessoa pessoa;

    @Column(nullable = false)
    @Builder.Default
    private Boolean bolsaFamilia = false;

    @Column(nullable = false)
    @Builder.Default
    private Boolean bpc = false;

    @Column(nullable = false)
    @Builder.Default
    private Boolean auxilioBrasil = false;

    @Column(nullable = false)
    @Builder.Default
    private Boolean seguroDesemprego = false;

    @Column(columnDefinition = "TEXT")
    private String outrosBeneficios;
}
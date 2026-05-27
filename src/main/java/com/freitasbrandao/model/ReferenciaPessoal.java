package com.freitasbrandao.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "referencias_pessoais")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReferenciaPessoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @Column(nullable = false)
    private String nome;

    @Column(length = 20)
    private String telefone;

    @Column(length = 100)
    private String parentesco;
}
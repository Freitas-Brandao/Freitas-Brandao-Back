package com.freitasbrandao.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "pessoas")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Protocolo gerado automaticamente
    @Column(nullable = false, unique = true, length = 20)
    private String protocolo;

    // Informações do acolhimento
    @Column(nullable = false)
    private LocalDate dataAcolhimento;

    private LocalTime horaAcolhimento;

    @Column(nullable = false)
    @Builder.Default
    private Boolean demandaEspontanea = false;

    // Dados pessoais
    @Column(nullable = false)
    private String nome;

    private String nomeSocial;

    private LocalDate dataNascimento;

    private String naturalidade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Genero genero;

    @Column(length = 20)
    private String telefone;

    // Documentação
    // Armazenado sempre sem formatação (11 dígitos)
    @Column(name = "cpf", unique = true, length = 11)
    private String cpf;

    @Column(length = 20)
    private String rg;

    @Column(length = 20)
    private String orgaoExpedidorRg;

    @Column(length = 30)
    private String tituloEleitoral;

    @Column(length = 30)
    private String carteiraTrabalho;

    @Column(length = 50)
    private String certidaoNascimento;

    // Saúde
    @Column(columnDefinition = "TEXT")
    private String condicoesSaude;

    @Column(columnDefinition = "TEXT")
    private String medicamentosEmUso;

    @Column(columnDefinition = "TEXT")
    private String alergiasRestricoes;

    @Column(nullable = false)
    @Builder.Default
    private Boolean usaSubstanciasPsicoativas = false;

    @Column(columnDefinition = "TEXT")
    private String substanciasQuais;

    // Atividades e Participação
    @Column(columnDefinition = "TEXT")
    private String atividadesRealizadas;

    @Column(columnDefinition = "TEXT")
    private String oficinasParticipadas;

    // Observações gerais
    @Column(columnDefinition = "TEXT")
    private String observacoes;

    // Termo de orientação
    @Column(nullable = false)
    @Builder.Default
    private Boolean aceitouTermo = false;

    private LocalDate dataAssinaturaTermo;

    // Controle de datas
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataPrimeiroCadastro;

    private LocalDate ultimaDataEntrada;

    private LocalDate ultimaDataSaida;

    @PrePersist
    protected void onCreate() {
        this.dataPrimeiroCadastro = LocalDateTime.now();
    }
}

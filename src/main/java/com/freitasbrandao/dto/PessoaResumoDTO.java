package com.freitasbrandao.dto;

import com.freitasbrandao.model.Genero;
import com.freitasbrandao.model.Pessoa;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PessoaResumoDTO {

    private Long id;
    private String protocolo;
    private String nome;
    private String nomeSocial;
    private String cpf;
    private Genero genero;
    private LocalDate dataAcolhimento;
    private LocalDate ultimaDataEntrada;
    private LocalDate ultimaDataSaida;

    public static PessoaResumoDTO fromEntity(Pessoa p) {
        PessoaResumoDTO dto = new PessoaResumoDTO();
        dto.setId(p.getId());
        dto.setProtocolo(p.getProtocolo());
        dto.setNome(p.getNome());
        dto.setNomeSocial(p.getNomeSocial());
        dto.setCpf(p.getCpf());
        dto.setGenero(p.getGenero());
        dto.setDataAcolhimento(p.getDataAcolhimento());
        dto.setUltimaDataEntrada(p.getUltimaDataEntrada());
        dto.setUltimaDataSaida(p.getUltimaDataSaida());
        return dto;
    }
}
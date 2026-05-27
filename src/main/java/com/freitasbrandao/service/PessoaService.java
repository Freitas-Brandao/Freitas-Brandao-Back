
package com.freitasbrandao.service;

import com.freitasbrandao.dto.PessoaRequestDTO;
import com.freitasbrandao.dto.PessoaResponseDTO;
import com.freitasbrandao.dto.PessoaResumoDTO;
import com.freitasbrandao.exception.ResourceNotFoundException;
import com.freitasbrandao.model.Genero;
import com.freitasbrandao.model.Pessoa;
import com.freitasbrandao.repository.PessoaRepository;
import com.freitasbrandao.repository.PessoaSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaResponseDTO cadastrar(PessoaRequestDTO dto) {
        if (dto.getCpf() != null && pessoaRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("Já existe uma pessoa cadastrada com este CPF.");
        }
        if (pessoaRepository.existsByProtocolo(dto.getProtocolo())) {
            throw new IllegalArgumentException("Já existe um cadastro com este protocolo.");
        }

        Pessoa pessoa = toEntity(new Pessoa(), dto);
        return PessoaResponseDTO.fromEntity(pessoaRepository.save(pessoa));
    }

    public Page<PessoaResumoDTO> listar(String nome, String cpf,
                                        String protocolo, Genero genero,
                                        Pageable pageable) {
        return pessoaRepository
                .findAll(PessoaSpecification.filtrar(nome, cpf, protocolo, genero), pageable)
                .map(PessoaResumoDTO::fromEntity);
    }

    public PessoaResponseDTO buscarPorId(Long id) {
        return PessoaResponseDTO.fromEntity(buscarEntidade(id));
    }

    public PessoaResponseDTO atualizar(Long id, PessoaRequestDTO dto) {
        Pessoa pessoa = buscarEntidade(id);

        // Verifica conflito de CPF com outra pessoa
        if (dto.getCpf() != null) {
            pessoaRepository.findByCpf(dto.getCpf()).ifPresent(existente -> {
                if (!existente.getId().equals(id)) {
                    throw new IllegalArgumentException("CPF já pertence a outro cadastro.");
                }
            });
        }

        // Verifica conflito de protocolo com outro cadastro
        pessoaRepository.findByProtocolo(dto.getProtocolo()).ifPresent(existente -> {
            if (!existente.getId().equals(id)) {
                throw new IllegalArgumentException("Protocolo já pertence a outro cadastro.");
            }
        });

        return PessoaResponseDTO.fromEntity(pessoaRepository.save(toEntity(pessoa, dto)));
    }

    public void deletar(Long id) {
        pessoaRepository.delete(buscarEntidade(id));
    }

    // ---- helpers ----

    public Pessoa buscarEntidade(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Pessoa não encontrada com id: " + id));
    }

    private Pessoa toEntity(Pessoa pessoa, PessoaRequestDTO dto) {
        pessoa.setProtocolo(dto.getProtocolo());
        pessoa.setDataAcolhimento(dto.getDataAcolhimento());
        pessoa.setHoraAcolhimento(dto.getHoraAcolhimento());
        pessoa.setDemandaEspontanea(Boolean.TRUE.equals(dto.getDemandaEspontanea()));
        pessoa.setNome(dto.getNome());
        pessoa.setNomeSocial(dto.getNomeSocial());
        pessoa.setDataNascimento(dto.getDataNascimento());
        pessoa.setNaturalidade(dto.getNaturalidade());
        pessoa.setGenero(dto.getGenero());
        pessoa.setTelefone(dto.getTelefone());
        pessoa.setCpf(dto.getCpf());
        pessoa.setRg(dto.getRg());
        pessoa.setOrgaoExpedidorRg(dto.getOrgaoExpedidorRg());
        pessoa.setTituloEleitoral(dto.getTituloEleitoral());
        pessoa.setCarteiraTrabalho(dto.getCarteiraTrabalho());
        pessoa.setCertidaoNascimento(dto.getCertidaoNascimento());
        pessoa.setCondicoesSaude(dto.getCondicoesSaude());
        pessoa.setMedicamentosEmUso(dto.getMedicamentosEmUso());
        pessoa.setAlergiasRestricoes(dto.getAlergiasRestricoes());
        pessoa.setUsaSubstanciasPsicoativas(Boolean.TRUE.equals(dto.getUsaSubstanciasPsicoativas()));
        pessoa.setSubstanciasQuais(dto.getSubstanciasQuais());
        pessoa.setAtividadesRealizadas(dto.getAtividadesRealizadas());
        pessoa.setOficinasParticipadas(dto.getOficinasParticipadas());
        pessoa.setObservacoes(dto.getObservacoes());
        pessoa.setAceitouTermo(Boolean.TRUE.equals(dto.getAceitouTermo()));
        pessoa.setDataAssinaturaTermo(dto.getDataAssinaturaTermo());
        pessoa.setUltimaDataEntrada(dto.getUltimaDataEntrada());
        pessoa.setUltimaDataSaida(dto.getUltimaDataSaida());
        return pessoa;
    }
}
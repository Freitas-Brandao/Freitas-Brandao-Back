package com.freitasbrandao.service;

import com.freitasbrandao.dto.PessoaRequestDTO;
import com.freitasbrandao.dto.PessoaResponseDTO;
import com.freitasbrandao.dto.PessoaResumoDTO;
import com.freitasbrandao.exception.ResourceNotFoundException;
import com.freitasbrandao.model.Genero;
import com.freitasbrandao.model.Pessoa;
import com.freitasbrandao.repository.BeneficioSocialRepository;
import com.freitasbrandao.repository.DesligamentoRepository;
import com.freitasbrandao.repository.DocumentoRepository;
import com.freitasbrandao.repository.EncaminhamentoRepository;
import com.freitasbrandao.repository.EvolucaoRepository;
import com.freitasbrandao.repository.PessoaRepository;
import com.freitasbrandao.repository.PessoaSpecification;
import com.freitasbrandao.repository.ReferenciaPessoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final DocumentoRepository documentoRepository;
    private final BeneficioSocialRepository beneficioSocialRepository;
    private final ReferenciaPessoalRepository referenciaPessoalRepository;
    private final DesligamentoRepository desligamentoRepository;
    private final EvolucaoRepository evolucaoRepository;
    private final EncaminhamentoRepository encaminhamentoRepository;

    @Transactional
    public PessoaResponseDTO cadastrar(PessoaRequestDTO dto) {
        String cpfLimpo = limparCpf(dto.getCpf());

        if (cpfLimpo != null && pessoaRepository.existsByCpf(cpfLimpo)) {
            throw new IllegalArgumentException("Já existe uma pessoa cadastrada com este CPF.");
        }

        Pessoa pessoa = toEntity(new Pessoa(), dto, cpfLimpo);
        pessoa.setProtocolo(gerarProtocolo());

        return PessoaResponseDTO.fromEntity(pessoaRepository.save(pessoa));
    }

    @Transactional(readOnly = true)
    public Page<PessoaResumoDTO> listar(String nome, String cpf,
                                        String protocolo, Genero genero,
                                        Pageable pageable) {
        String cpfLimpo = limparCpf(cpf);
        return pessoaRepository
                .findAll(PessoaSpecification.filtrar(nome, cpfLimpo, protocolo, genero), pageable)
                .map(PessoaResumoDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public PessoaResponseDTO buscarPorId(Long id) {
        return PessoaResponseDTO.fromEntity(buscarEntidade(id));
    }

    @Transactional
    public PessoaResponseDTO atualizar(Long id, PessoaRequestDTO dto) {
        Pessoa pessoa = buscarEntidade(id);
        String cpfLimpo = limparCpf(dto.getCpf());

        if (cpfLimpo != null) {
            pessoaRepository.findByCpf(cpfLimpo).ifPresent(existente -> {
                if (!existente.getId().equals(id)) {
                    throw new IllegalArgumentException("CPF já pertence a outro cadastro.");
                }
            });
        }

        return PessoaResponseDTO.fromEntity(
                pessoaRepository.save(toEntity(pessoa, dto, cpfLimpo)));
    }

    @Transactional
    public void deletar(Long id) {
        Pessoa pessoa = buscarEntidade(id);

        documentoRepository.deleteByPessoaId(id);
        beneficioSocialRepository.deleteByPessoaId(id);
        referenciaPessoalRepository.deleteByPessoaId(id);
        desligamentoRepository.deleteByPessoaId(id);
        evolucaoRepository.deleteByPessoaId(id);
        encaminhamentoRepository.deleteByPessoaId(id);

        pessoaRepository.delete(pessoa);
    }


    public Pessoa buscarEntidade(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Pessoa não encontrada com id: " + id));
    }

    private String limparCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) return null;
        return cpf.replaceAll("[.\\-]", "");
    }

    private String gerarProtocolo() {
        String data = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        long count = pessoaRepository.count() + 1;
        return String.format("CRAS-%s-%05d", data, count);
    }

    private Pessoa toEntity(Pessoa pessoa, PessoaRequestDTO dto, String cpfLimpo) {
        pessoa.setDataAcolhimento(
                dto.getDataAcolhimento() != null ? dto.getDataAcolhimento() : LocalDate.now()
        );
        pessoa.setHoraAcolhimento(dto.getHoraAcolhimento());
        pessoa.setDemandaEspontanea(Boolean.TRUE.equals(dto.getDemandaEspontanea()));
        pessoa.setNome(dto.getNome());
        pessoa.setNomeSocial(dto.getNomeSocial());
        pessoa.setDataNascimento(dto.getDataNascimento());
        pessoa.setNaturalidade(dto.getNaturalidade());
        pessoa.setGenero(dto.getGenero());
        pessoa.setTelefone(dto.getTelefone());
        pessoa.setCpf(cpfLimpo);
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

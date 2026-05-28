package com.freitasbrandao.service;

import com.freitasbrandao.dto.EvolucaoRequestDTO;
import com.freitasbrandao.dto.EvolucaoResponseDTO;
import com.freitasbrandao.exception.ResourceNotFoundException;
import com.freitasbrandao.model.Evolucao;
import com.freitasbrandao.model.Pessoa;
import com.freitasbrandao.repository.EvolucaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EvolucaoService {

    private final EvolucaoRepository evolucaoRepository;
    private final PessoaService pessoaService;

    @Transactional
    public EvolucaoResponseDTO criar(Long pessoaId, EvolucaoRequestDTO request) {
        Pessoa pessoa = pessoaService.buscarEntidade(pessoaId);

        Evolucao evolucao = Evolucao.builder()
                .pessoa(pessoa)
                .data(request.getData())
                .descricao(request.getDescricao())
                .responsavel(request.getResponsavel())
                .build();

        return toResponse(evolucaoRepository.save(evolucao));
    }

    @Transactional(readOnly = true)
    public List<EvolucaoResponseDTO> listar(Long pessoaId) {
        pessoaService.buscarEntidade(pessoaId);

        return evolucaoRepository.findByPessoaIdOrderByDataDesc(pessoaId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public EvolucaoResponseDTO buscarPorId(Long pessoaId, Long evolucaoId) {
        pessoaService.buscarEntidade(pessoaId);

        return toResponse(
                evolucaoRepository.findByIdAndPessoaId(evolucaoId, pessoaId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Evolução não encontrada com id: " + evolucaoId))
        );
    }

    @Transactional
    public EvolucaoResponseDTO atualizar(
            Long pessoaId, Long evolucaoId, EvolucaoRequestDTO request) {

        pessoaService.buscarEntidade(pessoaId);

        Evolucao evolucao = evolucaoRepository.findByIdAndPessoaId(evolucaoId, pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Evolução não encontrada com id: " + evolucaoId));

        evolucao.setData(request.getData());
        evolucao.setDescricao(request.getDescricao());
        evolucao.setResponsavel(request.getResponsavel());

        return toResponse(evolucaoRepository.save(evolucao));
    }

    @Transactional
    public void deletar(Long pessoaId, Long evolucaoId) {
        pessoaService.buscarEntidade(pessoaId);

        Evolucao evolucao = evolucaoRepository.findByIdAndPessoaId(evolucaoId, pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Evolução não encontrada com id: " + evolucaoId));

        evolucaoRepository.delete(evolucao);
    }

    private EvolucaoResponseDTO toResponse(Evolucao e) {
        return EvolucaoResponseDTO.builder()
                .id(e.getId())
                .pessoaId(e.getPessoa().getId())
                .data(e.getData())
                .descricao(e.getDescricao())
                .responsavel(e.getResponsavel())
                .build();
    }
}
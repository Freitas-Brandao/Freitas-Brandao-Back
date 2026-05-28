package com.freitasbrandao.service;

import com.freitasbrandao.dto.EncaminhamentoRequestDTO;
import com.freitasbrandao.dto.EncaminhamentoResponseDTO;
import com.freitasbrandao.exception.ResourceNotFoundException;
import com.freitasbrandao.model.Encaminhamento;
import com.freitasbrandao.model.Pessoa;
import com.freitasbrandao.repository.EncaminhamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EncaminhamentoService {

    private final EncaminhamentoRepository encaminhamentoRepository;
    private final PessoaService pessoaService;

    @Transactional
    public EncaminhamentoResponseDTO criar(Long pessoaId, EncaminhamentoRequestDTO request) {
        Pessoa pessoa = pessoaService.buscarEntidade(pessoaId);

        Encaminhamento encaminhamento = Encaminhamento.builder()
                .pessoa(pessoa)
                .data(request.getData())
                .destino(request.getDestino())
                .descricao(request.getDescricao())
                .build();

        return toResponse(encaminhamentoRepository.save(encaminhamento));
    }

    @Transactional(readOnly = true)
    public List<EncaminhamentoResponseDTO> listar(Long pessoaId) {
        pessoaService.buscarEntidade(pessoaId);

        return encaminhamentoRepository.findByPessoaIdOrderByDataDesc(pessoaId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public EncaminhamentoResponseDTO buscarPorId(Long pessoaId, Long encaminhamentoId) {
        pessoaService.buscarEntidade(pessoaId);

        return toResponse(
                encaminhamentoRepository.findByIdAndPessoaId(encaminhamentoId, pessoaId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Encaminhamento não encontrado com id: " + encaminhamentoId))
        );
    }

    @Transactional
    public EncaminhamentoResponseDTO atualizar(
            Long pessoaId, Long encaminhamentoId, EncaminhamentoRequestDTO request) {

        pessoaService.buscarEntidade(pessoaId);

        Encaminhamento encaminhamento = encaminhamentoRepository
                .findByIdAndPessoaId(encaminhamentoId, pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Encaminhamento não encontrado com id: " + encaminhamentoId));

        encaminhamento.setData(request.getData());
        encaminhamento.setDestino(request.getDestino());
        encaminhamento.setDescricao(request.getDescricao());

        return toResponse(encaminhamentoRepository.save(encaminhamento));
    }

    @Transactional
    public void deletar(Long pessoaId, Long encaminhamentoId) {
        pessoaService.buscarEntidade(pessoaId);

        Encaminhamento encaminhamento = encaminhamentoRepository
                .findByIdAndPessoaId(encaminhamentoId, pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Encaminhamento não encontrado com id: " + encaminhamentoId));

        encaminhamentoRepository.delete(encaminhamento);
    }

    private EncaminhamentoResponseDTO toResponse(Encaminhamento e) {
        return EncaminhamentoResponseDTO.builder()
                .id(e.getId())
                .pessoaId(e.getPessoa().getId())
                .data(e.getData())
                .destino(e.getDestino())
                .descricao(e.getDescricao())
                .build();
    }
}
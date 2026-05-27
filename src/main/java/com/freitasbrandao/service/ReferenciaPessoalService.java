package com.freitasbrandao.service;

import com.freitasbrandao.dto.ReferenciaPessoalRequestDTO;
import com.freitasbrandao.dto.ReferenciaPessoalResponseDTO;
import com.freitasbrandao.exception.ResourceNotFoundException;
import com.freitasbrandao.model.Pessoa;
import com.freitasbrandao.model.ReferenciaPessoal;
import com.freitasbrandao.repository.ReferenciaPessoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReferenciaPessoalService {

    private final ReferenciaPessoalRepository referenciaPessoalRepository;
    private final PessoaService pessoaService;

    @Transactional
    public ReferenciaPessoalResponseDTO criar(Long pessoaId, ReferenciaPessoalRequestDTO request) {
        Pessoa pessoa = pessoaService.buscarEntidade(pessoaId);

        ReferenciaPessoal referencia = ReferenciaPessoal.builder()
                .pessoa(pessoa)
                .nome(request.getNome())
                .telefone(request.getTelefone())
                .parentesco(request.getParentesco())
                .build();

        return toResponse(referenciaPessoalRepository.save(referencia));
    }

    @Transactional(readOnly = true)
    public List<ReferenciaPessoalResponseDTO> listar(Long pessoaId) {
        pessoaService.buscarEntidade(pessoaId);

        return referenciaPessoalRepository.findByPessoaId(pessoaId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ReferenciaPessoalResponseDTO buscarPorId(Long pessoaId, Long referenciaId) {
        pessoaService.buscarEntidade(pessoaId);

        ReferenciaPessoal referencia = referenciaPessoalRepository
                .findByIdAndPessoaId(referenciaId, pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Referência pessoal não encontrada com id: " + referenciaId));

        return toResponse(referencia);
    }

    @Transactional
    public ReferenciaPessoalResponseDTO atualizar(
            Long pessoaId, Long referenciaId, ReferenciaPessoalRequestDTO request) {

        pessoaService.buscarEntidade(pessoaId);

        ReferenciaPessoal referencia = referenciaPessoalRepository
                .findByIdAndPessoaId(referenciaId, pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Referência pessoal não encontrada com id: " + referenciaId));

        referencia.setNome(request.getNome());
        referencia.setTelefone(request.getTelefone());
        referencia.setParentesco(request.getParentesco());

        return toResponse(referenciaPessoalRepository.save(referencia));
    }

    @Transactional
    public void deletar(Long pessoaId, Long referenciaId) {
        pessoaService.buscarEntidade(pessoaId);

        ReferenciaPessoal referencia = referenciaPessoalRepository
                .findByIdAndPessoaId(referenciaId, pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Referência pessoal não encontrada com id: " + referenciaId));

        referenciaPessoalRepository.delete(referencia);
    }

    private ReferenciaPessoalResponseDTO toResponse(ReferenciaPessoal referencia) {
        return ReferenciaPessoalResponseDTO.builder()
                .id(referencia.getId())
                .pessoaId(referencia.getPessoa().getId())
                .nome(referencia.getNome())
                .telefone(referencia.getTelefone())
                .parentesco(referencia.getParentesco())
                .build();
    }
}
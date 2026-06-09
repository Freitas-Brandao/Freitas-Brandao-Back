package com.freitasbrandao.service;

import com.freitasbrandao.dto.DesligamentoRequestDTO;
import com.freitasbrandao.dto.DesligamentoResponseDTO;
import com.freitasbrandao.exception.ResourceNotFoundException;
import com.freitasbrandao.model.Desligamento;
import com.freitasbrandao.model.Pessoa;
import com.freitasbrandao.repository.DesligamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesligamentoService {

    private final DesligamentoRepository desligamentoRepository;
    private final PessoaService pessoaService;

    @Transactional
    public DesligamentoResponseDTO criar(Long pessoaId, DesligamentoRequestDTO request) {
        Pessoa pessoa = pessoaService.buscarEntidade(pessoaId);

        Desligamento desligamento = Desligamento.builder()
                .pessoa(pessoa)
                .data(request.getData())
                .motivo(request.getMotivo())
                .devolveuRoupas(valorOuFalse(request.getDevolveuRoupas()))
                .levouDocumentos(valorOuFalse(request.getLevouDocumentos()))
                .temLesoes(valorOuFalse(request.getTemLesoes()))
                .tecnicoResponsavel(request.getTecnicoResponsavel())
                .observacoes(request.getObservacoes())
                .build();

        pessoa.setUltimaDataSaida(request.getData());

        return toResponse(desligamentoRepository.save(desligamento));
    }

    @Transactional(readOnly = true)
    public List<DesligamentoResponseDTO> listar(Long pessoaId) {
        pessoaService.buscarEntidade(pessoaId);

        return desligamentoRepository.findByPessoaIdOrderByDataDesc(pessoaId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public DesligamentoResponseDTO buscarPorId(Long pessoaId, Long desligamentoId) {
        pessoaService.buscarEntidade(pessoaId);

        return toResponse(
                desligamentoRepository.findByIdAndPessoaId(desligamentoId, pessoaId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Desligamento não encontrado com id: " + desligamentoId))
        );
    }

    @Transactional
    public DesligamentoResponseDTO atualizar(
            Long pessoaId, Long desligamentoId, DesligamentoRequestDTO request) {

        pessoaService.buscarEntidade(pessoaId);

        Desligamento desligamento = desligamentoRepository
                .findByIdAndPessoaId(desligamentoId, pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Desligamento não encontrado com id: " + desligamentoId));

        desligamento.setData(request.getData());
        desligamento.setMotivo(request.getMotivo());
        desligamento.setDevolveuRoupas(valorOuFalse(request.getDevolveuRoupas()));
        desligamento.setLevouDocumentos(valorOuFalse(request.getLevouDocumentos()));
        desligamento.setTemLesoes(valorOuFalse(request.getTemLesoes()));
        desligamento.setTecnicoResponsavel(request.getTecnicoResponsavel());
        desligamento.setObservacoes(request.getObservacoes());

        return toResponse(desligamentoRepository.save(desligamento));
    }

    @Transactional
    public void deletar(Long pessoaId, Long desligamentoId) {
        pessoaService.buscarEntidade(pessoaId);

        Desligamento desligamento = desligamentoRepository
                .findByIdAndPessoaId(desligamentoId, pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Desligamento não encontrado com id: " + desligamentoId));

        desligamentoRepository.delete(desligamento);
    }

    private DesligamentoResponseDTO toResponse(Desligamento d) {
        return DesligamentoResponseDTO.builder()
                .id(d.getId())
                .pessoaId(d.getPessoa().getId())
                .data(d.getData())
                .motivo(d.getMotivo())
                .devolveuRoupas(d.getDevolveuRoupas())
                .levouDocumentos(d.getLevouDocumentos())
                .temLesoes(d.getTemLesoes())
                .tecnicoResponsavel(d.getTecnicoResponsavel())
                .observacoes(d.getObservacoes())
                .build();
    }

    private Boolean valorOuFalse(Boolean valor) {
        return valor != null ? valor : false;
    }
}

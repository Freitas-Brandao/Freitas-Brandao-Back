package com.freitasbrandao.service;

import com.freitasbrandao.dto.BeneficioSocialRequestDTO;
import com.freitasbrandao.dto.BeneficioSocialResponseDTO;
import com.freitasbrandao.exception.ResourceNotFoundException;
import com.freitasbrandao.model.BeneficioSocial;
import com.freitasbrandao.model.Pessoa;
import com.freitasbrandao.repository.BeneficioSocialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BeneficioSocialService {

    private final BeneficioSocialRepository beneficioSocialRepository;
    private final PessoaService pessoaService;

    @Transactional
    public BeneficioSocialResponseDTO criar(Long pessoaId, BeneficioSocialRequestDTO request) {
        Pessoa pessoa = pessoaService.buscarEntidade(pessoaId);

        if (beneficioSocialRepository.existsByPessoaId(pessoaId)) {
            throw new IllegalArgumentException("Já existe cadastro de benefícios sociais para esta pessoa.");
        }

        BeneficioSocial beneficio = BeneficioSocial.builder()
                .pessoa(pessoa)
                .bolsaFamilia(valorOuFalse(request.getBolsaFamilia()))
                .bpc(valorOuFalse(request.getBpc()))
                .auxilioBrasil(valorOuFalse(request.getAuxilioBrasil()))
                .seguroDesemprego(valorOuFalse(request.getSeguroDesemprego()))
                .outrosBeneficios(request.getOutrosBeneficios())
                .build();

        return toResponse(beneficioSocialRepository.save(beneficio));
    }

    @Transactional(readOnly = true)
    public BeneficioSocialResponseDTO buscarPorPessoaId(Long pessoaId) {
        pessoaService.buscarEntidade(pessoaId);

        BeneficioSocial beneficio = beneficioSocialRepository.findByPessoaId(pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Benefícios sociais não encontrados para a pessoa de id: " + pessoaId));

        return toResponse(beneficio);
    }

    @Transactional
    public BeneficioSocialResponseDTO atualizar(Long pessoaId, BeneficioSocialRequestDTO request) {
        pessoaService.buscarEntidade(pessoaId);

        BeneficioSocial beneficio = beneficioSocialRepository.findByPessoaId(pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Benefícios sociais não encontrados para a pessoa de id: " + pessoaId));

        beneficio.setBolsaFamilia(valorOuFalse(request.getBolsaFamilia()));
        beneficio.setBpc(valorOuFalse(request.getBpc()));
        beneficio.setAuxilioBrasil(valorOuFalse(request.getAuxilioBrasil()));
        beneficio.setSeguroDesemprego(valorOuFalse(request.getSeguroDesemprego()));
        beneficio.setOutrosBeneficios(request.getOutrosBeneficios());

        return toResponse(beneficioSocialRepository.save(beneficio));
    }

    @Transactional
    public void deletar(Long pessoaId) {
        pessoaService.buscarEntidade(pessoaId);

        if (!beneficioSocialRepository.existsByPessoaId(pessoaId)) {
            throw new ResourceNotFoundException(
                    "Benefícios sociais não encontrados para a pessoa de id: " + pessoaId);
        }

        beneficioSocialRepository.deleteByPessoaId(pessoaId);
    }

    private BeneficioSocialResponseDTO toResponse(BeneficioSocial beneficio) {
        return BeneficioSocialResponseDTO.builder()
                .id(beneficio.getId())
                .pessoaId(beneficio.getPessoa().getId())
                .bolsaFamilia(beneficio.getBolsaFamilia())
                .bpc(beneficio.getBpc())
                .auxilioBrasil(beneficio.getAuxilioBrasil())
                .seguroDesemprego(beneficio.getSeguroDesemprego())
                .outrosBeneficios(beneficio.getOutrosBeneficios())
                .build();
    }

    private Boolean valorOuFalse(Boolean valor) {
        return valor != null ? valor : false;
    }
}
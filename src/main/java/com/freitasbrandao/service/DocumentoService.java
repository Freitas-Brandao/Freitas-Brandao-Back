package com.freitasbrandao.service;

import com.freitasbrandao.dto.DocumentoResumoDTO;
import com.freitasbrandao.exception.ResourceNotFoundException;
import com.freitasbrandao.model.Documento;
import com.freitasbrandao.model.Pessoa;
import com.freitasbrandao.model.TipoDocumento;
import com.freitasbrandao.repository.DocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoService {

    private final DocumentoRepository documentoRepository;
    private final PessoaService pessoaService;

    private static final long MAX_FOTO_BYTES = 5 * 1024 * 1024;   // 5 MB
    private static final long MAX_PDF_BYTES  = 20 * 1024 * 1024;  // 20 MB

    public DocumentoResumoDTO upload(Long pessoaId, MultipartFile arquivo,
                                     TipoDocumento tipo) throws IOException {
        Pessoa pessoa = pessoaService.buscarEntidade(pessoaId);

        validarArquivo(arquivo, tipo);

        Documento doc = Documento.builder()
                .pessoa(pessoa)
                .tipo(tipo)
                .nomeOriginal(arquivo.getOriginalFilename())
                .contentType(arquivo.getContentType())
                .conteudo(arquivo.getBytes())
                .build();

        Documento salvo = documentoRepository.save(doc);

        return new DocumentoResumoDTO(
                salvo.getId(),
                salvo.getTipo(),
                salvo.getNomeOriginal(),
                salvo.getContentType(),
                salvo.getDataUpload()
        );
    }

    public List<DocumentoResumoDTO> listarMetadados(Long pessoaId) {
        pessoaService.buscarEntidade(pessoaId); // valida existência
        return documentoRepository.findMetadadosByPessoaId(pessoaId);
    }

    public Documento buscarParaDownload(Long pessoaId, Long documentoId) {
        pessoaService.buscarEntidade(pessoaId);
        return documentoRepository.findByIdAndPessoaId(documentoId, pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Documento não encontrado com id: " + documentoId));
    }

    public Documento buscarFotoPrincipal(Long pessoaId) {
        pessoaService.buscarEntidade(pessoaId);
        return documentoRepository
                .findFirstByPessoaIdAndTipo(pessoaId, TipoDocumento.FOTO)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Foto não encontrada para esta pessoa."));
    }

    public void deletar(Long pessoaId, Long documentoId) {
        buscarParaDownload(pessoaId, documentoId); // valida existência
        documentoRepository.deleteByIdAndPessoaId(documentoId, pessoaId);
    }

    // ---- helpers ----

    private void validarArquivo(MultipartFile arquivo, TipoDocumento tipo) {
        if (arquivo.isEmpty()) {
            throw new IllegalArgumentException("O arquivo não pode estar vazio.");
        }

        String contentType = arquivo.getContentType();

        if (tipo == TipoDocumento.FOTO) {
            if (contentType == null ||
                    (!contentType.equals("image/jpeg") && !contentType.equals("image/png"))) {
                throw new IllegalArgumentException("Foto deve ser JPEG ou PNG.");
            }
            if (arquivo.getSize() > MAX_FOTO_BYTES) {
                throw new IllegalArgumentException("Foto não pode ultrapassar 5 MB.");
            }
        }

        if (tipo == TipoDocumento.PDF) {
            if (contentType == null || !contentType.equals("application/pdf")) {
                throw new IllegalArgumentException("Documento deve ser um PDF.");
            }
            if (arquivo.getSize() > MAX_PDF_BYTES) {
                throw new IllegalArgumentException("PDF não pode ultrapassar 20 MB.");
            }
        }
    }
}
package com.freitasbrandao.controller;

import com.freitasbrandao.dto.DocumentoResumoDTO;
import com.freitasbrandao.model.Documento;
import com.freitasbrandao.model.TipoDocumento;
import com.freitasbrandao.service.DocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas/{pessoaId}/documentos")
@RequiredArgsConstructor
public class DocumentoController {

    private final DocumentoService documentoService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DocumentoResumoDTO> upload(
            @PathVariable Long pessoaId,
            @RequestParam("arquivo") MultipartFile arquivo,
            @RequestParam("tipo") TipoDocumento tipo) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(documentoService.upload(pessoaId, arquivo, tipo));
    }

    @GetMapping
    public ResponseEntity<List<DocumentoResumoDTO>> listar(
            @PathVariable Long pessoaId) {
        return ResponseEntity.ok(documentoService.listarMetadados(pessoaId));
    }

    @GetMapping("/{documentoId}/download")
    public ResponseEntity<byte[]> download(
            @PathVariable Long pessoaId,
            @PathVariable Long documentoId) {
        Documento doc = documentoService.buscarParaDownload(pessoaId, documentoId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + doc.getNomeOriginal() + "\"")
                .body(doc.getConteudo());
    }

    @GetMapping("/foto")
    public ResponseEntity<byte[]> foto(@PathVariable Long pessoaId) {
        Documento foto = documentoService.buscarFotoPrincipal(pessoaId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(foto.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + foto.getNomeOriginal() + "\"")
                .body(foto.getConteudo());
    }

    @DeleteMapping("/{documentoId}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long pessoaId,
            @PathVariable Long documentoId) {
        documentoService.deletar(pessoaId, documentoId);
        return ResponseEntity.noContent().build();
    }
}
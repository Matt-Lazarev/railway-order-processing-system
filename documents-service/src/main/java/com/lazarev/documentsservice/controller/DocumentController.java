package com.lazarev.documentsservice.controller;

import com.lazarev.documentsservice.service.DocumentService;
import com.lazarev.model.ClientOrderCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/documents")
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("/generate")
    public ResponseEntity<?> downloadDocument(@RequestBody ClientOrderCardDto clientOrderCard){
        byte[] data = documentService.generateDocument(clientOrderCard);
        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=document.docx")
                .contentType(MediaType.parseMediaType("application/docx"))
                .body(data);
    }
}

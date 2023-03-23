package com.lazarev.personalaccountservice.service;

import com.lazarev.model.documents.DocumentDto;
import com.lazarev.personalaccountservice.entity.Document;
import com.lazarev.personalaccountservice.exception.NoSuchDocumentException;
import com.lazarev.personalaccountservice.mapper.ClientMapper;
import com.lazarev.personalaccountservice.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final ClientMapper clientMapper;

    @Transactional(readOnly = true)
    public List<DocumentDto> getAllDocumentsByClientId(Integer clientId){
        List<Document> documents = documentRepository.findAllDocumentsByClientId(clientId);
        return clientMapper.toDocumentDtoList(documents);
    }

    @Transactional(readOnly = true)
    public DocumentDto getDocumentByDocumentId(Integer documentId) {
        Document document = documentRepository.findDocumentById(documentId)
                .orElseThrow(()->new NoSuchDocumentException("Document with id='%d' not found".formatted(documentId)));
        return clientMapper.toDocumentDto(document);
    }
}

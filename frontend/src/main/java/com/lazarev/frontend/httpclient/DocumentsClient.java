package com.lazarev.frontend.httpclient;

import com.lazarev.model.ClientOrderCardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "documents-client",
             url = "http://localhost:8083/api/documents")
public interface DocumentsClient {
    @PostMapping("/generate")
    ResponseEntity<byte[]> downloadDocument(@RequestBody ClientOrderCardDto clientOrderCard);
}

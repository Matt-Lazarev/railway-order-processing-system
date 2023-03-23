package com.lazarev.model.documents;

import com.lazarev.model.ClientOrderCardDto;

public record DocumentDto (Integer id,
                           Integer clientId,
                           Integer clientOrderId,
                           String type,
                           byte[] data,
                           ClientOrderCardDto clientOrder) { }

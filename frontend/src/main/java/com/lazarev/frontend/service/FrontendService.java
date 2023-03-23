package com.lazarev.frontend.service;


import com.lazarev.frontend.httpclient.AnalyticsClient;
import com.lazarev.frontend.httpclient.DocumentsClient;
import com.lazarev.frontend.httpclient.PersonalAccountClient;
import com.lazarev.frontend.mapper.DtoMapper;
import com.lazarev.model.*;
import com.lazarev.model.analytics.FlightParametersCalcRequest;
import com.lazarev.model.analytics.FlightParametersCalcResponse;
import com.lazarev.model.documents.DocumentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FrontendService {
    private final PersonalAccountClient personalAccountClient;
    private final AnalyticsClient analyticsClient;
    private final DocumentsClient documentsClient;
    private final DtoMapper dtoMapper;

    public ClientDto getClientByUsername(String username) {
        return personalAccountClient.getClientByUsername(username);
    }

    public ClientDto getClientById(Integer clientId) {
        return personalAccountClient.getClientById(clientId);
    }

    public List<ClientOrderDto> getClientOrdersByClientId(Integer clientId) {
        return personalAccountClient.getClientOrdersByClientId(clientId);
    }

    public List<Integer> getClientOrderIdsByClientId(Integer clientId) {
        return personalAccountClient.getClientOrderIdsByClientId(clientId);
    }

    public ClientOrderCardDto getClientOrderCardByClientIdAndOrderId(Integer clientId, Integer orderId) {
        return personalAccountClient.getClientOrderByClientIdAndOrderId(clientId, orderId);
    }

    public void updateClientById(Integer clientId, ClientDto client) {
        personalAccountClient.updateClientById(clientId, client);
    }

    public void saveNewClientOrder(Integer clientId, ClientOrderCardDto clientOrder) {
        Integer clientOrderId = personalAccountClient.saveNewClientOrder(clientId, clientOrder);
        clientOrder.setClientOrderId(clientOrderId);

        FlightParametersCalcRequest request = dtoMapper.toFlightParametersCalcRequest(clientOrder);
        FlightParametersCalcResponse response = analyticsClient.calculateFlightParameters(request);

        clientOrder.setOrderEnd(response.orderEnd());
        clientOrder.setWagonAmount(response.wagonAmount());
        clientOrder.setWagonVolume(response.wagonVolume());
        clientOrder.setRate(response.rate());

        personalAccountClient.updateClientOrderById(clientId, clientOrderId, response);
    }

    public ManagerDto getManagerByClientId(Integer clientId){
        return personalAccountClient.getManagerByClientId(clientId);
    }

    public List<ManagerCommunicationDto> getManagerCommunicationsByClientId(Integer clientId){
        return personalAccountClient.getAllManagerCommunicationsByClientId(clientId);
    }

    public void saveNewManagerCommunication(Integer clientId, ManagerCommunicationDto managerCommunication) {
        personalAccountClient.saveNewManagerCommunication(clientId, managerCommunication);
    }

    public List<DocumentDto> getAllClientDocuments(Integer clientId){
        return personalAccountClient.getAllClientDocuments(clientId);
    }

    public ResponseEntity<byte[]> downloadDocument(Integer clientId, Integer documentId) {
        DocumentDto document = personalAccountClient.getDocumentByDocumentId(clientId, documentId);
        return documentsClient.downloadDocument(document.clientOrder());
    }
}

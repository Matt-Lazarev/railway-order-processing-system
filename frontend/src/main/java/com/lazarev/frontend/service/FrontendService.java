package com.lazarev.frontend.service;


import com.lazarev.frontend.httpclient.PersonalAccountClient;
import com.lazarev.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FrontendService {
    private static final List<ManagerCommunicationDto> MOCK_COMMUNICATIONS = new ArrayList<>(List.of(
            new ManagerCommunicationDto(1, 1, 1, "Уточнение по заказу", "Необходимо уточнить параметры перевозки", "Исполнено",
                    LocalDateTime.of(2023, 3, 10, 10, 30),
                    LocalDateTime.of(2023, 3, 9, 16, 30))
    ));

    private final PersonalAccountClient personalAccountClient;

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
        personalAccountClient.saveNewClientOrder(clientId, clientOrder);
    }

    public ManagerDto getManagerByClientId(Integer clientId){
        return personalAccountClient.getManagerByClientId(clientId);
    }

    public List<ManagerCommunicationDto> getManagerCommunicationsByClientId(Integer clientId){
        return personalAccountClient.getAllManagerCommunicationsByClientId(clientId);
    }

    public void saveNewManagerCommunication(Integer clientId, ManagerCommunicationDto managerCommunication) {
        personalAccountClient.saveNewManagerCommunication(clientId, managerCommunication);
        managerCommunication.setCreateAt(LocalDateTime.now());
        managerCommunication.setStatus("Новый");
        MOCK_COMMUNICATIONS.add(managerCommunication);
    }
}

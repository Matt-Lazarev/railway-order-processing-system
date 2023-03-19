package com.lazarev.frontend.service;

import com.lazarev.frontend.model.ClientDto;
import com.lazarev.frontend.model.ClientOrderCardDto;
import com.lazarev.frontend.model.ClientOrderDto;
import com.lazarev.frontend.model.ManagerCommunicationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FrontendService {
    private static ClientDto MOCK_CLIENT = new ClientDto(1, "Mike", "Scott", "RJD", "mike@gmail.com", 1, 1);
    private static final List<ClientOrderDto> MOCK_CLIENT_ORDERS = new ArrayList<>(List.of(
          new ClientOrderDto(1, "Новый", "Прокшино", "Ольховая", 1),
          new ClientOrderDto(2, "В работе", "Юго-западная", "Калужская", 1)
    ));
    private static final ClientOrderCardDto MOCK_ORDER_CARD = new ClientOrderCardDto(
            1,
            LocalDate.now().minusDays(5),
            LocalDate.now(),
            "Новый", "Пшеница", "Прокшино", "Ольховая", 1500, 1);

    private static final List<ManagerCommunicationDto> MOCK_COMMUNICATIONS = new ArrayList<>(List.of(
            new ManagerCommunicationDto(1, 1, "Уточнение по заказу", "Необходимо уточнить параметры перевозки", "Исполнено",
                    LocalDateTime.of(2023, 3, 10, 10, 30),
                    LocalDateTime.of(2023, 3, 9, 16, 30))
    ));

    public List<ClientOrderDto> getClientOrdersByClientId(Integer clientId) {
        return MOCK_CLIENT_ORDERS;
    }

    public ClientDto getClientByUsername(String login) {
        return MOCK_CLIENT;
    }

    public ClientOrderCardDto getClientOrderCardByClientIdAndOrderId(Integer clientId, Integer orderId) {
        return MOCK_ORDER_CARD;
    }

    public ClientDto getClientById(Integer clientId) {
        return MOCK_CLIENT;
    }

    public void updateClientById(Integer clientId, ClientDto client) {
        MOCK_CLIENT = client;
        MOCK_CLIENT.setId(clientId);
    }

    public void saveNewClientOrder(Integer clientId, ClientOrderCardDto clientOrder) {
        MOCK_CLIENT_ORDERS.add(
                new ClientOrderDto(
                        clientOrder.getClientOrderId(),
                        "На согласовании",
                        clientOrder.getSourceStation(),
                        clientOrder.getDestStation(),
                        clientId)
        );
    }

    public List<ManagerCommunicationDto> getManagerCommunicationsByClientId(Integer clientId){
        return MOCK_COMMUNICATIONS;
    }
}

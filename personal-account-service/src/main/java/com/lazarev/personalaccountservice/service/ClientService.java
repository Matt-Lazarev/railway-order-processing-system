package com.lazarev.personalaccountservice.service;


import com.lazarev.model.*;
import com.lazarev.model.analytics.FlightParametersCalcResponse;
import com.lazarev.personalaccountservice.entity.Client;
import com.lazarev.personalaccountservice.entity.ClientOrder;
import com.lazarev.personalaccountservice.entity.Manager;
import com.lazarev.personalaccountservice.entity.ManagerCommunication;
import com.lazarev.personalaccountservice.exception.NoSuchClientException;
import com.lazarev.personalaccountservice.mapper.ClientMapper;
import com.lazarev.personalaccountservice.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ClientOrderService clientOrderService;
    private final ManagerCommunicationService managerCommunicationService;

    @Transactional(readOnly = true)
    public List<ClientDto> getAllClients(){
        List<Client> clients = clientRepository.findAll();
        return clientMapper.toClientDtoList(clients);
    }

    @Transactional(readOnly = true)
    public ClientDto getClientByUsername(String username){
        Client client = clientRepository.findClientByUsername(username)
                .orElseThrow(()->new NoSuchClientException("Client with username='%s' not found".formatted(username)));
        return clientMapper.toClientDto(client);
    }

    @Transactional(readOnly = true)
    public ClientDto getClientById(Integer clientId){
        Client client = clientRepository.findById(clientId)
                .orElseThrow(()->new NoSuchClientException("Client with id='%d' not found".formatted(clientId)));
        return clientMapper.toClientDto(client);
    }

    @Transactional
    public void updateClient(Integer clientId, ClientDto clientDto){
        Client client = clientRepository.findById(clientId)
                .orElseThrow(()->new NoSuchClientException("Client with id='%d' not found".formatted(clientId)));

        clientMapper.updateClient(client, clientDto);
    }

    @Transactional(readOnly = true)
    public List<ClientOrderDto> getAllClientOrdersByClientId(Integer clientId){
        return clientRepository.findAllClientOrdersByClientId(clientId);
    }

    @Transactional(readOnly = true)
    public List<Integer> getClientOrderIdsByClientId(Integer clientId) {
        return clientRepository.findClientOrderIdsByClientId(clientId);
    }

    @Transactional(readOnly = true)
    public ClientOrderCardDto getClientOrderByClientIdAndOrderId( Integer clientId, Integer orderId){
        ClientOrder clientOrder = clientOrderService.getClientOrderByClientIdAndClientOrderId(clientId, orderId);
        return clientMapper.toClientOrderCardDto(clientOrder);
    }

    @Transactional
    public Integer saveClientOrder(Integer clientId, ClientOrderCardDto clientOrderCardDto){
        Client client = clientRepository.findById(clientId)
                .orElseThrow(()->new NoSuchClientException("Client with id='%d' not found".formatted(clientId)));
        ClientOrder clientOrder = clientMapper.toClientOrder(clientOrderCardDto);
        Integer clientOrderId = clientOrderService.saveClientOrder(client, clientOrder, clientOrderCardDto);
        return clientOrderId;
    }

    @Transactional
    public void updateClientOrderById(Integer clientOrderId, FlightParametersCalcResponse response) {
        clientOrderService.updateClientOrderById(clientOrderId, response);
    }

    @Transactional(readOnly = true)
    public ManagerDto getManagerByClientId(Integer clientId){
        Manager manager = clientRepository.findManagerByClientId(clientId)
                .orElseThrow(()->new NoSuchClientException("Client with id='%d' not found".formatted(clientId)));
        return clientMapper.toManagerDto(manager);
    }

    @Transactional(readOnly = true)
    public List<ManagerCommunicationDto> getAllManagerCommunicationsByClientId(Integer clientId) {
        List<ManagerCommunication> managerCommunications = managerCommunicationService.getAllManagerCommunicationsByClientId(clientId);
        return clientMapper.toManagerCommunicationDtoList(managerCommunications);
    }

    @Transactional
    public void saveManagerCommunication(Integer clientId, ManagerCommunicationDto managerCommunicationDto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(()->new NoSuchClientException("Client with id='%d' not found".formatted(clientId)));
        ClientOrder clientOrder = clientOrderService.getClientOrderByClientIdAndClientOrderId(clientId, managerCommunicationDto.getClientOrderId());
        ManagerCommunication managerCommunication = clientMapper.toManagerCommunication(managerCommunicationDto);

        managerCommunicationService.saveManagerCommunication(managerCommunication, client, clientOrder);
    }
}

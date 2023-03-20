package com.lazarev.personalaccountservice.service;

import com.lazarev.personalaccountservice.entity.Client;
import com.lazarev.personalaccountservice.entity.ClientOrder;
import com.lazarev.personalaccountservice.entity.ManagerCommunication;
import com.lazarev.personalaccountservice.repository.ManagerCommunicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerCommunicationService {
    private final ManagerCommunicationRepository managerCommunicationRepository;

    @Transactional(readOnly = true)
    public List<ManagerCommunication> getAllManagerCommunicationsByClientId(Integer clientId) {
        return managerCommunicationRepository.findAllManagerCommunicationsByClientId(clientId);
    }

    @Transactional
    public void saveManagerCommunication(ManagerCommunication managerCommunication, Client client, ClientOrder clientOrder){
        managerCommunication.setStatus("Исполняется");
        managerCommunication.setCreateAt(LocalDateTime.now());
        managerCommunication.setClientOrder(clientOrder);
        managerCommunication.setClient(client);
        managerCommunicationRepository.save(managerCommunication);
    }
}

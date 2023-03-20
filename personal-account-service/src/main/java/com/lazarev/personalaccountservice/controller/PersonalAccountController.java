package com.lazarev.personalaccountservice.controller;

import com.lazarev.model.*;
import com.lazarev.personalaccountservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class PersonalAccountController {
    private final ClientService clientService;

    @GetMapping
    public List<ClientDto> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping(params = "username")
    public ClientDto getClientByUsername(@RequestParam String username){
        return clientService.getClientByUsername(username);
    }

    @GetMapping("/{id}")
    public ClientDto getClientById(@PathVariable Integer id){
        return clientService.getClientById(id);
    }

    @PutMapping("/{id}")
    public void updateClientById(@PathVariable Integer id, @RequestBody ClientDto client){
        clientService.updateClient(id, client);
    }

    @GetMapping("/{id}/orders")
    public List<ClientOrderDto> getClientOrdersByClientId(@PathVariable Integer id){
        return clientService.getAllClientOrdersByClientId(id);
    }

    @GetMapping("/{clientId}/orders/ids")
    public List<Integer> getClientOrderIdsByClientId(@PathVariable Integer clientId){
        return clientService.getClientOrderIdsByClientId(clientId);
    }

    @GetMapping("/{clientId}/orders/{orderId}")
    ClientOrderCardDto getClientOrderByClientIdAndOrderId(@PathVariable Integer clientId,
                                                          @PathVariable Integer orderId){
        return clientService.getClientOrderByClientIdAndOrderId(clientId, orderId);
    }

    @PostMapping("/{clientId}/orders")
    public void saveNewClientOrder(@PathVariable Integer clientId,
                            @RequestBody ClientOrderCardDto clientOrder){
        clientService.saveClientOrder(clientId, clientOrder);
    }

    @GetMapping("/{clientId}/manager")
    public ManagerDto getManagerByClientId(@PathVariable Integer clientId){
        return clientService.getManagerByClientId(clientId);
    }

    @GetMapping("/{clientId}/manager-communications")
    public List<ManagerCommunicationDto> getAllManagerCommunicationsByClientId(@PathVariable Integer clientId){
        return clientService.getAllManagerCommunicationsByClientId(clientId);
    }

    @PostMapping("/{clientId}/manager-communications")
    public void saveNewManagerCommunication(@PathVariable Integer clientId,
                                            @RequestBody ManagerCommunicationDto managerCommunication){
        clientService.saveManagerCommunication(clientId, managerCommunication);
    }


}

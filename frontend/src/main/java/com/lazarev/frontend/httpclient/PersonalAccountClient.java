package com.lazarev.frontend.httpclient;


import com.lazarev.model.*;
import com.lazarev.model.analytics.FlightParametersCalcResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "personal-account-client",
             url = "http://localhost:8080/api/clients")
public interface PersonalAccountClient {

    @GetMapping
    List<ClientDto> getAllClients();

    @GetMapping(params = "username")
    ClientDto getClientByUsername(@RequestParam(name = "username") String username);

    @GetMapping("/{id}")
    ClientDto getClientById(@PathVariable Integer id);

    @PutMapping("/{id}")
    void updateClientById(@PathVariable Integer id, @RequestBody ClientDto client);

    @GetMapping("/{id}/orders")
    List<ClientOrderDto> getClientOrdersByClientId(@PathVariable Integer id);

    @GetMapping("/{clientId}/orders/ids")
    List<Integer> getClientOrderIdsByClientId(@PathVariable Integer clientId);

    @GetMapping("/{clientId}/orders/{orderId}")
    ClientOrderCardDto getClientOrderByClientIdAndOrderId(@PathVariable Integer clientId,
                                                          @PathVariable Integer orderId);

    @PostMapping("/{clientId}/orders")
    Integer saveNewClientOrder(@PathVariable Integer clientId,
                            @RequestBody ClientOrderCardDto clientOrder);

    @PutMapping("/{clientId}/orders/{clientOrderId}")
    void updateClientOrderById(@PathVariable Integer clientId,
                               @PathVariable Integer clientOrderId,
                               @RequestBody FlightParametersCalcResponse response);
    @GetMapping("/{clientId}/manager")
    ManagerDto getManagerByClientId(@PathVariable Integer clientId);

    @GetMapping("/{clientId}/manager-communications")
    List<ManagerCommunicationDto> getAllManagerCommunicationsByClientId(@PathVariable Integer clientId);

    @PostMapping("/{clientId}/manager-communications")
    void saveNewManagerCommunication(@PathVariable Integer clientId,
                                     @RequestBody ManagerCommunicationDto managerCommunication);
}

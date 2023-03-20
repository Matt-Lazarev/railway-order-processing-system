package com.lazarev.frontend.controller;

import com.lazarev.frontend.service.FrontendService;
import com.lazarev.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/railway-system")
public class PersonAccountController {
    private static final UserDto MOCK_USER = new UserDto(1, "mike", "123", "ROLE_USER");
    private final FrontendService frontendService;

    @GetMapping("/home")
    public String getHomePage(Model model){
        ClientDto clientDto = frontendService.getClientByUsername(MOCK_USER.login());
        model.addAttribute("clientId", clientDto.getId());
        return "home";
    }

    @GetMapping("/clients/{clientId}/orders")
    public String getClientOrdersPage(@PathVariable Integer clientId, Model model){
        List<ClientOrderDto> clientOrders = frontendService.getClientOrdersByClientId(clientId);
        model.addAttribute("clientOrders", clientOrders);
        return "client_orders";
    }

    @GetMapping("/clients/{clientId}/orders/{orderId}")
    public String getClientOrderCardPage(@PathVariable Integer clientId,
                                         @PathVariable Integer orderId,
                                         Model model){
        ClientOrderCardDto clientOrderCard = frontendService.getClientOrderCardByClientIdAndOrderId(clientId, orderId);
        model.addAttribute("card", clientOrderCard);
        return "client_order_card";
    }

    @GetMapping("/clients/{clientId}")
    public String getClientPage(@PathVariable Integer clientId, Model model){
        ClientDto client = frontendService.getClientById(clientId);
        model.addAttribute("client", client);
        return "client_card";
    }

    @PutMapping("/clients/{clientId}")
    public String updateClient(@PathVariable Integer clientId,
                               @ModelAttribute ClientDto client){
        frontendService.updateClientById(clientId, client);
        return "redirect:/railway-system/clients/%d".formatted(clientId);
    }

    @GetMapping("/clients/{clientId}/new-order")
    public String getNewOrderPage(@PathVariable Integer clientId, Model model){
        model.addAttribute("clientOrder", new ClientOrderCardDto());
        return "new_client_order";
    }

    @PostMapping("/clients/{clientId}/orders")
    public String saveNewClientOrder(@PathVariable Integer clientId,
                                     @ModelAttribute ClientOrderCardDto clientOrder){
        frontendService.saveNewClientOrder(clientId, clientOrder);
        return "redirect:/railway-system/clients/%d/orders".formatted(clientId);
    }

    @GetMapping("/clients/{clientId}/manager-requests")
    public String getManagerRequestsPage(@PathVariable Integer clientId, Model model){
        ManagerDto manager = frontendService.getManagerByClientId(clientId);
        List<ManagerCommunicationDto> managerCommunications = frontendService.getManagerCommunicationsByClientId(clientId);
        model.addAttribute("manager", manager);
        model.addAttribute("managerCommunications", managerCommunications);
        return "manager_requests";
    }

    @GetMapping("clients/{clientId}/new-manager-request")
    public String getNewManagerRequestPage(@PathVariable Integer clientId, Model model){
        List<Integer> ids = frontendService.getClientOrderIdsByClientId(clientId);
        model.addAttribute("request", new ManagerCommunicationDto());
        model.addAttribute("clientOrderIds", ids);
        return "manager_request_card";
    }

    @PostMapping("clients/{clientId}/new-manager-request")
    public String getNewManagerRequestPage(@PathVariable Integer clientId,
                                           @ModelAttribute ManagerCommunicationDto managerCommunication){
        frontendService.saveNewManagerCommunication(clientId, managerCommunication);
        return "redirect:/railway-system/clients/%d/manager-requests".formatted(clientId);
    }

    @ModelAttribute
    public void addUser(Model model){
        model.addAttribute("user", MOCK_USER);
    }
}

package com.lazarev.personalaccountservice.mapper;

import com.lazarev.model.*;
import com.lazarev.model.analytics.FlightParametersCalcResponse;
import com.lazarev.personalaccountservice.entity.Client;
import com.lazarev.personalaccountservice.entity.ClientOrder;
import com.lazarev.personalaccountservice.entity.Manager;
import com.lazarev.personalaccountservice.entity.ManagerCommunication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDto toClientDto(Client client);

    Client toClient(ClientDto clientDto);

    List<ClientDto> toClientDtoList(List<Client> clients);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "clientOrders", ignore = true)
    void updateClient(@MappingTarget Client client, ClientDto clientDto);

    @Mapping(target = "sourceStation" , source = "clientOrder.sourceStation.name")
    @Mapping(target = "destStation" , source = "clientOrder.destStation.name")
    ClientOrderDto toClientOrderDto(ClientOrder clientOrder);

    @Mapping(target = "sourceStation", ignore = true)
    @Mapping(target = "destStation", ignore = true)
    @Mapping(target = "cargo", ignore = true)
    @Mapping(target = "beginDate", source = "orderBegin")
    @Mapping(target = "endDate", source = "orderEnd")
    @Mapping(target = "id", source = "clientOrderId")
    ClientOrder toClientOrder(ClientOrderCardDto clientOrderCardDto);

    List<ClientOrderDto> toClientOrderDtoList(List<ClientOrder> clientOrders);

    @Mapping(target = "sourceStation" , source = "clientOrder.sourceStation.name")
    @Mapping(target = "destStation" , source = "clientOrder.destStation.name")
    @Mapping(target = "cargo" , source = "clientOrder.cargo.name")
    @Mapping(target = "orderBegin", source = "beginDate")
    @Mapping(target = "orderEnd", source = "endDate")
    @Mapping(target = "clientOrderId", source = "id")
    ClientOrderCardDto toClientOrderCardDto(ClientOrder clientOrder);

    ManagerDto toManagerDto(Manager manager);

    @Mapping(target = "clientId", source = "managerCommunication.client.id")
    @Mapping(target = "clientOrderId", source = "managerCommunication.clientOrder.id")
    ManagerCommunicationDto toManagerCommunicationDto(ManagerCommunication managerCommunication);

    ManagerCommunication toManagerCommunication(ManagerCommunicationDto managerCommunicationDto);

    List<ManagerCommunicationDto> toManagerCommunicationDtoList(List<ManagerCommunication> managerCommunications);

    @Mapping(target = "endDate", source = "orderEnd")
    void updateClientOrder(@MappingTarget ClientOrder clientOrder, FlightParametersCalcResponse response);
}

package com.lazarev.personalaccountservice.service;

import com.lazarev.model.ClientOrderCardDto;
import com.lazarev.model.analytics.FlightParametersCalcResponse;
import com.lazarev.personalaccountservice.entity.Client;
import com.lazarev.personalaccountservice.entity.ClientOrder;
import com.lazarev.personalaccountservice.exception.NoSuchClientOrderException;
import com.lazarev.personalaccountservice.mapper.ClientMapper;
import com.lazarev.personalaccountservice.repository.ClientOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ClientOrderService {
    private final ClientOrderRepository clientOrderRepository;
    private final CargoService cargoService;
    private final StationService stationService;
    private final ClientMapper clientMapper;

    @Transactional(readOnly = true)
    public ClientOrder getClientOrderByClientIdAndClientOrderId(Integer clientId, Integer clientOrderId){
        return clientOrderRepository.findClientOrderByClientIdAndClientOrderId(clientId, clientOrderId)
                .orElseThrow(()->new NoSuchClientOrderException("ClientOrder with id='%d' not found".formatted(clientOrderId)));
    }

    @Transactional
    public Integer saveClientOrder(Client client, ClientOrder clientOrder, ClientOrderCardDto clientOrderCardDto) {
        client.addClientOrder(clientOrder);
        clientOrder.setSourceStation(stationService.getStationByName(clientOrderCardDto.getSourceStation()));
        clientOrder.setDestStation(stationService.getStationByName(clientOrderCardDto.getDestStation()));
        clientOrder.setCargo(cargoService.getCargoByName(clientOrderCardDto.getCargo()));
        clientOrder.setStatus("На согласовании");

        clientOrder = clientOrderRepository.save(clientOrder);
        return clientOrder.getId();
    }

    @Transactional
    public void updateClientOrderById(Integer clientOrderId, FlightParametersCalcResponse response) {
        ClientOrder clientOrder = clientOrderRepository.findById(clientOrderId)
                .orElseThrow(()->new NoSuchClientOrderException("ClientOrder with id='%d' not found".formatted(clientOrderId)));

        clientMapper.updateClientOrder(clientOrder, response);
        clientOrderRepository.save(clientOrder);
    }
}

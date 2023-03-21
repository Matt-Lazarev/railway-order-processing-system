package com.lazarev.personalaccountservice.mapper;

import com.lazarev.model.ClientDto;
import com.lazarev.model.ClientOrderCardDto;
import com.lazarev.model.ClientOrderDto;
import com.lazarev.model.ManagerCommunicationDto;
import com.lazarev.model.ManagerDto;
import com.lazarev.model.analytics.FlightParametersCalcResponse;
import com.lazarev.personalaccountservice.entity.Cargo;
import com.lazarev.personalaccountservice.entity.Client;
import com.lazarev.personalaccountservice.entity.ClientOrder;
import com.lazarev.personalaccountservice.entity.Manager;
import com.lazarev.personalaccountservice.entity.ManagerCommunication;
import com.lazarev.personalaccountservice.entity.Station;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-21T16:30:20+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.3.1 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientDto toClientDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDto clientDto = new ClientDto();

        clientDto.setId( client.getId() );
        clientDto.setFirstname( client.getFirstname() );
        clientDto.setLastname( client.getLastname() );
        clientDto.setUsername( client.getUsername() );
        clientDto.setOrganization( client.getOrganization() );
        clientDto.setEmail( client.getEmail() );

        return clientDto;
    }

    @Override
    public Client toClient(ClientDto clientDto) {
        if ( clientDto == null ) {
            return null;
        }

        Client client = new Client();

        client.setId( clientDto.getId() );
        client.setFirstname( clientDto.getFirstname() );
        client.setLastname( clientDto.getLastname() );
        client.setEmail( clientDto.getEmail() );
        client.setOrganization( clientDto.getOrganization() );
        client.setUsername( clientDto.getUsername() );

        return client;
    }

    @Override
    public List<ClientDto> toClientDtoList(List<Client> clients) {
        if ( clients == null ) {
            return null;
        }

        List<ClientDto> list = new ArrayList<ClientDto>( clients.size() );
        for ( Client client : clients ) {
            list.add( toClientDto( client ) );
        }

        return list;
    }

    @Override
    public void updateClient(Client client, ClientDto clientDto) {
        if ( clientDto == null ) {
            return;
        }

        client.setFirstname( clientDto.getFirstname() );
        client.setLastname( clientDto.getLastname() );
        client.setEmail( clientDto.getEmail() );
        client.setOrganization( clientDto.getOrganization() );
    }

    @Override
    public ClientOrderDto toClientOrderDto(ClientOrder clientOrder) {
        if ( clientOrder == null ) {
            return null;
        }

        String sourceStation = null;
        String destStation = null;
        Integer id = null;
        String status = null;

        sourceStation = clientOrderSourceStationName( clientOrder );
        destStation = clientOrderDestStationName( clientOrder );
        id = clientOrder.getId();
        status = clientOrder.getStatus();

        Integer clientId = null;

        ClientOrderDto clientOrderDto = new ClientOrderDto( id, status, sourceStation, destStation, clientId );

        return clientOrderDto;
    }

    @Override
    public ClientOrder toClientOrder(ClientOrderCardDto clientOrderCardDto) {
        if ( clientOrderCardDto == null ) {
            return null;
        }

        ClientOrder clientOrder = new ClientOrder();

        clientOrder.setBeginDate( clientOrderCardDto.getOrderBegin() );
        clientOrder.setEndDate( clientOrderCardDto.getOrderEnd() );
        clientOrder.setId( clientOrderCardDto.getClientOrderId() );
        clientOrder.setStatus( clientOrderCardDto.getStatus() );
        clientOrder.setWagonAmount( clientOrderCardDto.getWagonAmount() );
        clientOrder.setWagonVolume( clientOrderCardDto.getWagonVolume() );
        clientOrder.setTotalVolume( clientOrderCardDto.getTotalVolume() );
        clientOrder.setRate( clientOrderCardDto.getRate() );

        return clientOrder;
    }

    @Override
    public List<ClientOrderDto> toClientOrderDtoList(List<ClientOrder> clientOrders) {
        if ( clientOrders == null ) {
            return null;
        }

        List<ClientOrderDto> list = new ArrayList<ClientOrderDto>( clientOrders.size() );
        for ( ClientOrder clientOrder : clientOrders ) {
            list.add( toClientOrderDto( clientOrder ) );
        }

        return list;
    }

    @Override
    public ClientOrderCardDto toClientOrderCardDto(ClientOrder clientOrder) {
        if ( clientOrder == null ) {
            return null;
        }

        ClientOrderCardDto clientOrderCardDto = new ClientOrderCardDto();

        clientOrderCardDto.setSourceStation( clientOrderSourceStationName( clientOrder ) );
        clientOrderCardDto.setDestStation( clientOrderDestStationName( clientOrder ) );
        clientOrderCardDto.setCargo( clientOrderCargoName( clientOrder ) );
        clientOrderCardDto.setOrderBegin( clientOrder.getBeginDate() );
        clientOrderCardDto.setOrderEnd( clientOrder.getEndDate() );
        clientOrderCardDto.setClientOrderId( clientOrder.getId() );
        clientOrderCardDto.setStatus( clientOrder.getStatus() );
        clientOrderCardDto.setTotalVolume( clientOrder.getTotalVolume() );
        clientOrderCardDto.setWagonAmount( clientOrder.getWagonAmount() );
        clientOrderCardDto.setWagonVolume( clientOrder.getWagonVolume() );
        clientOrderCardDto.setRate( clientOrder.getRate() );

        return clientOrderCardDto;
    }

    @Override
    public ManagerDto toManagerDto(Manager manager) {
        if ( manager == null ) {
            return null;
        }

        String firstname = null;
        String lastname = null;
        String phone = null;
        String email = null;

        firstname = manager.getFirstname();
        lastname = manager.getLastname();
        phone = manager.getPhone();
        email = manager.getEmail();

        Integer managerId = null;
        Integer clientId = null;

        ManagerDto managerDto = new ManagerDto( managerId, clientId, firstname, lastname, phone, email );

        return managerDto;
    }

    @Override
    public ManagerCommunicationDto toManagerCommunicationDto(ManagerCommunication managerCommunication) {
        if ( managerCommunication == null ) {
            return null;
        }

        ManagerCommunicationDto managerCommunicationDto = new ManagerCommunicationDto();

        managerCommunicationDto.setClientId( managerCommunicationClientId( managerCommunication ) );
        managerCommunicationDto.setClientOrderId( managerCommunicationClientOrderId( managerCommunication ) );
        managerCommunicationDto.setId( managerCommunication.getId() );
        managerCommunicationDto.setReason( managerCommunication.getReason() );
        managerCommunicationDto.setMessage( managerCommunication.getMessage() );
        managerCommunicationDto.setStatus( managerCommunication.getStatus() );
        managerCommunicationDto.setDateTime( managerCommunication.getDateTime() );
        managerCommunicationDto.setCreateAt( managerCommunication.getCreateAt() );

        return managerCommunicationDto;
    }

    @Override
    public ManagerCommunication toManagerCommunication(ManagerCommunicationDto managerCommunicationDto) {
        if ( managerCommunicationDto == null ) {
            return null;
        }

        ManagerCommunication managerCommunication = new ManagerCommunication();

        managerCommunication.setId( managerCommunicationDto.getId() );
        managerCommunication.setReason( managerCommunicationDto.getReason() );
        managerCommunication.setMessage( managerCommunicationDto.getMessage() );
        managerCommunication.setStatus( managerCommunicationDto.getStatus() );
        managerCommunication.setDateTime( managerCommunicationDto.getDateTime() );
        managerCommunication.setCreateAt( managerCommunicationDto.getCreateAt() );

        return managerCommunication;
    }

    @Override
    public List<ManagerCommunicationDto> toManagerCommunicationDtoList(List<ManagerCommunication> managerCommunications) {
        if ( managerCommunications == null ) {
            return null;
        }

        List<ManagerCommunicationDto> list = new ArrayList<ManagerCommunicationDto>( managerCommunications.size() );
        for ( ManagerCommunication managerCommunication : managerCommunications ) {
            list.add( toManagerCommunicationDto( managerCommunication ) );
        }

        return list;
    }

    @Override
    public void updateClientOrder(ClientOrder clientOrder, FlightParametersCalcResponse response) {
        if ( response == null ) {
            return;
        }

        clientOrder.setEndDate( response.orderEnd() );
        clientOrder.setWagonAmount( response.wagonAmount() );
        clientOrder.setWagonVolume( response.wagonVolume() );
        clientOrder.setRate( response.rate() );
    }

    private String clientOrderSourceStationName(ClientOrder clientOrder) {
        if ( clientOrder == null ) {
            return null;
        }
        Station sourceStation = clientOrder.getSourceStation();
        if ( sourceStation == null ) {
            return null;
        }
        String name = sourceStation.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String clientOrderDestStationName(ClientOrder clientOrder) {
        if ( clientOrder == null ) {
            return null;
        }
        Station destStation = clientOrder.getDestStation();
        if ( destStation == null ) {
            return null;
        }
        String name = destStation.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String clientOrderCargoName(ClientOrder clientOrder) {
        if ( clientOrder == null ) {
            return null;
        }
        Cargo cargo = clientOrder.getCargo();
        if ( cargo == null ) {
            return null;
        }
        String name = cargo.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Integer managerCommunicationClientId(ManagerCommunication managerCommunication) {
        if ( managerCommunication == null ) {
            return null;
        }
        Client client = managerCommunication.getClient();
        if ( client == null ) {
            return null;
        }
        Integer id = client.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer managerCommunicationClientOrderId(ManagerCommunication managerCommunication) {
        if ( managerCommunication == null ) {
            return null;
        }
        ClientOrder clientOrder = managerCommunication.getClientOrder();
        if ( clientOrder == null ) {
            return null;
        }
        Integer id = clientOrder.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}

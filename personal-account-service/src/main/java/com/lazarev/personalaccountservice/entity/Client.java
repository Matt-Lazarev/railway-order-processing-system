package com.lazarev.personalaccountservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String phone;

    private String organization;

    private String username;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<ClientOrder> clientOrders = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<ManagerCommunication> managerCommunications = new ArrayList<>();

    public void setClientOrders(List<ClientOrder> clientOrders){
        clientOrders.forEach(clientOrder -> clientOrder.setClient(this));
        this.clientOrders = clientOrders;
    }

    public void addClientOrder(ClientOrder clientOrder){
        clientOrder.setClient(this);
        this.clientOrders.add(clientOrder);
    }

    public void setManagerCommunications(List<ManagerCommunication> managerCommunications){
        managerCommunications.forEach(managerCommunication -> managerCommunication.setClient(this));
        this.managerCommunications = managerCommunications;
    }

    public void addManagerCommunication(ManagerCommunication managerCommunication){
        managerCommunication.setClient(this);
        this.managerCommunications.add(managerCommunication);
    }
}

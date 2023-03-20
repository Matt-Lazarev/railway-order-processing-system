package com.lazarev.personalaccountservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="managers")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String phone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
    private List<Client> clients = new ArrayList<>();

    public void setClientOrders(List<Client> clients){
        this.clients.forEach(client -> client.setManager(this));
    }

    public void addClientOrder(Client client){
        clients.add(client);
    }
}

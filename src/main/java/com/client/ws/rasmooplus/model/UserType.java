package com.client.ws.rasmooplus.model;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.UUID;

import java.io.Serializable;

@Entity
@Table(name = "user_type")
public class UserType implements Serializable { // Serializable >>> transforma nosso objeto em um array de bytes ou em um formato de texto, para que melhore a navegação entre a rede ou que fique salvo em disco

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)     // autoincremental
    @Column(name = "user_type_id")                      // de acordo com a criação dos atributos no .sql
    private long id;
    private String name;
    private String description;

}

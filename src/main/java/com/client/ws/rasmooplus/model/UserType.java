package com.client.ws.rasmooplus.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "user_type")
public class UserType implements Serializable { // Serializable >>> transforma nosso objeto em um array de bytes ou em um formato de texto, para que melhore a navegação entre a rede ou que fique salvo em disco

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)     // autoincremental
    @Column(name = "user_type_id")                      // de acordo com a criação dos atributos no .sql
    private Long id;
    private String name;
    private String description;

    public UserType(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public UserType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

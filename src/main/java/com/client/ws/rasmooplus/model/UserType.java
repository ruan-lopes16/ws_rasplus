package com.client.ws.rasmooplus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// lombok > evitar repetição de código
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "user_type")
public class UserType implements Serializable { // Serializable >>> transforma nosso objeto em um array de bytes ou em um formato de texto, para que melhore a navegação entre a rede ou que fique salvo em disco

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_type_id")                      // de acordo com a criação dos atributos no .sql
    private Long id;
    private String name;
    private String description;

}

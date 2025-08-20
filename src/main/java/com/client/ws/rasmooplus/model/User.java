package com.client.ws.rasmooplus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

// lombok > evitar repetição de código
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String cpf;

    @Column(name = "dt_subscription")
    private LocalDate dtSubscription = LocalDate.now();

    @Column(name = "dt_expiration")
    private LocalDate dtExpiration;

    // vinculando com UserType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_type_id")
    private UserType userType;

    // vinculando com SubscriptionType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_type_id")
    private SubscriptionType subscriptionType;

}


package com.journey_back.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nome nao pode ser nulo")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Email nao poder nulo")
    @Email(message = "Email digitado inválido")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "senha nao pode ser nula")
    @Column(nullable = false)
    private String password;

    @Column(name = "trip_id")
    private Integer tripId;
}

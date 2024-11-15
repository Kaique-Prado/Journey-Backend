package com.journey_back.model;

import com.journey_back.request.TripRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name = "trips")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TripModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O destino não poder nulo")
    @Column(nullable = false)
    private String destination;

    @NotBlank(message = "A data de inicio não poder nulo")
    @Column(name = "starts_at", nullable = false)
    private LocalDateTime startsAt;

    @NotBlank(message = "A data final não poder nulo")
    @Column(name = "ends_at", nullable = false)
    private LocalDateTime endsAt;

    @Column(name = "is_confirmed")
    private Boolean isConfirmed;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "user_id")
    private Integer userId;

    public TripModel(TripRequest data){
        this.destination = data.destination();
        this.isConfirmed = false;
        this.ownerName = data.owner_name();
        this.startsAt = LocalDateTime.parse(data.starts_at(), DateTimeFormatter.ISO_DATE_TIME);
        this.endsAt = LocalDateTime.parse(data.ends_at(), DateTimeFormatter.ISO_DATE_TIME);
    }

}

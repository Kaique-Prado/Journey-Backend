package com.journey_back.service;

import com.journey_back.model.TripModel;
import com.journey_back.repository.TripRepository;
import com.journey_back.request.TripRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    //Construtor
    public TripService(TripRepository repository) {
        this.tripRepository = repository;
    }


    // Todas as viagens
    public List<TripModel> getTripList() {
        return tripRepository.findAll();
    }

    // Viagem especifica
    public TripModel getTripDetails(UUID id) {
        Optional<TripModel> trip = this.tripRepository.findById(id);
        if(trip.isPresent()) {
            TripModel tripDetail = trip.get();
            return tripDetail;
        } else {
            throw new RuntimeException("Viagem não econtrada");
        }
    }

    // Cadastrar uma viagem
    public TripModel registerTrip(TripModel tripModel) {
        TripModel trip = tripModel;
        tripRepository.save(tripModel);
        return trip;
    }

    //Atualizar viagem
    public TripModel updateTrip(UUID id, TripRequest request) {
        Optional<TripModel> trip = this.tripRepository.findById(id);

        if (trip.isPresent()) {
            TripModel rawTrip = trip.get();
            rawTrip.setStartsAt(LocalDateTime.parse(request.starts_at(), DateTimeFormatter.ISO_DATE_TIME));
            rawTrip.setEndsAt(LocalDateTime.parse(request.starts_at(), DateTimeFormatter.ISO_DATE_TIME));
            rawTrip.setDestination(request.destination());

            this.tripRepository.save(rawTrip);

            return rawTrip;
        } else {
            throw new RuntimeException("Viagem não econtrada");
        }
    }

    // Deletar viagem
    public TripModel deleteTrip(UUID id)  {
        Optional<TripModel> trip = this.tripRepository.findById(id);

        if (trip.isPresent()) {
            TripModel deletedTrip = trip.get();
            tripRepository.deleteById(id);
            return deletedTrip;
        } else {
            throw new RuntimeException("Viagem não econtrada");
        }
    }
}

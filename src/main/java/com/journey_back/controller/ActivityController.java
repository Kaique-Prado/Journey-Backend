package com.journey_back.controller;


import com.journey_back.model.ActivityModel;
import com.journey_back.model.LinkModel;
import com.journey_back.request.ActivityRequest;
import com.journey_back.request.LinkRequest;
import com.journey_back.service.ActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivitiesService activitiesService;

    // Construtor
    public ActivityController(ActivitiesService service) {
        this.activitiesService = service;
    }

    // Listar Links
    @GetMapping
    public ResponseEntity<List<ActivityModel>> getActivities() {
        return ResponseEntity.ok().body(activitiesService.getActivities());
    }

    // Cadastrar link
    @PostMapping("{id}")
    public ResponseEntity<ActivityModel> registerActivity(@RequestBody @Validated ActivityModel activityModel) {
        return ResponseEntity.status(201).body(activitiesService.registerActivity(activityModel));
    }

    // Atualizar link
    @PutMapping("{id}")
    public ResponseEntity<ActivityModel> updateActivity(@RequestBody @Validated UUID id, ActivityRequest activityRequest) {
        return ResponseEntity.status(201).body(activitiesService.updateActivity(id, activityRequest));
    }

    // Deletar Link
    @DeleteMapping("{id}")
    public ResponseEntity deleteActivity(@RequestBody UUID id) {
        var exists = activitiesService.deleteActivity(id);

        if (!exists) {
            throw new RuntimeException("Atividade não encontrada");
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}

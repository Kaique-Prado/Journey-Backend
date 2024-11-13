package com.journey_back.service;

import com.journey_back.model.ActivityModel;
import com.journey_back.repository.ActivityRepository;
import com.journey_back.request.ActivityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ActivitiesService {

    @Autowired
    private ActivityRepository activityRepository;

    // Construtor
    public ActivitiesService(ActivityRepository repository) {
        this.activityRepository = repository;
    }


    // Listar atividades
    public List<ActivityModel> getActivities() {
        List<ActivityModel> list = activityRepository.findAll();
        return list;
    }

    // Cadastrar atividades
    public ActivityModel registerActivity(ActivityModel activityModel) {
        ActivityModel activity = activityModel;
        activityRepository.save(activity);
        return activity;
    }

    // Atualizar atividades
    public ActivityModel updateActivity(UUID id, ActivityRequest activityRequest) {
        var activity = activityRepository.findById(id);

        if (activity.isPresent()) {
            ActivityModel activityBefore = activity.get();
            activityBefore.setTitle(activityRequest.title());
            activityBefore.setOccursAt(LocalDateTime.parse(activityRequest.occurs_at()));
            ActivityModel newActivity = activityBefore;
            this.activityRepository.save(newActivity);
            return newActivity;
        } else {
            throw new RuntimeException("Atividade não encontrada");
        }
    }

    // Deletar atividades
    public boolean deleteActivity(UUID id) {
        var activity = this.activityRepository.findById(id);

        if (activity.isPresent()) {
            activityRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

package com.journey_back.service;

import com.journey_back.infra.exception.ValidationError;
import com.journey_back.model.ActivityModel;
import com.journey_back.repository.ActivityRepository;
import com.journey_back.request.ActivityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    public ActivityModel updateActivity(Integer id, ActivityRequest activityRequest) {
        var activity = activityRepository.findById(id);

        if (activity.isPresent()) {
            ActivityModel activityBefore = activity.get();
            activityBefore.setTitle(activityRequest.title());
            activityBefore.setDate(activityRequest.date());
            ActivityModel newActivity = activityBefore;
            activityRepository.save(newActivity);
            return newActivity;
        } else {
            throw new ValidationError("Atividade nao encontrada");
        }
    }

    // Deletar atividades
    public boolean deleteActivity(Integer id) {
        var activity = this.activityRepository.findById(id);

        if (activity.isPresent()) {
            activityRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

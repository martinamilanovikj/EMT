package com.example.emtlabs.jobs;

import com.example.emtlabs.service.application.MaterializedViewRefresherInterface;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final MaterializedViewRefresherInterface materializedViewRefresher;

    public ScheduledTasks(MaterializedViewRefresherInterface materializedViewRefresher) {
        this.materializedViewRefresher = materializedViewRefresher;
    }


    @Scheduled(cron = "0 0 * * * *")
    public void refreshMaterializedView() {

        materializedViewRefresher.refreshBooksByAuthorView();
        System.out.println("Materialized view 'books_per_author' е успешно освежен.");
    }
}


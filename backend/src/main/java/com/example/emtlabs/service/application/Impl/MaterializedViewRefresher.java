package com.example.emtlabs.service.application.Impl;

import com.example.emtlabs.service.application.MaterializedViewRefresherInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MaterializedViewRefresher implements MaterializedViewRefresherInterface {

    @PersistenceContext
    private EntityManager entityManager;


    @Scheduled(cron = "0 0 * * * *")
    @Transactional
    @Override
    public void refreshBooksByAuthorView() {
        entityManager.createNativeQuery("REFRESH MATERIALIZED VIEW books_per_author").executeUpdate();
    }
}

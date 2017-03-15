package com.example;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * @author firoz
 * @since 15/03/17
 */
@Component
public class ComplaintQueryObjectUpdater {

    private final ComplaintQueryObjectRepository complaintsQueryObjectRepository;

    public ComplaintQueryObjectUpdater(ComplaintQueryObjectRepository complaintsQueryObjectRepository) {
        this.complaintsQueryObjectRepository = complaintsQueryObjectRepository;
    }

    @EventHandler
    public void on(ComplaintFiledEvent event) {
        complaintsQueryObjectRepository.save(new ComplaintQueryObject(event.getId(), event.getCompany(), event.getDescription()));
    }
}

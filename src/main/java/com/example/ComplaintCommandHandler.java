package com.example;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author firoz
 * @since 15/03/17
 */
@Slf4j
@Component
public class ComplaintCommandHandler {

    private AxonConfiguration axonConfiguration;

    private EventBus eventBus;

    private Repository<Complaint> repository;

    @Autowired
    public ComplaintCommandHandler(AxonConfiguration axonConfiguration, EventBus eventBus) {
        this.axonConfiguration = axonConfiguration;
        this.eventBus = eventBus;

        this.repository = axonConfiguration.repository(Complaint.class);
    }

    @CommandHandler
    public String newComplaint(FileComplaint fileComplaint) throws Exception {
        Aggregate<Complaint> complaintAggregate = repository.newInstance(() -> new Complaint(fileComplaint));
        return complaintAggregate.identifierAsString();
    }
}

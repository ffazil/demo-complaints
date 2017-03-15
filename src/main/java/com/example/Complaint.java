package com.example;

import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * @author firoz
 * @since 15/03/17
 */
@Aggregate
public class Complaint {

    @AggregateIdentifier
    private String complaintId;

    public Complaint() {

    }

    public Complaint(FileComplaint command) {
        Assert.hasLength(command.getCompany());
        apply(new ComplaintFiledEvent(command.getId(), command.getCompany(), command.getDescription()));
    }

    @EventSourcingHandler
    protected void on(ComplaintFiledEvent event) {
        this.complaintId = event.getId();
    }

}

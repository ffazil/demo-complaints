package com.example;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author firoz
 * @since 15/03/17
 */
@RequestMapping("/complaints")
@RestController
public class ComplaintAPI {

    private final CommandGateway commandGateway;
    private final ComplaintQueryObjectRepository complaintsQueryObjectRepository;

    public ComplaintAPI(CommandGateway commandGateway, ComplaintQueryObjectRepository complaintsQueryObjectRepository) {
        this.commandGateway = commandGateway;
        this.complaintsQueryObjectRepository = complaintsQueryObjectRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture<String> fileComplaint(@RequestBody FileComplaint fileComplaint) {
        return commandGateway.send(fileComplaint);
    }

    @GetMapping
    public List<ComplaintQueryObject> findAll() {
        return complaintsQueryObjectRepository.findAll();
    }

    @GetMapping("/{id}")
    public ComplaintQueryObject find(@PathVariable String id) {
        return complaintsQueryObjectRepository.findOne(id);
    }
}
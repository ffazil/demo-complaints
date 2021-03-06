package com.example;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
    public ResponseEntity<ComplaintQueryObject> fileComplaint(@RequestBody FileComplaint fileComplaint) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = commandGateway.send(fileComplaint);
        return Optional.of(complaintsQueryObjectRepository.findOne(future.get()))
                .map(r -> ResponseEntity.ok(r))
                .orElseThrow(() -> new RuntimeException("Did not go well"));
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
package com.skyward.DroneLogServiceV1.controllers;

import com.skyward.DroneLogServiceV1.models.DroneLog;
import com.skyward.DroneLogServiceV1.models.messages.FlightLogMessage;
import com.skyward.DroneLogServiceV1.repositories.DroneLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/logs")
public class DroneLogController {
    private final Logger logger = LoggerFactory.getLogger(DroneLogController.class);

    @Autowired
    private DroneLogRepository droneLogRepository;

    @GetMapping()
    public Flux<DroneLog> list() {
        return droneLogRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<DroneLog>> getById(@PathVariable final UUID id) {
        return droneLogRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/trip_battery_statistics")
    public Mono<ResponseEntity<Map<String, Map<String, ?>>>> getTripVoltageById(@PathVariable final UUID id) {
        return droneLogRepository.findById(id)
                .map(DroneLog::getMessage)
                .map(FlightLogMessage::getTripBatteryStatistics)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public Mono<UUID> create(@Valid @RequestBody DroneLog droneLog) {
        final UUID newId = UUID.randomUUID();

        // Do save asynchronously
        droneLog.setId(newId);
        droneLogRepository.save(droneLog).subscribe(null,
                error -> logger.error(error.getMessage()),
                () -> logger.info("Drone log saved, id={}", newId));

        return Mono.just(newId);
    }

}
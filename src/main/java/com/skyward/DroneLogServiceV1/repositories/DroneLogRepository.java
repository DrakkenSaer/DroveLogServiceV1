package com.skyward.DroneLogServiceV1.repositories;

import com.skyward.DroneLogServiceV1.models.DroneLog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface DroneLogRepository extends ReactiveMongoRepository<DroneLog, UUID> { }

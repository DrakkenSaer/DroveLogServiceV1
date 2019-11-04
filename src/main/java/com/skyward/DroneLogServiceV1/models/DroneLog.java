package com.skyward.DroneLogServiceV1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skyward.DroneLogServiceV1.models.messages.FlightLogMessage;
import com.skyward.DroneLogServiceV1.models.traits.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroneLog implements BaseEntity<UUID> {
    @Id
    private UUID id;

    @JsonProperty("exchange_type")
    private String exchangeType;
    private String exchanger;
    private UUID uploadOrgUuid;
    private UUID uploadPilotUuid;

    @JsonProperty("flight_session_id")
    private String flightSessionId;
    private FlightLogMessage message;

    @Override
    public boolean isNew() {
        return this.getId() == null;
    }
}
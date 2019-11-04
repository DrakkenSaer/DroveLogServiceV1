package com.skyward.DroneLogServiceV1.models.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skyward.DroneLogServiceV1.models.common.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@AllArgsConstructor
public class FlightLogMessage implements DroneMessage {
    private FlightLogMessageFile file;

    @JsonProperty("message_type")
    private String messageType;

    @JsonProperty("flight_logging")
    private FlightLogging flightLogging;

    @JsonProperty("flight_data")
    private FlightLogData flightData;

    public Map<String, Map<String, ?>> getTripBatteryStatistics() {
        // TODO: Sort this by timestamp
        List<Map<String, ?>> flightLogEntries = getFlightLogging().getFlightLoggingEntries();

        // TODO: Refactor this
        final Map<String, ?> start = flightLogEntries.get(0).entrySet().stream()
                .filter(entry -> entry.getKey().contains("battery"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        final Map<String, ?> end = flightLogEntries.get(flightLogEntries.size() - 1).entrySet().stream()
                .filter(entry -> entry.getKey().contains("battery"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        final Map<String, Map<String, ?>> result = new HashMap<>();
        result.put("Start", start);
        result.put("End", end);

        return result;
    }
}

// These all seemed to be embedded with the same message object and would
// only ever occur in THIS particular type of message, so I felt it proper
// to just keep them all contained within the same file.

@Data
@AllArgsConstructor
class FlightLogMessageFile {
    @JsonProperty("creation_dtg")
    private Date creationDtg;

    @JsonProperty("logging_type")
    private String loggingType;
    private String filename;
}

@Data
@AllArgsConstructor
class FlightLogging {
    private List<FlightLoggingEvent> event;

    @JsonProperty("altitude_system")
    private String altitudeSystem;

    @JsonProperty("logging_start_dtg")
    private Date loggingStartDtg;

    @JsonProperty("flight_logging_items")
    private List<List<?>> flightLoggingItems;

    @JsonProperty("flight_logging_keys")
    private List<String> flightLoggingKeys;

    public List<Map<String, ?>> getFlightLoggingEntries() {
        return flightLoggingItems.stream()
                .map(list -> IntStream.range(0, flightLoggingKeys.size())
                        .boxed()
                        .collect(Collectors.toMap(flightLoggingKeys::get, list::get))
                ).collect(Collectors.toList());
    }
}

@Data
@AllArgsConstructor
class FlightLoggingEvent {
    @JsonProperty("event_info")
    private String eventInfo;

    @JsonProperty("event_timestamp")
    private Date eventTimestamp;

    @JsonProperty("event_type")
    private String eventType;
}

@Data
@AllArgsConstructor
class FlightLogData {
    private FlightDataPayload payload;

    @JsonProperty("remote_controller")
    private RemoteController remoteController;
    private Aircraft aircraft;
    private Summary summary;
    private GCS gcs;

    @JsonProperty("flight_session_end")
    private Date flightSessionEnd;

    @JsonProperty("flight_session_start")
    private Date flightSessionStart;

    @JsonProperty("flight_controller")
    private FlightController flightController;
    private Battery battery;

    @JsonProperty("logfile_device_origin")
    private LogfileDeviceOrigin logfileDeviceOrigin;
}

@Data
@AllArgsConstructor
class FlightDataPayload {
    private CameraPayload camera;
    private GimbalPayload gimbal;
}

@Data
@AllArgsConstructor
class CameraPayload {
    @JsonProperty("serial_number")
    private String serialNumber;
    private String model;

    @JsonProperty("firmware_version")
    private String firmwareVersion;
}

@Data
@AllArgsConstructor
class GimbalPayload {
    @JsonProperty("serial_number")
    private String serialNumber;

    @JsonProperty("firmware_version")
    private String firmwareVersion;
}

@Data
@AllArgsConstructor
class Summary {
    @JsonProperty("home_location_lat")
    private String homeLocationLat;

    @JsonProperty("home_location_long")
    private String homeLocationLong;

    @JsonProperty("aircraft_smart_gohome")
    private AircraftSmartGoHome aircraftSmartGoHome;
}

@Data
@AllArgsConstructor
class AircraftSmartGoHome {
    @JsonProperty("flight_return_time")
    private Date flightReturnTime;

    @JsonProperty("landing_power")
    private Integer landingPower;

    @JsonProperty("return_power")
    private Integer returnPower;

    @JsonProperty("landing_radius")
    private Double landingRadius;

    @JsonProperty("landing_time")
    private Date landingTime;
}

@Data
@AllArgsConstructor
class LogfileDeviceOrigin {
    @JsonProperty("user_interface_idiom")
    private String userInterfaceIdiom;

    @JsonProperty("operating_system")
    private String operatingSystem;
    private String model;

    @JsonProperty("device_ssid")
    private String deviceSSID;
}

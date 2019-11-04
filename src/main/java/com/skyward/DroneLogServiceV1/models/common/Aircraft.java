package com.skyward.DroneLogServiceV1.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Aircraft {
    private String manufacturer;

    @JsonProperty("serial_number")
    private String serialNumber;
    private String name;
    private String model;

    @JsonProperty("firmware_version")
    private String firmwareVersion;
}

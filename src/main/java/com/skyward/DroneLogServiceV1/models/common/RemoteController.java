package com.skyward.DroneLogServiceV1.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RemoteController {
    @JsonProperty("serial_number")
    private String serialNumber;

    @JsonProperty("firmware_version")
    private String firmwareVersion;
}

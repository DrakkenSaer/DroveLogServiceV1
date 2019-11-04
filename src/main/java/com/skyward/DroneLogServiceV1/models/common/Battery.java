package com.skyward.DroneLogServiceV1.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Battery {
    @JsonProperty("serial_number")
    private String serialNumber;

    @JsonProperty("remaining_life")
    private Integer remainingLife;
    private Integer discharges;

    @JsonProperty("full_charge_volume")
    private Integer fullChargeVolume;

    @JsonProperty("cell_number")
    private Integer cellNumber;

    @JsonProperty("firmware_version")
    private String firmwareVersion;
}

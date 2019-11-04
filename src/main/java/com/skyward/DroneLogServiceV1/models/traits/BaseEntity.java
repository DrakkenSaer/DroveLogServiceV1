package com.skyward.DroneLogServiceV1.models.traits;

import org.springframework.data.domain.Persistable;

public interface BaseEntity<T> extends Persistable<T> {
    T getId();
    void setId(T id);
}

//package com.skyward.DroneLogServiceV1.repositories.listeners;
//
//import com.skyward.DroneLogServiceV1.models.traits.BaseEntity;
//import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
//import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Component
//public class GenerateUUIDListener extends AbstractMongoEventListener<BaseEntity<UUID>> {
//
//    @Override
//    public void onBeforeConvert(BeforeConvertEvent<BaseEntity<UUID>> event) {
//        BaseEntity<UUID> entity = event.getSource();
//        if (entity.isNew()) {
//            entity.setId(UUID.randomUUID());
//        }
//    }
//
//}
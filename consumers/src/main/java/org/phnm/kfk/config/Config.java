package org.phnm.kfk.config;

import org.apache.kafka.clients.consumer.CooperativeStickyAssignor;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

public class Config {
    public static Map<String, Object> getConfig() {
        return Map.of(
                BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",

                AUTO_OFFSET_RESET_CONFIG, "earliest",
                GROUP_ID_CONFIG, "group_one",
                KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
                PARTITION_ASSIGNMENT_STRATEGY_CONFIG, CooperativeStickyAssignor.class.getName(),
                VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()
        );
    }
}

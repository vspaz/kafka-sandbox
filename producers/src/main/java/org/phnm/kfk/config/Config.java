package org.phnm.kfk.config;

import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

public class Config {
    public static Map<String, Object> getConfig() {
        return Map.of(
                BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(),
                VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(),
                BATCH_SIZE_CONFIG, "10"
                // PARTITIONER_CLASS_CONFIG, RoundRobinPartitioner.class.getName()
        );
    }
}

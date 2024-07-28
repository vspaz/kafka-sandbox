package org.phnm.kfk;

import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

public class Config {
    public static Map<String, Object> getProducerProperties() {
        return Map.of(
                BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                ACKS_CONFIG, "all",
                BATCH_SIZE_CONFIG, Integer.toString(32 * 1024),
                COMPRESSION_TYPE_CONFIG, "snappy",
                KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(),
                LINGER_MS_CONFIG, "20",
                VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()
        );
    }

    public static String getSource() {
        return "https://stream.wikimedia.org/v2/stream/recentchange";
    }
}

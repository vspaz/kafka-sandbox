package org.phnm.kfk.keys;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.phnm.kfk.DefaultProducer;

public class KeyProducer extends DefaultProducer {

    public KeyProducer(Properties properties) {
        super(properties);
    }

    @Override
    protected String getTopic(int i) {
        return i % 2 == 0 ? "topic_1" : "topic_2";
    }

    protected String getKey(int i) {
        return i % 2 == 0 ? "key_1" : "key_2";
    }

    @Override
    public void run() {
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {
            logger.info(String.format("Producer PID (%s) started.", ProcessHandle.current().pid()));
            for (int i = 0; i < 10; i++) {
                var key = getKey(i);
                ProducerRecord<String, String> record = new ProducerRecord<>(getTopic(i), key, "value_" + i);
                producer.send(record, (recordMetadata, e) -> {
                    if (e == null) {
                        logger.info("key: '{}', partition: '{}'", key, recordMetadata.partition());
                    } else {
                        logger.error("Error received on topic '{}': {}", recordMetadata.topic(), e.toString());
                    }
                });
                Thread.sleep(REQUEST_INTERVAL);
            }
            logger.info("producer finished.");
        } catch (InterruptedException e) {
            logger.error(e.toString());
        }
    }
}

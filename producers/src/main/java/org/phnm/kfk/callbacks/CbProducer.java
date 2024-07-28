package org.phnm.kfk.callbacks;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.phnm.kfk.DefaultProducer;

import java.util.Map;

public class CbProducer extends DefaultProducer {

    public CbProducer(Map<String, Object> config) {
        super(config);
    }

    @Override
    public void run() {
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(config)) {
            logger.info(String.format("Producer PID (%s) started.", ProcessHandle.current().pid()));
            for (int i = 0; i < 100; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<>(getTopic(i), "value_" + i);
                producer.send(record, (recordMetadata, e) -> {
                    if (e == null) {
                        logger.info(
                                "received: topic '{}', partition: '{}', offset: '{}', timestamp '{}'",
                                recordMetadata.topic(),
                                recordMetadata.partition(),
                                recordMetadata.offset(),
                                recordMetadata.timestamp());
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
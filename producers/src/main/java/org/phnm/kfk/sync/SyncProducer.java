package org.phnm.kfk.sync;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.phnm.kfk.BasicProducer;

import java.util.Properties;

public class SyncProducer extends BasicProducer {

    public SyncProducer(Properties properties) {
        super(properties);
    }

    @Override
    public void run() {
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {
            logger.info(String.format("Producer PID (%s) started.", ProcessHandle.current().pid()));
            for (int i = 0; i < 100; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<>(getTopic(i), "value_" + i);
                producer.send(record);
                Thread.sleep(REQUEST_INTERVAL);
            }
            logger.info("producer finished.");
        } catch (InterruptedException e) {
            logger.error(e.toString());
        }
    }
}

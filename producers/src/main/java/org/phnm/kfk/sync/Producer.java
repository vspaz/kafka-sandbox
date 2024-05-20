package org.phnm.kfk.sync;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.phnm.kfk.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Producer {
    private static final Long REQUEST_INTERVAL = 500L;
    private Properties properties;
    private Logger logger;

    public Producer(final Logger logger, final Properties properties) {
        this.properties = properties;
        this.logger = logger;
    }

    public Producer(final Logger logger) {
        this.properties = Config.getProperties();
        this.logger = logger;
    }

    public Producer(final Properties properties) {
        this.properties = properties;
        this.logger = LoggerFactory.getLogger(Main.class.getSimpleName());
    }

    public Producer() {
        this.properties = Config.getProperties();
        this.logger = LoggerFactory.getLogger(Main.class.getSimpleName());
    }

    private String getTopic(int i) {
        return i % 2 == 0 ? Topics.ONE.getTopicName() : Topics.TWO.getTopicName();
    }

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
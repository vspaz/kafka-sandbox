package org.phnm.kfk;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.*;


public class Producer {
    private static final String TOPIC_1 = "first_topic";
    private static final String TOPIC_2 = "second_topic";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class.getSimpleName());

    public static void main(String[] args) {
        Properties properties = new Properties();
        LOGGER.info("connecting to " + BOOTSTRAP_SERVERS);
        properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);

        // set producer properties
        properties.setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // create producer
        // use try with resources/ or call flush, close manually.
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {
            LOGGER.info("producer initialized");

            for (int i = 0; i < 100; i++) {
                String topic = i % 2 == 0 ? TOPIC_2 : TOPIC_1;
                ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, "foobar_" + i);
                producer.send(producerRecord);
                Thread.sleep(500);
            }
            LOGGER.info("record sent");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
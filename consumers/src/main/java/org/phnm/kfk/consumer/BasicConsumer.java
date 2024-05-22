package org.phnm.kfk.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.phnm.kfk.DefaultConsumer;
import org.phnm.kfk.config.Topics;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class BasicConsumer extends DefaultConsumer {

    public BasicConsumer(Properties properties) {
        super(properties);
    }

    @Override
    public void run() {
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(Arrays.asList(Topics.ONE.getTopicName(), Topics.TWO.getTopicName()));
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    logger.info("key: {}, value {}, partition {}, offset {}", record.key(), record.value(), record.partition(), record.offset());
                }
            }
        }
    }
}

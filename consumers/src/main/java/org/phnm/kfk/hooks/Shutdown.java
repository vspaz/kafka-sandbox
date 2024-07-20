package org.phnm.kfk.hooks;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.phnm.kfk.DefaultConsumer;
import org.phnm.kfk.config.Topics;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Shutdown extends DefaultConsumer {

    Shutdown(Properties properties) {
        super(properties);
    }

    @Override
    public void run() {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("shutdown detected; calling 'consumer.wakeup' to exit");
            consumer.wakeup();
            try {
                mainThread.join();
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        }));

        try {
            consumer.subscribe(Arrays.asList(Topics.ONE.getTopicName(), Topics.TWO.getTopicName()));

            while (true) {
                final ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    logger.info("key: {}, value {}, partition {}, offset {}", record.key(), record.value(), record.partition(), record.offset());
                }
            }

        } catch (WakeupException e) {
            logger.info("shutting down consumer...");
        } catch (Exception e) {
            logger.error("unexpected exception", e);
        } finally {
            consumer.close();
        }
    }
}

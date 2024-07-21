package org.phnm.kfk;

import com.launchdarkly.eventsource.EventHandler;
import org.apache.kafka.clients.producer.KafkaProducer;
import com.launchdarkly.eventsource.EventSource;


import java.net.URI;
import java.util.concurrent.TimeUnit;


public class Producer {
    public void run() {
        KafkaProducer<String, String> producer = new KafkaProducer<>(Config.getProducerProperties());

        EventHandler eventHandler = new Handler(producer, "recent.updates");
        try (EventSource eventSource = new EventSource.Builder(eventHandler, URI.create(Config.getSource())).build()) {
            eventSource.start();
        }

        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.phnm.kfk;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.net.URI;
import java.util.concurrent.TimeUnit;


public class Producer {
    public void run() {
        final KafkaProducer<String, String> producer = new KafkaProducer<>(Config.getProducerProperties());
        final EventHandler eventHandler = new Handler(producer, "recent.updates");
        final EventSource eventSource = new EventSource.Builder(eventHandler, URI.create(Config.getSource())).build();
        eventSource.start();

        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

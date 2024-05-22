package org.phnm.kfk;

import org.phnm.kfk.config.Config;
import org.phnm.kfk.config.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public abstract class BasicProducer implements Producer {
    protected static final Long REQUEST_INTERVAL = 500L;
    protected Properties properties;
    protected Logger logger;

    public BasicProducer(final Logger logger, final Properties properties) {
        this.properties = properties;
        this.logger = logger;
    }

    public BasicProducer(final Logger logger) {
        this.properties = Config.getProperties();
        this.logger = logger;
    }

    public BasicProducer(final Properties properties) {
        this.properties = properties;
        this.logger = LoggerFactory.getLogger(Producer.class.getSimpleName());
    }

    public BasicProducer() {
        this.properties = Config.getProperties();
        this.logger = LoggerFactory.getLogger(Producer.class.getSimpleName());
    }

    protected String getTopic(int i) {
        return i % 2 == 0 ? Topics.ONE.getTopicName() : Topics.TWO.getTopicName();
    }
}

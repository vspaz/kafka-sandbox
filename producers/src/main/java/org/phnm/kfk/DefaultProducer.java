package org.phnm.kfk;

import java.util.Properties;

import org.phnm.kfk.config.Config;
import org.phnm.kfk.config.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DefaultProducer implements Producer {
    protected static final Long REQUEST_INTERVAL = 500L;
    protected Properties properties;
    protected Logger logger;

    public DefaultProducer(final Logger logger, final Properties properties) {
        this.properties = properties;
        this.logger = logger;
    }

    public DefaultProducer(final Logger logger) {
        this.properties = Config.getProperties();
        this.logger = logger;
    }

    public DefaultProducer(final Properties properties) {
        this.properties = properties;
        this.logger = LoggerFactory.getLogger(Producer.class.getSimpleName());
    }

    public DefaultProducer() {
        this.properties = Config.getProperties();
        this.logger = LoggerFactory.getLogger(Producer.class.getSimpleName());
    }

    protected String getTopic(int i) {
        return i % 2 == 0 ? Topics.ONE.getTopicName() : Topics.TWO.getTopicName();
    }
}

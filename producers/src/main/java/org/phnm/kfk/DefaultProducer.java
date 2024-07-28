package org.phnm.kfk;

import org.phnm.kfk.config.Config;
import org.phnm.kfk.config.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public abstract class DefaultProducer implements Producer {
    protected static final Long REQUEST_INTERVAL = 500L;
    protected Map<String, Object> config;
    protected Logger logger;

    public DefaultProducer(final Logger logger, final Map<String, Object> properties) {
        this.config = properties;
        this.logger = logger;
    }

    public DefaultProducer(final Logger logger) {
        this.config = Config.getConfig();
        this.logger = logger;
    }

    public DefaultProducer(final Map<String, Object> properties) {
        this.config = properties;
        this.logger = LoggerFactory.getLogger(Producer.class.getSimpleName());
    }

    public DefaultProducer() {
        this.config = Config.getConfig();
        this.logger = LoggerFactory.getLogger(Producer.class.getSimpleName());
    }

    protected String getTopic(int i) {
        return i % 2 == 0 ? Topics.ONE.getTopicName() : Topics.TWO.getTopicName();
    }
}

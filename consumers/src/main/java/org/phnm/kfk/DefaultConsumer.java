package org.phnm.kfk;

import org.phnm.kfk.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public abstract class DefaultConsumer implements Consumer {
    protected Map<String, Object> config;
    protected Logger logger;

    public DefaultConsumer(final Logger logger, final Map<String, Object> config) {
        this.config = config;
        this.logger = logger;
    }

    public DefaultConsumer(final Logger logger) {
        this.config = Config.getConfig();
        this.logger = logger;
    }

    public DefaultConsumer(final Map<String, Object> config) {
        this.config = config;
        this.logger = LoggerFactory.getLogger(DefaultConsumer.class.getSimpleName());
    }

    public DefaultConsumer() {
        this.config = Config.getConfig();
        this.logger = LoggerFactory.getLogger(DefaultConsumer.class.getSimpleName());
    }
}

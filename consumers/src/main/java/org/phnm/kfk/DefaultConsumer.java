package org.phnm.kfk;

import java.util.Properties;

import org.phnm.kfk.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DefaultConsumer implements Consumer {
    protected Properties properties;
    protected Logger logger;

    public DefaultConsumer(final Logger logger, final Properties properties) {
        this.properties = properties;
        this.logger = logger;
    }

    public DefaultConsumer(final Logger logger) {
        this.properties = Config.getProperties();
        this.logger = logger;
    }

    public DefaultConsumer(final Properties properties) {
        this.properties = properties;
        this.logger = LoggerFactory.getLogger(DefaultConsumer.class.getSimpleName());
    }

    public DefaultConsumer() {
        this.properties = Config.getProperties();
        this.logger = LoggerFactory.getLogger(DefaultConsumer.class.getSimpleName());
    }
}

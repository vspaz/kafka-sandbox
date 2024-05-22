package org.phnm.kfk.consumer;

import org.phnm.kfk.config.Config;

public class Main {
    public static void main(String[] args) {
        new BasicConsumer(Config.getProperties()).run();
    }
}

package org.phnm.kfk.keys;

import org.phnm.kfk.config.Config;

public class Main {
    public static void main(String[] args) {
        new KeyProducer(Config.getProperties()).run();
    }
}

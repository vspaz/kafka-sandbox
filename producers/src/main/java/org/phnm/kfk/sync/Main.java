package org.phnm.kfk.sync;

import org.phnm.kfk.config.Config;

public class Main {
    public static void main(String[] args) {
        new SyncProducer(Config.getProperties()).run();
    }
}

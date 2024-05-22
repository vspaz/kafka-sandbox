package org.phnm.kfk.callbacks;

import org.phnm.kfk.config.Config;

public class Main {
    public static void main(String[] args) {
        new CbProducer(Config.getProperties()).run();
    }
}

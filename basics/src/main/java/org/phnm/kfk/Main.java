package org.phnm.kfk;

import org.phnm.kfk.producer.Config;
import org.phnm.kfk.producer.Producer;

public class Main {
    public static void main(String[] args) {
        new Producer(Config.getProperties()).run();
    }
}

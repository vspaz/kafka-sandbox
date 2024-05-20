package org.phnm.kfk;

import org.phnm.kfk.sync.Config;
import org.phnm.kfk.sync.Producer;

public class Main {
    public static void main(String[] args) {
        new Producer(Config.getProperties()).run();
    }
}

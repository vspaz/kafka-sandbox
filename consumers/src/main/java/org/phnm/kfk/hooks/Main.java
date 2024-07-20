package org.phnm.kfk.hooks;

import org.phnm.kfk.config.Config;

public class Main {
    public static void main(String[] args) {
        new Shutdown(Config.getProperties()).run();
    }
}

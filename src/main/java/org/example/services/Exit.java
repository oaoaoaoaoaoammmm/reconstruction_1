package org.example.services;

import lombok.extern.java.Log;

import java.util.logging.Level;

@Log
public class Exit {

    public void execute() {
        log.log(Level.INFO, "Shutdown...");
        System.exit(0);
    }
}

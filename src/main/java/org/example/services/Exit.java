package org.example.services;

import lombok.extern.java.Log;
import org.example.usecase.Command;

import java.util.logging.Level;

@Log
public class Exit implements Command {

    @Override
    public void execute() {
        log.log(Level.INFO, "Shutdown...");
        System.exit(0);
    }
}

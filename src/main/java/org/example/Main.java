package org.example;

import org.example.repository.DragonRepo;
import org.example.services.*;
import org.example.usecase.Manager;
import org.example.usecase.Start;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class Main {
    private static Logger log;
    private final static Start controller = new EntryPoint(
            Manager.builder()
                    .dragonRepo(new DragonRepo())
                    .reader(new ReaderFromConsoleService())
                    .build()
    );

    public static void main(String[] args) throws Exception {
        configureLog();
        log.log(Level.INFO, "Ready for use");
        controller.start();
    }

    private static void configureLog() {
        try (InputStream ins = Thread.currentThread().getContextClassLoader().getResourceAsStream("applicationLog.properties")) {
            LogManager.getLogManager().readConfiguration(ins);
            log = Logger.getLogger(Main.class.getName());
        } catch (Exception ex) {
            System.out.println("Log error");
        }
    }
}
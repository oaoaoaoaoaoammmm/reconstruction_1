package org.example;

import org.example.controller.Controller;
import org.example.repository.DragonRepo;
import org.example.services.*;
import org.example.usecase.Manager;
import org.example.usecase.Start;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class Main {
    private static Logger log;

    static {
        try (InputStream ins = Files.newInputStream(Path.of("src\\main\\resources\\applicationLog.properties"))) {
            LogManager.getLogManager().readConfiguration(ins);
            log = Logger.getLogger(Main.class.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private final static Start controller = new Controller(
            new Manager(
                    new DragonRepo(),
                    new ReaderFromConsoleService(),

                    new AddIfMax(),
                    new AverageOfAge(),
                    new CountEqualsThatType(),
                    new Exit(),
                    new Help(),
                    new Info(),
                    new PrintFieldAscendingColor(),
                    new Reorder(),
                    new Show(),
                    new Update()
            )
    );

    public static void main(String[] args) throws Exception {
        log.log(Level.INFO, "Ready for use");
        controller.start();
    }
}
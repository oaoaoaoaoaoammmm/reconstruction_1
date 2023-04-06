package org.example;

import org.example.controller.Controller;
import org.example.repository.DragonRepo;
import org.example.services.*;
import org.example.usecase.Manager;
import org.example.usecase.Start;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class Main {
    private static Logger log;

    static {
        try (InputStream ins = new FileInputStream("src\\main\\resources\\applicationLog.properties")) {
            LogManager.getLogManager().readConfiguration(ins);
            log = Logger.getLogger(Main.class.getName());
        } catch (Exception ex) {
            System.out.println("Log error");
        }
    }

    private final static Start controller = Controller.builder()
            .manager(
                    Manager.builder()
                            .dragonRepo(new DragonRepo())
                            .reader(new ReaderFromConsoleService())

                            .reorder(new Reorder())
                            .exit(new Exit())
                            .averageOfAge(new AverageOfAge())
                            .addIfMax(new AddIfMax())
                            .countLessThatType(new CountEqualsThatType())
                            .printFieldAscendingColor(new PrintFieldAscendingColor())
                            .info(new Info())
                            .show(new Show())
                            .update(new Update())
                            .help(new Help())
                            .build()
            ).build();

    public static void main(String[] args) throws Exception {
        log.log(Level.INFO, "Ready for use");
        controller.start();
    }
}
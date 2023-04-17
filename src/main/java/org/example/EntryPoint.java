package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.example.usecase.Manage;
import org.example.usecase.Start;

import java.util.Scanner;
import java.util.logging.Level;

@Log
@RequiredArgsConstructor
public class EntryPoint implements Start {
    private final Manage manager;
    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello!\nWrite 'help' for the help");
        while (true) {
            try {

                String req = scanner.nextLine();
                log.log(Level.INFO, "Request accepted: " + req);
                manager.manage(req);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}

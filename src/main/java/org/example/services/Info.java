package org.example.services;

import org.example.entity.Dragon;
import org.example.usecase.Command;

import java.util.List;

public class Info implements Command {

    @Override
    public void execute(List<Dragon> dragons) {
        System.out.println("Collection's size - " + dragons.size());
    }
}

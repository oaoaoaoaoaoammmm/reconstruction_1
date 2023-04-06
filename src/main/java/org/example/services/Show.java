package org.example.services;

import org.example.entity.Dragon;
import org.example.usecase.Command;

import java.util.List;

public class Show implements Command {

    @Override
    public void execute(List<Dragon> dragons) {
        dragons.forEach(System.out::println);
    }
}

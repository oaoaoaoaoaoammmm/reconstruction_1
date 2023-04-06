package org.example.services;

import org.example.entity.Dragon;
import org.example.usecase.Command;

import java.util.List;

public class AverageOfAge implements Command {

    @Override
    public void execute(List<Dragon> dragons) {
        dragons.stream().
                mapToLong(Dragon::getAge)
                .average()
                .ifPresent(System.out::println);
    }
}

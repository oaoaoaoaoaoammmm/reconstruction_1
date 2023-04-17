package org.example.services;

import org.example.domain.Dragon;
import org.example.usecase.Command;

import java.util.Comparator;
import java.util.List;

public class Reorder implements Command {

    @Override
    public void execute(List<Dragon> dragons) {
        dragons.stream()
                .sorted(Comparator.comparing(Dragon::getAge))
                .forEach(System.out::println);
    }
}

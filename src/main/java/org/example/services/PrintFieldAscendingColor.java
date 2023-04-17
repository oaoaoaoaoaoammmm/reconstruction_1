package org.example.services;

import org.example.domain.Dragon;
import org.example.model.Color;
import org.example.usecase.Command;

import java.util.List;

public class PrintFieldAscendingColor implements Command {

    @Override
    public void execute(List<Dragon> dragons) {
        dragons.stream()
                .map(this::mapToColor)
                .sorted()
                .forEach(System.out::println);
    }

    private Color mapToColor(Dragon dragon) {
        return dragon.getColor();
    }
}

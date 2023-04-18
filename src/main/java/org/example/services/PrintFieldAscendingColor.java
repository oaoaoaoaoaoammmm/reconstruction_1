package org.example.services;

import lombok.AllArgsConstructor;
import org.example.domain.Dragon;
import org.example.model.Color;

import java.util.List;

@AllArgsConstructor
public class PrintFieldAscendingColor {
    private final List<Dragon> dragons;

    public void execute() {
        dragons.stream()
                .map(this::mapToColor)
                .sorted()
                .forEach(System.out::println);
    }

    private Color mapToColor(Dragon dragon) {
        return dragon.getColor();
    }
}

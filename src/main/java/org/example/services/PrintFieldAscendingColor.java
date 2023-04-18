package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.domain.Dragon;
import org.example.model.Color;

import java.util.List;

@RequiredArgsConstructor
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

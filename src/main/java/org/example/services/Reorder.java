package org.example.services;

import lombok.AllArgsConstructor;
import org.example.domain.Dragon;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
public class Reorder {
    private final List<Dragon> dragons;

    public void execute() {
        dragons.stream()
                .sorted(Comparator.comparing(Dragon::getAge))
                .forEach(System.out::println);
    }
}

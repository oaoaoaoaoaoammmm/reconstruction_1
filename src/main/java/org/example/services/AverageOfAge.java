package org.example.services;

import lombok.AllArgsConstructor;
import org.example.domain.Dragon;

import java.util.List;

@AllArgsConstructor
public class AverageOfAge {

    private final List<Dragon> dragons;

    public void execute() {
        dragons.stream()
                .mapToLong(Dragon::getAge)
                .average()
                .ifPresent(System.out::println);
    }
}

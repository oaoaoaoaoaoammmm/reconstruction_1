package org.example.services;

import lombok.AllArgsConstructor;
import org.example.domain.Dragon;

import java.util.List;
import java.util.NoSuchElementException;


@AllArgsConstructor
public class AddIfMax {

    private final List<Dragon> dragons;
    private final Dragon dragon;

    public Dragon execute() {

        long max = dragons.stream()
                .mapToLong(Dragon::getAge)
                .max()
                .orElseThrow(NoSuchElementException::new);

        if (dragon.getAge() > max) return dragon;
        else throw new RuntimeException("Element is lower");
    }
}

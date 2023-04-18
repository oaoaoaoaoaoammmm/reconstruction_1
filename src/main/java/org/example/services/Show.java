package org.example.services;

import lombok.AllArgsConstructor;
import org.example.domain.Dragon;

import java.util.List;

@AllArgsConstructor
public class Show {
    private final List<Dragon> dragons;

    public void execute() {
        dragons.forEach(System.out::println);
    }
}

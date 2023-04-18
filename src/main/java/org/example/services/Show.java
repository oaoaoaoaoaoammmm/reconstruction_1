package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.domain.Dragon;

import java.util.List;

@RequiredArgsConstructor
public class Show {
    private final List<Dragon> dragons;

    public void execute() {
        dragons.forEach(System.out::println);
    }
}

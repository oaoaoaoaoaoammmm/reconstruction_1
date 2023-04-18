package org.example.services;

import lombok.AllArgsConstructor;
import org.example.domain.Dragon;

import java.util.List;

@AllArgsConstructor
public class Info {
    private final List<Dragon> dragons;

    public void execute() {
        System.out.println("Collection's size - " + dragons.size());
    }
}

package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.domain.Dragon;

import java.util.List;

@RequiredArgsConstructor
public class Info {
    private final List<Dragon> dragons;

    public void execute() {
        System.out.println("Collection's size - " + dragons.size());
    }
}

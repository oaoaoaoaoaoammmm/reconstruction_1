package org.example.services;

import org.example.entity.Dragon;
import org.example.usecase.Command;

import java.util.List;
import java.util.NoSuchElementException;


public class AddIfMax implements Command {

    @Override
    public Dragon execute(List<Dragon> dragons, Dragon dragon) {

        if (dragon.getAge() > dragons.stream()
                .mapToLong(Dragon::getAge)
                .max()
                .orElseThrow(NoSuchElementException::new)) {
            return dragon;
        } else throw new RuntimeException("Element is lower");
    }
}

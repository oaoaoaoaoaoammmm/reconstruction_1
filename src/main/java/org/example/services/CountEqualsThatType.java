package org.example.services;

import org.example.entity.Dragon;
import org.example.entity.enums.DragonType;
import org.example.usecase.Command;

import java.util.List;

public class CountEqualsThatType implements Command {

    @Override
    public void execute(List<Dragon> dragons, DragonType type) {
        System.out.println(
                dragons.stream()
                        .map(this::mapToType)
                        .filter(type::equals)
                        .count()
        );
    }

    private DragonType mapToType(Dragon dragon) {
        return dragon.getType();
    }
}

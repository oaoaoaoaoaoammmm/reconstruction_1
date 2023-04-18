package org.example.services;

import lombok.AllArgsConstructor;
import org.example.domain.Dragon;
import org.example.model.DragonType;

import java.util.List;

@AllArgsConstructor
public class CountEqualsThatType {
    private final List<Dragon> dragons;
    private final DragonType type;

    public void execute() {
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

package org.example.usecase;

import org.example.dto.Request;
import org.example.entity.Dragon;
import org.example.entity.enums.DragonType;

import java.util.List;

public interface Command {
    default void execute() {}
    default void execute(List<Dragon> dragons) {}
    default void execute(List<Dragon> dragons, DragonType type) {}
    default Dragon execute(List<Dragon> dragons, Dragon dragon){
        return dragon;
    }
    default Dragon execute(long id, Dragon dragon) {
        return dragon;
    }
}

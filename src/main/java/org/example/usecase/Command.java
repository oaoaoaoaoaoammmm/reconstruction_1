package org.example.usecase;

import org.example.dto.Request;
import org.example.entity.Dragon;
import org.example.entity.enums.DragonType;

import java.util.List;

public interface Command {
    default void execute() {
        throw new UnsupportedOperationException();
    }
    default void execute(List<Dragon> dragons) {
        throw new UnsupportedOperationException();
    }
    default void execute(List<Dragon> dragons, DragonType type) {
        throw new UnsupportedOperationException();
    }
    default Dragon execute(List<Dragon> dragons, Dragon dragon){
        throw new UnsupportedOperationException();
    }
    default Dragon execute(long id, Dragon dragon) {
        throw new UnsupportedOperationException();
    }
}

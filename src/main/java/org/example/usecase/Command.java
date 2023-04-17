package org.example.usecase;

import org.example.domain.Dragon;
import org.example.model.DragonType;

import java.util.List;

public interface Command {
    default void execute() {
        throw new UnsupportedOperationException("Unsupported execute");
    }

    default void execute(List<Dragon> dragons) {
        throw new UnsupportedOperationException("Unsupported execute");
    }

    default void execute(List<Dragon> dragons, DragonType type) {
        throw new UnsupportedOperationException("Unsupported execute");
    }

    default Dragon execute(List<Dragon> dragons, Dragon dragon) {
        throw new UnsupportedOperationException("Unsupported execute");
    }

    default Dragon execute(long id, Dragon dragon) {
        throw new UnsupportedOperationException("Unsupported execute");
    }
}

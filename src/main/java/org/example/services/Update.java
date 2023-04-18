package org.example.services;

import lombok.AllArgsConstructor;
import org.example.domain.Dragon;

@AllArgsConstructor
public class Update {
    private final Dragon dragon;
    private final long id;

    public Dragon execute() {
        dragon.setId(id);
        return dragon;
    }
}

package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.domain.Dragon;

@RequiredArgsConstructor
public class Update {
    private final Dragon dragon;
    private final long id;

    public Dragon execute() {
        dragon.setId(id);
        return dragon;
    }
}

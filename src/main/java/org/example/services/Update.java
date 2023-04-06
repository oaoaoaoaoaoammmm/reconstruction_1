package org.example.services;

import org.example.entity.Dragon;
import org.example.usecase.Command;


public class Update implements Command {

    @Override
    public Dragon execute(long id, Dragon dragon) {
        dragon.setId(id);
        return dragon;
    }
}

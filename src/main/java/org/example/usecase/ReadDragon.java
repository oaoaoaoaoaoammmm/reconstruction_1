package org.example.usecase;

import org.example.entity.Dragon;
import org.example.entity.enums.DragonType;

import java.util.zip.DataFormatException;

public interface ReadDragon {
    Dragon readDragon() throws DataFormatException;
    DragonType readDragonType() throws DataFormatException;
    Long readId() throws DataFormatException;
}

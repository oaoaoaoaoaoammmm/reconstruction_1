package org.example.usecase;

import org.example.domain.Dragon;
import org.example.model.DragonType;

import java.util.zip.DataFormatException;

public interface ReadDragon {
    Dragon readDragon() throws DataFormatException;
    DragonType readDragonType() throws DataFormatException;
    Long readId() throws DataFormatException;
}

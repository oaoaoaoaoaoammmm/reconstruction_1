package org.example.usecase;

import org.example.entity.Dragon;
import org.example.exception.DataBaseException;

import java.util.List;

public interface EntityManager {
    void save(Dragon dragon) throws DataBaseException;

    void update(Dragon dragon) throws DataBaseException;

    void deleteById(long id) throws DataBaseException;

    void deleteLast() throws DataBaseException;

    void deleteAll() throws DataBaseException;

    List<Dragon> findAll() throws DataBaseException;
}

package org.example.usecase;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.example.exception.DataBaseException;
import org.example.exception.ManageException;
import org.example.services.*;

import java.util.logging.Level;
import java.util.zip.DataFormatException;

@Log
@Builder
@RequiredArgsConstructor
public class Manager implements Manage {
    private final EntityManager dragonRepo;
    private final ReadDragon reader;

    @Override
    public void manage(String req) throws DataFormatException, ManageException, DataBaseException {
        log.log(Level.INFO, "Manage request: " + req);

        switch (req) {
            case "help" -> new Help().execute();
            case "exit" -> new Exit().execute();
            case "info" -> new Info(dragonRepo.findAll()).execute();
            case "show" -> new Show(dragonRepo.findAll()).execute();
            case "reorder" -> new Reorder(dragonRepo.findAll()).execute();
            case "average_of_age" -> new AverageOfAge(dragonRepo.findAll()).execute();
            case "print_field_ascending_color" -> new PrintFieldAscendingColor(dragonRepo.findAll()).execute();

            case "clear" -> dragonRepo.deleteAll();
            case "add" -> dragonRepo.save(reader.readDragon());
            case "add_if_max" -> dragonRepo.save(new AddIfMax(dragonRepo.findAll(), reader.readDragon()).execute());

            case "remove_by_id" -> dragonRepo.deleteById(reader.readId());
            case "remove_last" -> dragonRepo.deleteLast();

            case "update" -> dragonRepo.update(new Update(reader.readDragon(), reader.readId()).execute());

            case "count_equals_that_type" ->
                    new CountEqualsThatType(dragonRepo.findAll(), reader.readDragonType()).execute();

            default -> throw new ManageException("Unknown command");
        }
        log.log(Level.INFO, "Request " + req + " completed");
    }
}

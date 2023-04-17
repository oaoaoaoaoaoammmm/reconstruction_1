package org.example.usecase;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.example.exception.DataBaseException;
import org.example.exception.ManageException;

import java.util.logging.Level;
import java.util.zip.DataFormatException;

@Log
@Builder
@RequiredArgsConstructor
public class Manager implements Manage {
    private final EntityManager dragonRepo;
    private final ReadDragon reader;
    private final Command addIfMax, averageOfAge, countLessThatType,
            exit, help, info, printFieldAscendingColor, reorder, show, update;

    @Override
    public void manage(String req) throws DataFormatException, ManageException, DataBaseException {
        log.log(Level.INFO, "Manage request: " + req);


        switch (req) {
            case "help" -> help.execute();
            case "exit" -> exit.execute();
            case "info" -> info.execute(dragonRepo.findAll());
            case "show" -> show.execute(dragonRepo.findAll());
            case "reorder" -> reorder.execute(dragonRepo.findAll());
            case "average_of_age" -> averageOfAge.execute(dragonRepo.findAll());
            case "print_field_ascending_color" -> printFieldAscendingColor.execute(dragonRepo.findAll());

            case "clear" -> dragonRepo.deleteAll();
            case "add" -> dragonRepo.save(reader.readDragon());
            case "add_if_max" -> dragonRepo.save(addIfMax.execute(dragonRepo.findAll(), reader.readDragon()));

            case "remove_by_id" -> dragonRepo.deleteById(reader.readId());
            case "remove_last" -> dragonRepo.deleteLast();

            case "update" -> dragonRepo.update(update.execute(reader.readId(), reader.readDragon()));

            case "count_equals_that_type" -> countLessThatType.execute(dragonRepo.findAll(), reader.readDragonType());

            default -> throw new ManageException("Unknown command");
        }
        log.log(Level.INFO, "Request " + req + " completed");
    }
}

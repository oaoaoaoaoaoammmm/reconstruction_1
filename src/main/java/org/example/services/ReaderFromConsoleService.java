package org.example.services;

import lombok.extern.java.Log;
import org.example.domain.Coordinates;
import org.example.domain.Dragon;
import org.example.domain.Person;
import org.example.model.Color;
import org.example.model.DragonCharacter;
import org.example.model.DragonType;
import org.example.usecase.ReadDragon;

import java.util.Arrays;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.zip.DataFormatException;

@Log
public class ReaderFromConsoleService implements ReadDragon {

    @Override
    public Long readId() {
        Scanner scanner = new Scanner(System.in);
        log.log(Level.INFO, "Reading dragon id from console");
        System.out.println("Enter id:");
        return readFromConsole(scanner::nextLong, (id) -> id > 0);
    }

    @Override
    public DragonType readDragonType() {
        Scanner scanner = new Scanner(System.in);
        log.log(Level.INFO, "Reading dragonType from console");
        System.out.println("Enter dragon's type WATER, UNDERGROUND, AIR, FIRE:");
        var type = readFromConsole(scanner::next,
                (t) -> Arrays.stream(DragonType.values())
                        .anyMatch((dragonType) -> t.equals(dragonType.name()))
        );
        return DragonType.valueOf(type);
    }

    @Override
    public Dragon readDragon() throws DataFormatException {

        Scanner scanner = new Scanner(System.in);
        log.log(Level.INFO, "Reading dragon from console");

        try {

            System.out.println("Enter dragon's name:");
            var name = readFromConsole(scanner::next, (n) -> !n.equals(""));

            System.out.println("Enter dragon's age > 0:");
            var age = readFromConsole(scanner::nextLong, (a) -> a > 0);
            var type = readDragonType();

            System.out.println("Enter dragon's color GREEN, BLACK, ORANGE, BROWN:");
            var color = readFromConsole(scanner::next,
                    (c) -> Arrays.stream(Color.values())
                            .anyMatch((dragonColor) -> c.equals(dragonColor.name()))
            );

            System.out.println("Enter dragon's character CUNNING, EVIL, CHAOTIC_EVIL:");
            var character = readFromConsole(scanner::next,
                    (c) -> Arrays.stream(DragonCharacter.values())
                            .anyMatch((dragonCharacter) -> c.equals(dragonCharacter.name()))
            );

            var coordinates = readCoordinates(scanner);

            var person = readPerson(scanner);


            log.log(Level.INFO, "Dragon creation");

            return Dragon.builder()
                    .name(name)
                    .coordinates(coordinates)
                    .age(age)
                    .creationDate(new Date())
                    .color(Color.valueOf(color))
                    .character(DragonCharacter.valueOf(character))
                    .type(type)
                    .killer(person)
                    .build();

        } catch (InputMismatchException ex) {
            log.log(Level.WARNING, "Dragon wasn't created, error with data");
            throw new DataFormatException("Dragon wasn't created, error with data");
        }
    }

    private Coordinates readCoordinates(Scanner scanner) {
        System.out.println("Enter coordinate X:");
        var x = readFromConsole(scanner::nextFloat, (X) -> !X.isNaN());

        System.out.println("Enter coordinate Y <= 902:");
        var y = readFromConsole(scanner::nextLong, (Y) -> Y <= 902);
        return Coordinates.builder()
                .x(x)
                .y(y)
                .build();
    }

    private Person readPerson(Scanner scanner) {
        System.out.println("Enter killer's name:");
        var killerName = readFromConsole(scanner::next, (n) -> !n.equals(""));

        System.out.println("Enter killer's height:");
        var killerHeight = readFromConsole(scanner::nextInt, (h) -> h > 0);

        System.out.println("Enter killer's weight:");
        var killerWeight = readFromConsole(scanner::nextDouble, (w) -> w > 0);

        System.out.println("Enter killer's eyes color GREEN, BLACK, ORANGE, BROWN:");
        var killerEyesColor = readFromConsole(scanner::next,
                (e) -> Arrays.stream(Color.values())
                        .anyMatch((color1) -> e.equals(color1.name()))
        );

        System.out.println("Enter killer's hair color GREEN, BLACK, ORANGE, BROWN:");
        var killerHairColor = readFromConsole(scanner::next,
                (h) -> Arrays.stream(Color.values())
                        .anyMatch((color1) -> h.equals(color1.name()))
        );
        return Person.builder()
                .name(killerName)
                .height(killerHeight)
                .weight(killerWeight)
                .eyeColor(Color.valueOf(killerEyesColor))
                .hairColor(Color.valueOf(killerHairColor))
                .build();
    }

    private <T> T readFromConsole(Supplier<T> operator, Predicate<T> predicate) {
        T attribute = operator.get();
        if (predicate.test(attribute)) return attribute;
        else throw new InputMismatchException();
    }
}

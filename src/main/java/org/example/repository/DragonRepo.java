package org.example.repository;

import lombok.extern.java.Log;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.example.entity.Coordinates;
import org.example.entity.Dragon;
import org.example.entity.Person;
import org.example.entity.enums.Color;
import org.example.entity.enums.DragonCharacter;
import org.example.entity.enums.DragonType;
import org.example.exception.DataBaseException;
import org.example.usecase.EntityManager;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

@Log
public class DragonRepo implements EntityManager {
    private Connection conn;

    {
        try {
            conn = getConnection();
            log.log(Level.INFO, "Database connection completed");

            if (existsValues() < 1) {
                insertDefaultValues();
                log.log(Level.INFO, "Default values inserted");
            } else log.log(Level.INFO, "There are values");

        } catch (SQLException | IOException ex) {
            log.log(Level.WARNING, "Database setting failed");
            System.out.println("Setting to database is failed\nShutdown...");
            System.exit(1);
        }
    }

    private Connection getConnection() throws IOException, SQLException {
        Properties props = new Properties();
        log.log(Level.INFO, "Reading properties for database");
        try (InputStream in = new FileInputStream("src\\main\\resources\\database.properties")) {
            props.load(in);
        }

        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        log.log(Level.INFO, "Connecting to the database");

        return DriverManager.getConnection(url, username, password);
    }

    private int existsValues() throws SQLException {
        PreparedStatement statement = conn.prepareStatement("""
                select count(*) from dragon;
                """);
        log.log(Level.INFO, "Checking values in the dragon's table");
        ResultSet results = statement.executeQuery();
        results.next();
        return results.getInt("count(*)");
    }

    private void insertDefaultValues() throws IOException, SQLException {
        ScriptRunner scriptRunner = new ScriptRunner(conn);
        log.log(Level.INFO, "Reading scripts with values");
        try (Reader reader = new FileReader("src\\main\\resources\\scripts\\1-insert-default-values.sql")) {
            scriptRunner.runScript(reader);
        }

    }

    @Override
    public List<Dragon> findAll() throws DataBaseException {
        List<Dragon> dragons = new ArrayList<>();

        try {
            PreparedStatement statement = conn.prepareStatement("""
                    select d.id, d.firstname, d.creation_date, d.age, d.color, d.type, d.dragon_character,
                    c.x, c.y,
                    p.firstname, p.height, p.weight, p.eye_color, p.hair_color
                    from dragon as d
                    join coordinates c on d.id = c.dragon_id
                    join person p on d.id = p.dragon_id;
                    """);

            log.log(Level.INFO, """
                    Database query:
                    select d.id, d.firstname, d.creation_date, d.age, d.color, d.type, d.dragon_character,
                    c.x, c.y,
                    p.firstname, p.height, p.weight, p.eye_color, p.hair_color
                    from dragon as d
                    join coordinates c on d.id = c.dragon_id
                    join person p on d.id = p.dragon_id
                    """);

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                dragons.add(
                        Dragon.builder()
                                .id(results.getLong("d.id"))
                                .name(results.getString("d.firstname"))
                                .coordinates(
                                        Coordinates.builder()
                                                .x(results.getFloat("c.x"))
                                                .y(results.getLong("c.y"))
                                                .build()
                                )
                                .age(results.getLong("d.age"))
                                .creationDate(results.getDate("d.creation_date"))
                                .color(Color.valueOf(results.getString("d.color")))
                                .character(DragonCharacter.valueOf(results.getString("d.dragon_character")))
                                .type(DragonType.valueOf(results.getString("d.type")))
                                .killer(
                                        Person.builder()
                                                .name(results.getString("p.firstname"))
                                                .height(results.getDouble("p.height"))
                                                .weight(results.getDouble("p.weight"))
                                                .eyeColor(Color.valueOf(results.getString("p.eye_color")))
                                                .hairColor(Color.valueOf(results.getString("p.hair_color")))
                                                .build()
                                )
                                .build()
                );
            }

            return dragons;
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Database query denied");
            throw new DataBaseException("Database query denied");
        }
    }

    @Override
    public void save(Dragon dragon) throws DataBaseException {
        try {
            PreparedStatement statement = conn.prepareStatement("""
                    insert into dragon (firstname, creation_date, age, color, type, dragon_character)
                    VALUES (?, ?, ?, ?, ?, ?);
                    """);

            statement.setString(1, dragon.getName());
            statement.setDate(2, new Date(dragon.getCreationDate().getTime()));
            statement.setLong(3, dragon.getAge());
            statement.setString(4, dragon.getColor().name());
            statement.setString(5, dragon.getType().name());
            statement.setString(6, dragon.getCharacter().name());

            log.log(Level.INFO, """
                    Database query:
                    insert into dragon (firstname, creation_date, age, color, type, dragon_character)
                    """);

            statement.executeUpdate();

            statement = conn.prepareStatement("""
                    insert into coordinates (dragon_id, x, y)
                                    VALUES ((select id from dragon where dragon.firstname = ?), ?, ?);
                            """);
            statement.setString(1, dragon.getName());
            statement.setFloat(2, dragon.getCoordinates().getX());
            statement.setLong(3, dragon.getCoordinates().getY());

            log.log(Level.INFO, """
                    Database query:
                    insert into coordinates (dragon_id, x, y)
                    """);

            statement.executeUpdate();

            statement = conn.prepareStatement("""
                    insert into person (dragon_id, firstname, height, weight, eye_color, hair_color)
                                    VALUES ((select id from dragon where dragon.firstname = ?),
                                            ?, ?, ?, ?, ?);
                                            """);

            statement.setString(1, dragon.getName());
            statement.setString(2, dragon.getKiller().getName());
            statement.setDouble(3, dragon.getKiller().getHeight());
            statement.setDouble(4, dragon.getKiller().getWeight());
            statement.setString(5, dragon.getKiller().getEyeColor().name());
            statement.setString(6, dragon.getKiller().getHairColor().name());

            log.log(Level.INFO, """
                    Database query:
                    insert into person (dragon_id, firstname, height, weight, eye_color, hair_color)
                    """);

            statement.executeUpdate();

        } catch (SQLException ex) {
            log.log(Level.WARNING, "Database query denied");
            throw new DataBaseException("Database query denied");
        }
    }

    @Override
    public void update(Dragon dragon) throws DataBaseException {
        try {
            PreparedStatement statement = conn.prepareStatement("""
                    update dragon as d, coordinates as c, person as p
                    set d.firstname = ?, d.age = ?, d.creation_date = ?, d.dragon_character = ?, d.color = ?, d.type = ?,
                    c.x = ?, c.y = ?,
                    p.firstname = ?, p.height = ?, p.weight = ?, p.eye_color = ?, p.hair_color = ?
                    where d.id = ? and c.dragon_id = ? and p.dragon_id = ?;
                    """);

            statement.setString(1, dragon.getName());
            statement.setLong(2, dragon.getAge());
            statement.setDate(3, new Date(dragon.getCreationDate().getTime()));
            statement.setString(4, dragon.getCharacter().toString());
            statement.setString(5, dragon.getColor().name());
            statement.setString(6, dragon.getType().name());
            statement.setFloat(7, dragon.getCoordinates().getX());
            statement.setLong(8, dragon.getCoordinates().getY());
            statement.setString(9, dragon.getKiller().getName());
            statement.setDouble(10, dragon.getKiller().getHeight());
            statement.setDouble(11, dragon.getKiller().getWeight());
            statement.setString(12, dragon.getKiller().getEyeColor().name());
            statement.setString(13, dragon.getKiller().getHairColor().name());
            statement.setLong(14, dragon.getId());
            statement.setLong(15, dragon.getId());
            statement.setLong(16, dragon.getId());

            log.log(Level.INFO, """
                    Database query:
                    update dragon as d, coordinates as c, person as p
                    set d.firstname = ?, d.age = ?, d.creation_date = ?, d.dragon_character = ?, d.color = ?, d.type = ?,
                    c.x = ?, c.y = ?,
                    p.firstname = ?, p.height = ?, p.weight = ?, p.eye_color = ?, p.hair_color = ?
                    where d.id = ? and c.dragon_id = ? and p.dragon_id = ?
                    """);

            statement.executeUpdate();

        } catch (SQLException ex) {
            log.log(Level.WARNING, "Database query denied");
            throw new DataBaseException("Database query denied");
        }
    }

    @Override
    public void deleteById(long id) throws DataBaseException {
        try {
            PreparedStatement statement = conn.prepareStatement("""
                    delete from dragon
                    where dragon.id = ?;
                    """);

            statement.setLong(1, id);

            log.log(Level.INFO, """
                    Database query:
                    delete from dragon where dragon.id = ?
                    """);
            statement.execute();

        } catch (SQLException ex) {
            log.log(Level.WARNING, "Database query denied");
            throw new DataBaseException("Database query denied");
        }
    }

    @Override
    public void deleteLast() throws DataBaseException {
        try {
            PreparedStatement statement = conn.prepareStatement("""
                    select max(id) from dragon;
                    """);

            log.log(Level.INFO, """
                    Database query:
                    select max(id) from dragon
                    """);

            ResultSet results = statement.executeQuery();
            results.next();
            long id = results.getLong("max(id)");

            statement = conn.prepareStatement("""
                    delete from dragon as d
                    where d.id = ?;
                    """);

            statement.setLong(1, id);

            log.log(Level.INFO, """
                    Database query:
                    delete from dragon as d
                    where d.id = ?;
                    """);

            statement.execute();

        } catch (SQLException ex) {
            log.log(Level.WARNING, "Database query denied");
            throw new DataBaseException("Database query denied");
        }
    }


    @Override
    public void deleteAll() throws DataBaseException {
        try {
            PreparedStatement statement = conn.prepareStatement("""
                    delete from dragon;
                    """);

            log.log(Level.INFO, """
                    Database query:
                    delete from dragon
                    """);

            statement.execute();

        } catch (SQLException ex) {
            log.log(Level.WARNING, "Database query denied");
            throw new DataBaseException("Database query denied");
        }
    }
}

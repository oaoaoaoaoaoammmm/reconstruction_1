package org.example.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.example.model.Color;
import org.example.model.DragonCharacter;
import org.example.model.DragonType;

@Data
@Builder
public class Dragon implements Comparable<Dragon> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NonNull
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NonNull
    private Coordinates coordinates; //Поле не может быть null
    @NonNull
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long age; //Значение поля должно быть больше 0
    @NonNull
    private Color color; //Поле не может быть null
    @NonNull
    private Person killer; //Поле не может быть null
    @NonNull
    private DragonType type; //Поле не может быть null
    @NonNull
    private DragonCharacter character; //Поле не может быть null

    @Override
    public int compareTo(Dragon o) {
        if (this.id > o.id) return 1;
        else return 0;
    }
}

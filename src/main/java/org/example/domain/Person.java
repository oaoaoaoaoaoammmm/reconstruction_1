package org.example.domain;


import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.example.model.Color;
@Data
@Builder
public class Person {
    @NonNull
    private String name; //Поле не может быть null, Строка не может быть пустой
    private double height; //Значение поля должно быть больше 0
    @NonNull
    private Double weight; //Поле не может быть null, Значение поля должно быть больше 0
    @NonNull
    private Color eyeColor; //Поле не может быть null
    @NonNull
    private Color hairColor; //Поле не может быть null
}

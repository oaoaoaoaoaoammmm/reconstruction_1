package org.example.entity;


import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.example.entity.enums.Color;
@Data
@Builder
public class Person {
    @NonNull
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NonNull
    private double height; //Значение поля должно быть больше 0
    @NonNull
    private Double weight; //Поле может быть null, Значение поля должно быть больше 0
    @NonNull
    private Color eyeColor; //Поле не может быть null
    @NonNull
    private Color hairColor; //Поле не может быть null
}

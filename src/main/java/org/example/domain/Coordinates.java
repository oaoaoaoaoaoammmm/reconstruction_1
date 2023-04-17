package org.example.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Coordinates {
    private float x;
    @NonNull
    private Long y; //Максимальное значение поля: 902, Поле не может быть null
}

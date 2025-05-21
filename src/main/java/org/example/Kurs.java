package org.example;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@EqualsAndHashCode
@SuperBuilder(
        builderMethodName = "Builder",
        buildMethodName = "skapa",
        setterPrefix = "med"
)
public class Kurs {
    private String kursKod;
    private String namn;
}

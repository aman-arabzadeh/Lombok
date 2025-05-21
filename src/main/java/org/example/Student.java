package org.example;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Getter
@ToString
@EqualsAndHashCode
@SuperBuilder(builderMethodName = "Builder",
        buildMethodName   = "skapa",
        setterPrefix      = "med")
public class Student {
    private final String namn;
    private final int alder;

    @Builder.Default
    private final Set<Kurs> kurser = new HashSet<>();

    public Optional<Kurs> hittaKurs(String kursNamn) {
        Objects.requireNonNull(kursNamn, "kursNamn får inte vara null");
        return kurser.stream()
                .filter(k -> kursNamn.equals(k.getNamn()))
                .findFirst();
    }

    public boolean addKurs(Kurs kurs) {
        Objects.requireNonNull(kurs, "Kan inte lägga till en null-kurs.");
        return kurser.add(kurs);
    }

    public boolean removeKurs(Kurs kurs) {
        Objects.requireNonNull(kurs, "Kan inte ta bort en null-kurs.");
        return kurser.remove(kurs);
    }

    public Set<Kurs> getKurser() {
        return Collections.unmodifiableSet(kurser);
    }
}

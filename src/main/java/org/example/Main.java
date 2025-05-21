package org.example;

import java.util.Set;

public class Main {

    private static final String YES                  = "Ja";
    private static final String NO_EXISTS_OR_INVALID = "Nej, fanns redan eller ogiltig";
    private static final String NO_NOT_FOUND         = "Nej, fanns inte";
    private static final String DELIMITER            = ".".repeat(30);
    private static final String NEWLINE              = "\n";

    public static void main(String[] args) {
        var person = Person.Builder()
                .medNamn("Mona Lisa")
                .medAlder(518)
                .skapa();
        System.out.println(person);
        separator();

        var student = Student.Builder()
                .medNamn("Sara")
                .medAlder(23)
                .skapa();
        System.out.println(student);
        separator();

        // Kurser
        var matematik     = Kurs.Builder().medKursKod("MA101").medNamn("Matematik").skapa();
        var engelska      = Kurs.Builder().medKursKod("EN101").medNamn("Engelska").skapa();
        var programmering = Kurs.Builder().medKursKod("PRG231").medNamn("Programmering i Java").skapa();

        LaggTillKurs(student, matematik);
        LaggTillKurs(student, engelska);
        LaggTillKurs(student, programmering);
        System.out.println(student);
        separator();
        printKurser(student.getKurser());


        HittaKurs(student, "Matematik");
        HittaKurs(student, "Fysik"); // kurs som inte lagts till
        separator();

        TaBortKurs(student, matematik);
        System.out.println(student);
        separator();

        var fysik = Kurs.Builder().medKursKod("FY101").medNamn("Fysik").skapa();
        TaBortKurs(student, fysik);
        separator();
    }

    private static void LaggTillKurs(Student student, Kurs kurs) {
        System.out.printf("Lade till %-20s: %s%n",
                kurs.getNamn(),
                student.addKurs(kurs) ? YES : NO_EXISTS_OR_INVALID);
    }

    private static void TaBortKurs(Student student, Kurs kurs) {
        System.out.printf("Tog bort %-20s: %s%n",
                kurs.getNamn(),
                student.removeKurs(kurs) ? YES : NO_NOT_FOUND);
    }

    private static void printKurser(Set<Kurs> kurser) {
        if (kurser.isEmpty()) {
            System.out.println("Inga kurser registrerade.");
        } else {
            for (var k : kurser) {
                System.out.printf("- %-15s (%s)%n", k.getNamn(), k.getKursKod());
            }
        }
    }

    private static void HittaKurs(Student student, String kursNamn) {
        System.out.printf("Hitta kurs %-12s: ", kursNamn);
        student.hittaKurs(kursNamn)
                .ifPresentOrElse(
                        k -> System.out.printf("Hittade %s (%s)%n", k.getNamn(), k.getKursKod()),
                        () -> System.out.println("Hittades inte")
                );
    }


    private static void separator() {
        System.out.println(NEWLINE + DELIMITER + NEWLINE);
    }
}

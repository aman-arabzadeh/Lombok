import org.example.Kurs;
import org.example.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentTest {

    private Student student;

    @Mock
    private Kurs kurs;

    @BeforeEach
    void init() {
        student = Student.Builder()
                .medNamn("Alice")
                .medAlder(20)
                .skapa();
    }

    @Test
    void builderInit() {
        assertEquals("Alice", student.getNamn());
        assertEquals(20, student.getAlder());
        assertTrue(student.getKurser().isEmpty());
    }

    @Test
    void addNull() {
        assertThrows(NullPointerException.class, () -> student.addKurs(null));
    }

    @Test
    void add() {
        assertTrue(student.addKurs(kurs));
        assertTrue(student.getKurser().contains(kurs));
    }

    @Test
    void removeNull() {
        assertThrows(NullPointerException.class, () -> student.removeKurs(null));
    }

    @Test
    void remove() {
        student.addKurs(kurs);
        assertTrue(student.removeKurs(kurs));
        assertFalse(student.getKurser().contains(kurs));
    }

    @Test
    void removeNotPresent() {
        assertFalse(student.removeKurs(kurs));
    }

    @Test
    void eqAndHash() {
        Student s1 = Student.Builder()
                .medNamn("Bob")
                .medAlder(30)
                .skapa();
        Student s2 = Student.Builder()
                .medNamn("Bob")
                .medAlder(30)
                .skapa();
        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
    }
@Test
    void toStringTest() {
        String str = student.toString();
        assertTrue(str.contains("namn=Alice"));
        assertTrue(str.contains("alder=20"));
    }
}

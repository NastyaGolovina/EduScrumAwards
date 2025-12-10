import UPT_SQ.EduScrumAwards.model.Teacher;
import UPT_SQ.EduScrumAwards.model.User;
import UPT_SQ.EduScrumAwards.model.UserRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TeacherTest {
    private Teacher t1;
    private Teacher t2;
    private Teacher t4;

    @BeforeAll
    public static void onceExecutedBeforeAll() {
        System.out.println("---Test Teacher Class---");
    }

    @BeforeEach
    public void setup() {
        t1 = new Teacher();

        t2 = new Teacher(
                "Professor Almeida",
                "prof.almeida",
                "prof123"
        );
    }

    @Test
    public void testCreateTeacher() {
        assertNull(t4);
        assertNotNull(t1);
        assertNotNull(t2);
    }

    @Test
    public void testTeacherFields() {
        assertEquals("Professor Almeida", t2.getName());
        assertEquals("prof.almeida", t2.getLogin());
        assertEquals("prof123", t2.getPassword());
        assertEquals(UserRole.TEACHER, t2.getRole());
        assertNull(t2.getUserId());
    }

    @Test
    public void testTeacherSets() {
        t1.setUserId(1L);
        t1.setName("Novo Professor");
        t1.setLogin("novo.prof");
        t1.setPassword("novaSenha");

        assertEquals(1L, t1.getUserId());
        assertEquals("Novo Professor", t1.getName());
        assertEquals("novo.prof", t1.getLogin());
        assertEquals("novaSenha", t1.getPassword());
        assertEquals(UserRole.TEACHER, t1.getRole());
    }

    @Test
    public void testTeacherRoleAutomaticallySet() {
        Teacher newTeacher = new Teacher("Novo Professor", "novo.prof", "senha");
        assertEquals(UserRole.TEACHER, newTeacher.getRole());

        Teacher defaultTeacher = new Teacher();
        assertEquals(UserRole.TEACHER, defaultTeacher.getRole());
    }

//    @Test
//    public void testGenerateDashboard() {
//        assertDoesNotThrow(() -> t2.generateDashboard());
//    }

    @Test
    public void testTeacherToString() {
        t2.setUserId(1L);
        String toStringResult = t2.toString();

        assertTrue(toStringResult.contains("Teacher{"));
        assertTrue(toStringResult.contains("userId=1"));
        assertTrue(toStringResult.contains("name='Professor Almeida'"));
        assertTrue(toStringResult.contains("login='prof.almeida'"));
    }

    @Test
    public void testTeacherInheritance() {
        assertTrue(t2 instanceof User);

        User user = t2;
        assertEquals("Professor Almeida", user.getName());
        assertEquals(UserRole.TEACHER, user.getRole());
    }
}

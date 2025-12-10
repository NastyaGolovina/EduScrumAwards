import UPT_SQ.EduScrumAwards.model.Student;
import UPT_SQ.EduScrumAwards.model.UserRole;
import UPT_SQ.EduScrumAwards.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    private Student s1;
    private Student s2;
    private Student s3;
    private Student s4;

    @BeforeAll
    public static void onceExecutedBeforeAll() {
        System.out.println("---Test Student Class---");
    }

    @BeforeEach
    public void setup() {
        s1 = new Student();

        s2 = new Student(
                "Ana Costa",
                "ana.costa",
                "senha456"
        );

        s3 = new Student(
                "Carlos Souza",
                "carlos.souza",
                "senha789",
                "2023001",
                3
        );
    }

    @Test
    public void testCreateStudent() {
        assertNull(s4);
        assertNotNull(s1);
        assertNotNull(s2);
        assertNotNull(s3);
    }

    @Test
    public void testStudentFields() {
        assertEquals("Carlos Souza", s3.getName());
        assertEquals("carlos.souza", s3.getLogin());
        assertEquals("senha789", s3.getPassword());
        assertEquals(UserRole.STUDENT, s3.getRole());
        assertEquals("2023001", s3.getStudentNumber());
        assertEquals(3, s3.getCurrentSemester());
        assertEquals(0, s3.getTotalScore());
        assertNull(s3.getUserId());
    }

    @Test
    public void testStudentBasicConstructor() {
        assertEquals("Ana Costa", s2.getName());
        assertEquals("ana.costa", s2.getLogin());
        assertEquals(UserRole.STUDENT, s2.getRole());
        assertNull(s2.getStudentNumber());
        assertNull(s2.getCurrentSemester());
        assertEquals(0, s2.getTotalScore());
    }

    @Test
    public void testStudentSets() {
        s1.setUserId(1L);
        s1.setName("Novo Estudante");
        s1.setLogin("novo.est");
        s1.setPassword("novaSenha");
        s1.setStudentNumber("2023002");
        s1.setCurrentSemester(4);
        s1.setTotalScore(150);

        assertEquals(1L, s1.getUserId());
        assertEquals("Novo Estudante", s1.getName());
        assertEquals("novo.est", s1.getLogin());
        assertEquals("novaSenha", s1.getPassword());
        assertEquals("2023002", s1.getStudentNumber());
        assertEquals(4, s1.getCurrentSemester());
        assertEquals(150, s1.getTotalScore());
        assertEquals(UserRole.STUDENT, s1.getRole());
    }

    @Test
    public void testCalculateTotalScore() {
        s3.setTotalScore(200);
        Integer score = s3.calculateTotalScore();

        assertEquals(200, score);
    }

//    @Test
//    public void testGetStudentAwards() {
//        assertNotNull(s3.getStudentAwards());
//        assertTrue(s3.getStudentAwards().isEmpty());
//    }

    @Test
    public void testStudentToString() {
        s3.setUserId(1L);
        s3.setTotalScore(100);

        String toStringResult = s3.toString();

        assertTrue(toStringResult.contains("Student{"));
        assertTrue(toStringResult.contains("userId=1"));
        assertTrue(toStringResult.contains("name='Carlos Souza'"));
        assertTrue(toStringResult.contains("login='carlos.souza'"));
        assertTrue(toStringResult.contains("studentNumber='2023001'"));
        assertTrue(toStringResult.contains("currentSemester=3"));
        assertTrue(toStringResult.contains("totalScore=100"));
    }

    @Test
    public void testStudentInheritance() {
        assertTrue(s3 instanceof User);

        User user = s3;
        assertEquals("Carlos Souza", user.getName());
        assertEquals(UserRole.STUDENT, user.getRole());
    }

    @Test
    public void testStudentRoleAutomaticallySet() {
        Student newStudent = new Student("Novo Estudante", "novo.est", "senha");
        assertEquals(UserRole.STUDENT, newStudent.getRole());

        Student defaultStudent = new Student();
        assertEquals(UserRole.STUDENT, defaultStudent.getRole());
    }
}

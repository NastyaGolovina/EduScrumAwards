

import UPT_SQ.EduScrumAwards.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class StudentAwardTest {

    private StudentAward sa1;
    private StudentAward sa2;
    private StudentAward sa4;

    @BeforeAll
    public static void onceExecutedBeforeAll() {
        System.out.println("Test StudentAward Class");
    }

    @BeforeEach
    public void setup() {
        sa1 = new StudentAward();

        Award award = new Award();
        Student student = new Student();
        Teacher teacher = new Teacher();
        Team team = new Team();
        Project project = new Project();
        Date date = new Date();

        sa2 = new StudentAward(
                award,
                student,
                teacher,
                project,
                team,
                date,
                50
        );

    }

    @Test
    public void testCreateStudentAward() {
        assertNull(sa4);
        assertNotNull(sa1);
        assertNotNull(sa2);
    }

    @Test
    public void testStudentAwardFields() {
        assertNotNull(sa2.getAward());
        assertNotNull(sa2.getStudent());
        assertNotNull(sa2.getTeacher());
        assertNotNull(sa2.getProject());
        assertNotNull(sa2.getTeam());
        assertNotNull(sa2.getDate());
        assertEquals(50, sa2.getPoints());
    }

    @Test
    public void testStudentAwardSets() {
        Award award = new Award();
        Student student = new Student();
        Teacher teacher = new Teacher();
        Project project = new Project();
        Team team = new Team();
        Date date = new Date();

        sa1.setStudentAwardId(1);
        sa1.setAward(award);
        sa1.setStudent(student);
        sa1.setTeacher(teacher);
        sa1.setProject(project);
        sa1.setTeam(team);
        sa1.setDate(date);
        sa1.setPoints(75);

        assertEquals(1, sa1.getStudentAwardId());
        assertEquals(award, sa1.getAward());
        assertEquals(student, sa1.getStudent());
        assertEquals(teacher, sa1.getTeacher());
        assertEquals(project, sa1.getProject());
        assertEquals(team, sa1.getTeam());
        assertEquals(date, sa1.getDate());
        assertEquals(75, sa1.getPoints());
    }
}

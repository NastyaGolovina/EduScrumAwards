

import UPT_SQ.EduScrumAwards.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
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


    @Test
    public void testStudentAwardSprint() {
        Sprint sprint = new Sprint();
        AwardRule rule = new AwardRule();


        StudentAward saWithSprint = new StudentAward(
                new Award(),
                new Student(),
                new Teacher(),
                new Project(),
                new Team(),
                new Date(),
                100,
                sprint,
                rule
        );

        assertNotNull(saWithSprint.getSprint());
        assertEquals(sprint, saWithSprint.getSprint());

        assertNotNull(saWithSprint.getRule());
        assertEquals(rule, saWithSprint.getRule());


        sa1.setSprint(sprint);
        assertEquals(sprint, sa1.getSprint());

        sa1.setRule(rule);
        assertEquals(rule, sa1.getRule());
    }

    @Test
    public void testToString() {
        StudentAward sa = new StudentAward();
        sa.setStudentAwardId(5);

        String output = sa.toString();

        assertTrue(output.contains("studentAwardId=5"));
        assertTrue(output.contains("StudentAward{"));

    }

    @Test
    void testToString_AllNonNull() {
        StudentAward sa = new StudentAward();
        sa.setStudentAwardId(1);

        Award award = new Award();
        award.setAwardName("Best Project");
        sa.setAward(award);

        Student student = new Student();
        student.setUserId(101L);
        sa.setStudent(student);

        Teacher teacher = new Teacher();
        teacher.setUserId(201L);
        sa.setTeacher(teacher);

        Project project = new Project();
        project.setProjectName("AI Project");
        sa.setProject(project);

        Team team = new Team();
        team.setTeamName("Team A");
        sa.setTeam(team);

        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.DECEMBER, 11);
        sa.setDate(cal.getTime());
        sa.setPoints(95);

        String result = sa.toString();
        assertTrue(result.contains("student=101"));
        assertTrue(result.contains("teacher=201"));
        assertTrue(result.contains("project=AI Project"));
        assertTrue(result.contains("team=Team A"));
        assertTrue(result.contains("points=95"));
    }


}

import UPT_SQ.EduScrumAwards.model.Student;
import UPT_SQ.EduScrumAwards.model.Team;
import UPT_SQ.EduScrumAwards.model.TeamMember;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TeamMemberTest {
    @Test
    void testConstructorWithNameAndRole() {
        TeamMember tm = new TeamMember("Alice", TeamMember.Role.DEVELOPER);

        assertEquals("Alice", tm.getName());
        assertEquals(TeamMember.Role.DEVELOPER, tm.getRole());
        assertNull(tm.getStudent());  // not assigned
        assertNull(tm.getTeam());     // not assigned
    }

    @Test
    void testConstructorWithStudentAndTeam() {
        Student student = new Student("Bob", "bob@login", "pass123");
        Team team = new Team("Team Rocket");

        TeamMember tm = new TeamMember("Bob", TeamMember.Role.SCRUM_MASTER, student, team);

        assertEquals("Bob", tm.getName());
        assertEquals(TeamMember.Role.SCRUM_MASTER, tm.getRole());
        assertEquals(student, tm.getStudent());
        assertEquals(team, tm.getTeam());
    }

    @Test
    void testFullConstructor() {
        Student s = new Student("Eve", "eve@login", "pass123");
        Team t = new Team("Avengers");

        TeamMember tm = new TeamMember(10, "Eve", TeamMember.Role.QA_ENGINEER, s, t);

        assertEquals(10, tm.getTeamMemberId());
        assertEquals("Eve", tm.getName());
        assertEquals(TeamMember.Role.QA_ENGINEER, tm.getRole());
        assertEquals(s, tm.getStudent());
        assertEquals(t, tm.getTeam());
    }

    @Test
    void testSettersAndGetters() {
        TeamMember tm = new TeamMember();

        Student s = new Student("Tom", "tom@login", "pass");
        Team t = new Team("Team A");

        tm.setName("Tom");
        tm.setRole(TeamMember.Role.PRODUCT_OWNER);
        tm.setStudent(s);
        tm.setTeam(t);

        assertEquals("Tom", tm.getName());
        assertEquals(TeamMember.Role.PRODUCT_OWNER, tm.getRole());
        assertEquals(s, tm.getStudent());
        assertEquals(t, tm.getTeam());
    }

    @Test
    void testToString() {
        Student s = new Student("Ana", "ana@login", "123");
        Team t = new Team("Team B");
        TeamMember tm = new TeamMember(1, "Ana", TeamMember.Role.DEVELOPER, s, t);

        String output = tm.toString();

        assertTrue(output.contains("Ana"));
        assertTrue(output.contains("DEVELOPER"));
        assertTrue(output.contains("1"));
    }
}

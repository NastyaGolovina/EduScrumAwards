
import UPT_SQ.EduScrumAwards.model.Student;
import UPT_SQ.EduScrumAwards.model.StudentAward;
import UPT_SQ.EduScrumAwards.model.Team;
import UPT_SQ.EduScrumAwards.model.TeamMember;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class TeamTest {

    /* Constructor */
    @Test
    void testEmptyConstructor() {
        Team team = new Team();
        assertNotNull(team.getTeamMember());
        assertTrue(team.getTeamMember().isEmpty());
    }

    @Test
    void testConstructorWithName() {
        Team team = new Team("Alpha");
        assertEquals("Alpha", team.getTeamName());
        assertNotNull(team.getTeamMember());
        assertTrue(team.getTeamMember().isEmpty());
    }

    @Test
    void testFullConstructor() {
        TeamMember tm = new TeamMember("Alice", TeamMember.Role.DEVELOPER);
        ArrayList<TeamMember> list = new ArrayList<>();
        list.add(tm);

        Team team = new Team(10, "Beta", list);

        assertEquals(10, team.getTeamID());
        assertEquals("Beta", team.getTeamName());
        assertEquals(1, team.getTeamMember().size());
    }

    /* Getter & Setter Tests */

    @Test
    void testSetAndGetTeamID() {
        Team team = new Team();
        team.setTeamID(5);
        assertEquals(5, team.getTeamID());
    }

    @Test
    void testSetAndGetTeamName() {
        Team team = new Team();
        team.setTeamName("Gamma");
        assertEquals("Gamma", team.getTeamName());
    }

    @Test
    void testSetAndGetTeamMemberList() {
        Team team = new Team();
        TeamMember tm = new TeamMember("Bob", TeamMember.Role.QA_ENGINEER);

        ArrayList<TeamMember> list = new ArrayList<>();
        list.add(tm);

        team.setTeamMember(list);

        assertEquals(1, team.getTeamMember().size());
        assertEquals("Bob", team.getTeamMember().get(0).getName());
    }

    /* searchTeamMember() Tests */

    @Test
    void testSearchTeamMemberFound() {
        Team team = new Team("Alpha");

        TeamMember tm1 = new TeamMember(1, "Alice", TeamMember.Role.DEVELOPER, null, team);
        TeamMember tm2 = new TeamMember(2, "Bob", TeamMember.Role.SCRUM_MASTER, null, team);

        team.getTeamMember().add(tm1);
        team.getTeamMember().add(tm2);

        TeamMember result = team.searchTeamMember(2);
        assertNotNull(result);
        assertEquals("Bob", result.getName());
    }

    @Test
    void testSearchTeamMemberNotFound() {
        Team team = new Team("Alpha");
        assertNull(team.searchTeamMember(99));
    }

    /* isTeamMember() Tests */

    @Test
    void testIsTeamMemberFound() {
        Team team = new Team("Zeta");

        Student s = new Student("John", "john123", "pwd");
        s.setUserId(100L);

        TeamMember tm = new TeamMember("John", TeamMember.Role.PRODUCT_OWNER, s, team);
        team.getTeamMember().add(tm);

        TeamMember result = team.isTeamMember(100L);
        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    @Test
    void testIsTeamMemberNotFound() {
        Team team = new Team("Zeta");
        assertNull(team.isTeamMember(999L));
    }

    /* ---------------------------------------------------------
       earnAward() Tests (NO DB — mock via in-memory)
       --------------------------------------------------------- */

    @Test
    void testEarnAwardSumCorrect() {
        Team team = new Team();
        team.setTeamID(1);

        // Mock Student
        Student s = new Student("A", "a", "pwd");
        s.setUserId(10L);

        // Mock awards list (we bypass DB by overriding team.studentsAwards())
        StudentAward sa1 = new StudentAward();
        sa1.setPoints(10);
        sa1.setTeam(team);

        StudentAward sa2 = new StudentAward();
        sa2.setPoints(15);
        sa2.setTeam(team);

        // Inject fake award list via subclass trick
        Team teamSpy = new Team() {
            @Override
            public ArrayList<StudentAward> studentsAwards() {
                ArrayList<StudentAward> list = new ArrayList<>();
                list.add(sa1);
                list.add(sa2);
                return list;
            }
        };
        teamSpy.setTeamID(1);

        assertEquals(25, teamSpy.earnAward());
    }

    @Test
    void testEarnAwardNoAwards() {
        Team teamSpy = new Team() {
            @Override
            public ArrayList<StudentAward> studentsAwards() {
                return new ArrayList<>();
            }
        };

        assertEquals(0, teamSpy.earnAward());
    }
}

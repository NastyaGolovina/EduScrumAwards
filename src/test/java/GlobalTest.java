import UPT_SQ.EduScrumAwards.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GlobalTest {
    private Global global;

    @BeforeEach
    void setUp() {
        global = new Global();
    }

    @Test
    void testSearchAward_Found() {
        Award award1 = new Award();
        award1.setAwardID(1);
        Award award2 = new Award();
        award2.setAwardID(2);

        global.getAwards().add(award1);
        global.getAwards().add(award2);

        Award found = global.searchAward(2);
        assertNotNull(found);
        assertEquals(2, found.getAwardID());
    }

    @Test
    void testSearchAward_NotFound() {
        Award award = new Award();
        award.setAwardID(1);
        global.getAwards().add(award);

        Award result = global.searchAward(5);
        assertNull(result);
    }

    @Test
    void testSearchUser_Found() {
        User user1 = new User();
        user1.setUserId(100L);
        User user2 = new User();
        user2.setUserId(200L);

        global.getUsers().add(user1);
        global.getUsers().add(user2);

        User found = global.searchUser(200);
        assertNotNull(found);
        assertEquals(200, found.getUserId());
    }

    @Test
    void testSearchUser_NotFound() {
        User user = new User();
        user.setUserId(999L);
        global.getUsers().add(user);

        User result = global.searchUser(123);
        assertNull(result);
    }

    @Test
    void testSearchStudentAward_Found() {
        StudentAward sa1 = new StudentAward();
        sa1.setStudentAwardId(10);
        StudentAward sa2 = new StudentAward();
        sa2.setStudentAwardId(20);

        global.getStudentsAwards().add(sa1);
        global.getStudentsAwards().add(sa2);

        StudentAward found = global.searchStudentAward(10);
        assertNotNull(found);
        assertEquals(10, found.getStudentAwardId());
    }

    @Test
    void testSearchStudentAward_NotFound() {
        StudentAward sa = new StudentAward();
        sa.setStudentAwardId(1);
        global.getStudentsAwards().add(sa);

        StudentAward result = global.searchStudentAward(99);
        assertNull(result);
    }

    @Test
    void testSearchTeamFound() {
        Global global = new Global();
        global.setTeams(new ArrayList<>());

        Team team1 = new Team(1, "Alpha", new ArrayList<>());
        Team team2 = new Team(2, "Beta", new ArrayList<>());
        Team team3 = new Team(3, "Gamma", new ArrayList<>());

        global.getTeams().add(team1);
        global.getTeams().add(team2);
        global.getTeams().add(team3);

        Team result = global.searchTeam(2);

        assertNotNull(result);
        assertEquals("Beta", result.getTeamName());
        assertEquals(2, result.getTeamID());
    }

    @Test
    void testSearchTeamNotFound() {
        Global global = new Global();
        global.setTeams(new ArrayList<>());

        Team team1 = new Team(1, "Alpha", new ArrayList<>());
        Team team2 = new Team(2, "Beta", new ArrayList<>());

        global.getTeams().add(team1);
        global.getTeams().add(team2);

        Team result = global.searchTeam(99);
        assertNull(result);
    }

    @Test
    void testSearchTeamEmptyList() {
        Global global = new Global();
        global.setTeams(new ArrayList<>());

        Team result = global.searchTeam(1);
        assertNull(result);
    }
}

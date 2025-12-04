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

    @Test
    void testFindProject_Found() {
        Course course = new Course();
        Project project1 = new Project();
        project1.setProjectId(101);
        Project project2 = new Project();
        project2.setProjectId(102);

        course.setProjects(new ArrayList<>());
        course.getProjects().add(project1);
        course.getProjects().add(project2);

        global.getCourses().add(course);

        Project found = global.findProject(102);
        assertNotNull(found);
        assertEquals(102, found.getProjectId());
    }

    @Test
    void testFindProject_NotFound() {
        Course course = new Course();
        Project project = new Project();
        project.setProjectId(201);

        course.setProjects(new ArrayList<>());
        course.getProjects().add(project);

        global.getCourses().add(course);

        Project result = global.findProject(999);
        assertNull(result);
    }

    @Test
    void testFindProjectByTeamId_Found() {
        Team team = new Team();
        team.setTeamID(301);

        Project project = new Project();
        project.setTeam(team);

        Course course = new Course();
        course.setProjects(new ArrayList<>());
        course.getProjects().add(project);

        global.getCourses().add(course);

        Project found = global.findProjectByTeamId(301);
        assertNotNull(found);
        assertEquals(301, found.getTeam().getTeamID());
    }

    @Test
    void testFindProjectByTeamId_NotFound() {
        Team team = new Team();
        team.setTeamID(401);

        Project project = new Project();
        project.setTeam(team);

        Course course = new Course();
        course.setProjects(new ArrayList<>());
        course.getProjects().add(project);

        global.getCourses().add(course);

        Project result = global.findProjectByTeamId(999);
        assertNull(result);
    }

    @Test
    void testFindProjectByTeamId_CourseWithNoProjects() {
        Course course = new Course();
        course.setProjects(new ArrayList<>());
        global.getCourses().add(course);

        Project result = global.findProjectByTeamId(1);
        assertNull(result);
    }

    @Test
    void testFindProjectByTeamId_ProjectWithNullTeam() {
        Project project = new Project();
        project.setTeam(null);

        Course course = new Course();
        course.setProjects(new ArrayList<>());
        course.getProjects().add(project);

        global.getCourses().add(course);

        Project result = global.findProjectByTeamId(1);
        assertNull(result);
    }


    @Test
    void testIsAwardInStudentAwards_Found() {
        Award award = new Award();
        award.setAwardID(1);

        StudentAward sa = new StudentAward();
        sa.setAward(award);

        global.getStudentsAwards().add(sa);

        assertTrue(global.isAwardInStudentAwards(1));
    }

    @Test
    void testIsAwardInStudentAwards_NotFound() {
        Award award = new Award();
        award.setAwardID(2);

        StudentAward sa = new StudentAward();
        sa.setAward(award);

        global.getStudentsAwards().add(sa);

        assertFalse(global.isAwardInStudentAwards(1));
    }

    @Test
    void testIsAwardInStudentAwards_EmptyList() {
        assertFalse(global.isAwardInStudentAwards(1));
    }


    @Test
    void testCreateTeam_NameNull() {
        String result = global.createTeam(null);

        assertEquals("ERROR: Name is empty!", result);
        assertTrue(global.getTeams().isEmpty());
    }

    @Test
    void testCreateTeam_NameEmpty() {
        String result = global.createTeam("");

        assertEquals("ERROR: Name is empty!", result);
        assertTrue(global.getTeams().isEmpty());
    }

    @Test
    void testCreateTeam_DuplicateName() {
        // Prepare existing team
        Team team = new Team(1, "Alpha", new ArrayList<>());
        global.getTeams().add(team);

        // Try to create with same name (case-insensitive)
        String result = global.createTeam("alpha");

        assertEquals("ERROR: Team name already exists!", result);
        assertEquals(1, global.getTeams().size());
    }

    /**
     * This one WILL hit the database part.
     * It assumes your Hibernate test config is correctly set up.
     * If you don't have DB ready yet, you can temporarily disable it.
     */
    @Test
    void testCreateTeam_Success() {
        String result = global.createTeam("New Team");

        assertEquals("Success", result);
        assertEquals(1, global.getTeams().size());
        assertEquals("New Team", global.getTeams().get(0).getTeamName());
    }

    @Test
    void testUpdateTeam_TeamNotFound() {
        // no teams in list
        String result = global.updateTeam(99, "New Name");

        assertEquals("ERROR: Team with this id does not exist", result);
    }

    @Test
    void testUpdateTeam_NameNull() {
        Team team = new Team(1, "Alpha", new ArrayList<>());
        global.getTeams().add(team);

        String result = global.updateTeam(1, null);

        assertEquals("ERROR: Name is empty!", result);
        assertEquals("Alpha", team.getTeamName());
    }

    @Test
    void testUpdateTeam_NameEmpty() {
        Team team = new Team(1, "Alpha", new ArrayList<>());
        global.getTeams().add(team);

        String result = global.updateTeam(1, "");

        assertEquals("ERROR: Name is empty!", result);
        assertEquals("Alpha", team.getTeamName());
    }
    @Test
    void testIsTeamInStudentAwards_Found() {
        Global global = new Global();
        global.setStudentsAwards(new ArrayList<>());

        Team team = new Team();
        team.setTeamID(1);

        StudentAward sa = new StudentAward();
        sa.setTeam(team);

        global.getStudentsAwards().add(sa);

        assertTrue(global.isTeamInStudentAwards(1));
    }

    @Test
    void testDeleteTeam_TeamNotFound() {
        String result = global.deleteTeam(123);

        assertEquals("ERROR: Team not found", result);
    }

}


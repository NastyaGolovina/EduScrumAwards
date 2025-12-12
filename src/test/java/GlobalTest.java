import UPT_SQ.EduScrumAwards.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GlobalTest {
    private Global global;

    @BeforeEach
    void setUp() {
        global = new Global();
    }


    @Test
    void testInitialListsNotNull() {
        assertNotNull(global.getUsers());
        assertNotNull(global.getCourses());
        assertNotNull(global.getTeams());
        assertNotNull(global.getAwards());
        assertNotNull(global.getStudentsAwards());
    }

    @Test
    void testSetAndGetUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        global.setUsers(users);

        assertEquals(users, global.getUsers());
        assertEquals(1, global.getUsers().size());
    }

    @Test
    void testSetAndGetCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course());
        global.setCourses(courses);

        assertEquals(courses, global.getCourses());
        assertEquals(1, global.getCourses().size());
    }

    @Test
    void testSetAndGetTeams() {
        ArrayList<Team> teams = new ArrayList<>();
        teams.add(new Team());
        global.setTeams(teams);

        assertEquals(teams, global.getTeams());
        assertEquals(1, global.getTeams().size());
    }

    @Test
    void testSetAndGetAwards() {
        ArrayList<Award> awards = new ArrayList<>();
        awards.add(new Award());
        global.setAwards(awards);

        assertEquals(awards, global.getAwards());
        assertEquals(1, global.getAwards().size());
    }

    @Test
    void testSetAndGetStudentsAwards() {
        ArrayList<StudentAward> sa = new ArrayList<>();
        sa.add(new StudentAward());
        global.setStudentsAwards(sa);

        assertEquals(sa, global.getStudentsAwards());
        assertEquals(1, global.getStudentsAwards().size());
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
    void findTeamByName_returnsTeam_caseInsensitive() {
        Global g = new Global();

        Team t1 = new Team("Alpha");
        t1.setTeamID(1);
        Team t2 = new Team("Beta Squad");
        t2.setTeamID(2);

        // <<< no reflection, no setTeams >>>
        g.getTeams().clear();         // make sure list is empty
        g.getTeams().add(t1);
        g.getTeams().add(t2);

        Team result = g.findTeamByName("beta squad");
        assertNotNull(result);
        assertEquals(2, result.getTeamID());
        assertEquals("Beta Squad", result.getTeamName());
    }

    @Test
    void findTeamByName_returnsNull_whenNotFound() {
        Global g = new Global();

        Team t1 = new Team("Alpha");
        t1.setTeamID(1);

        g.getTeams().clear();
        g.getTeams().add(t1);

        Team result = g.findTeamByName("Delta");

        assertNull(result);
    }

    @Test
    void isTeamInStudentAwards_returnsTrue_whenAwardForTeamExists() {
        Global g = new Global();

        Team team = new Team("Gamma");
        team.setTeamID(5);

        Team other = new Team("Other");
        other.setTeamID(99);

        StudentAward sa1 = new StudentAward();
        sa1.setTeam(team);
        sa1.setPoints(10);

        StudentAward sa2 = new StudentAward();
        sa2.setTeam(other);
        sa2.setPoints(20);

        g.getStudentsAwards().clear();
        g.getStudentsAwards().add(sa1);
        g.getStudentsAwards().add(sa2);

        assertTrue(g.isTeamInStudentAwards(5));
    }

    @Test
    void isTeamInStudentAwards_returnsFalse_whenNoAwardForTeam() {
        Global g = new Global();

        Team other = new Team("Other");
        other.setTeamID(99);

        StudentAward sa = new StudentAward();
        sa.setTeam(other);
        sa.setPoints(20);

        g.getStudentsAwards().clear();
        g.getStudentsAwards().add(sa);

        assertFalse(g.isTeamInStudentAwards(1));
    }

    @Test
    void isTeamInStudentAwards_returnsFalse_whenListEmpty() {
        Global g = new Global();

        // ensure list is empty
        g.getStudentsAwards().clear();

        assertFalse(g.isTeamInStudentAwards(1));
    }

    @Test
    void isTeamInStudentAwards_handlesAwardWithNullTeam() {
        Global g = new Global();

        // StudentAward present, but its team is null
        StudentAward sa = new StudentAward();
        sa.setTeam(null);

        g.getStudentsAwards().clear();
        g.getStudentsAwards().add(sa);   // IMPORTANT: award is NOT null, only its team is null

        assertFalse(g.isTeamInStudentAwards(1));
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


//    @Test
//    void testCreateTeam_NameNull() {
//        String result = global.createTeam(null);
//
//        assertEquals("ERROR: Name is empty!", result);
//        assertTrue(global.getTeams().isEmpty());
//    }
//
//    @Test
//    void testCreateTeam_NameEmpty() {
//        String result = global.createTeam("");
//
//        assertEquals("ERROR: Name is empty!", result);
//        assertTrue(global.getTeams().isEmpty());
//    }

//    @Test
//    void testCreateTeam_DuplicateName() {
//        // Prepare existing team
//        Team team = new Team(1, "Alpha", new ArrayList<>());
//        global.getTeams().add(team);
//
//        // Try to create with same name (case-insensitive)
//        String result = global.createTeam("alpha");
//
//        assertEquals("ERROR: Team name already exists!", result);
//        assertEquals(1, global.getTeams().size());
//    }

    /**
     * This one WILL hit the database part.
     * It assumes your Hibernate test config is correctly set up.
     * If you don't have DB ready yet, you can temporarily disable it.
     */
//    @Test
//    void testCreateTeam_Success() {
//        String result = global.createTeam("New Team");
//
//        assertEquals("Success", result);
//        assertEquals(1, global.getTeams().size());
//        assertEquals("New Team", global.getTeams().get(0).getTeamName());
//    }
//
//    @Test
//    void testUpdateTeam_TeamNotFound() {
//        // no teams in list
//        String result = global.updateTeam(99, "New Name");
//
//        assertEquals("ERROR: Team with this id does not exist", result);
//    }
//
//    @Test
//    void testUpdateTeam_NameNull() {
//        Team team = new Team(1, "Alpha", new ArrayList<>());
//        global.getTeams().add(team);
//
//        String result = global.updateTeam(1, null);
//
//        assertEquals("ERROR: Name is empty!", result);
//        assertEquals("Alpha", team.getTeamName());
//    }
//
//    @Test
//    void testUpdateTeam_NameEmpty() {
//        Team team = new Team(1, "Alpha", new ArrayList<>());
//        global.getTeams().add(team);
//
//        String result = global.updateTeam(1, "");
//
//        assertEquals("ERROR: Name is empty!", result);
//        assertEquals("Alpha", team.getTeamName());
//    }
//    @Test
//    void testIsTeamInStudentAwards_Found() {
//        Global global = new Global();
//        global.setStudentsAwards(new ArrayList<>());
//
//        Team team = new Team();
//        team.setTeamID(1);
//
//        StudentAward sa = new StudentAward();
//        sa.setTeam(team);
//
//        global.getStudentsAwards().add(sa);
//
//        assertTrue(global.isTeamInStudentAwards(1));
//    }
//
//    @Test
//    void testDeleteTeam_TeamNotFound() {
//        String result = global.deleteTeam(123);
//
//        assertEquals("ERROR: Team not found", result);
//    }



    @Test
    void testGetCourseStudentPointValue_AllBranches() {
        Student student = new Student();
        student.setUserId(100L);

        Course course = new Course();
        course.setCourseID(1);
        Project project = new Project();
        project.setCourse(course);

        Global g =  new Global();
        g.setStudentsAwards(new ArrayList<>());

        // Another student and another course → should not be taken into account
        StudentAward award1 = new StudentAward();
        award1.setPoints(30);

        Student student1 = new Student();
        student1.setUserId(200L);
        award1.setStudent(student1);

        Course course2 = new Course();
        course2.setCourseID(2);
        Project project2 = new Project();
        project2.setCourse(course2);
        award1.setProject(project2);

        // The student matches, but the course is different → not taken into account
        StudentAward award2 = new StudentAward();
        award2.setPoints(40);

        award2.setStudent(student);
        award2.setProject(project2);

        // Same course, but different student → not taken into account
        StudentAward award3 = new StudentAward();
        award3.setPoints(50);
        award3.setStudent(student1);
        award3.setProject(project);

        // The student and course match → taken into account
        StudentAward award4 = new StudentAward();
        award4.setPoints(60);
        award4.setStudent(student);
        award4.setProject(project);

        g.getStudentsAwards().add(award1);
        g.getStudentsAwards().add(award2);
        g.getStudentsAwards().add(award3);
        g.getStudentsAwards().add(award4);

        int result = g.getCourseStudentPointValue(1, 100L);
        assertEquals(60, result); // Only award4 is taken into account
    }



}


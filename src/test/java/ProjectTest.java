import UPT_SQ.EduScrumAwards.model.Course;
import UPT_SQ.EduScrumAwards.model.Project;
import UPT_SQ.EduScrumAwards.model.Sprint;
import UPT_SQ.EduScrumAwards.model.Team;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectTest {
    @Test
    public void testConstructorAndGetters() {
        Team team = new Team();
        Course course = new Course();
        List<Sprint>sprints = new ArrayList<>();

        Project p = new Project(1, "AI Project", team, course, sprints);

        assertEquals(1, p.getProjectId());
        assertEquals("AI Project", p.getProjectName());
        assertEquals(team, p.getTeam());
        assertEquals(course, p.getCourse());
        assertEquals(sprints, p.getSprints());
    }

    @Test
    public void testSetters() {
        Project p = new Project();

        Team team = new Team();
        Course course = new Course();
        List<Sprint> sList = new ArrayList<>();

        p.setProjectId(5);
        p.setProjectName("Web App");
        p.setTeam(team);
        p.setCourse(course);
        p.setSprints(sList);

        assertEquals(5, p.getProjectId());
        assertEquals("Web App", p.getProjectName());
        assertEquals(team, p.getTeam());
        assertEquals(course, p.getCourse());
        assertEquals(sList, p.getSprints());
    }

    @Test
    public void testSearchSprint() {
        Project p = new Project();
        List<Sprint> slist = new ArrayList<>();

        Sprint s1 = new Sprint();
        s1.setSprintId(1);
        Sprint s2 = new Sprint();
        s2.setSprintId(2);

        slist.add(s1);
        slist.add(s2);

        p.setSprints(slist);

        assertEquals(s1, p.searchSprint(1));
        assertEquals(s2, p.searchSprint(2));
    }

    @Test
    public void testToString() {
        Project p = new Project();
        p.setProjectId(1);
        p.setProjectName("TestProject");
        p.setSprints(new ArrayList<>());

        String text = p.toString();

        assertTrue(text.contains("TestProject"));
        assertTrue(text.contains("projectId=1"));
        assertTrue(text.contains("sprints=[]"));
    }

}

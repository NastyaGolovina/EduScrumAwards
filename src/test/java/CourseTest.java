import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import UPT_SQ.EduScrumAwards.model.*;

public class CourseTest {

    @Test
    void gettersAndSetters_work() {
        Course c = new Course();
        c.setCourseID(101);
        c.setCourseName("Software Quality");

        List<CourseTeacher> teachers = new ArrayList<>();
        List<Project> projects = new ArrayList<>();
        c.setTeachers((List<CourseTeacher>) teachers);
        c.setProjects((List<Project>) projects);

        assertEquals(101, c.getCourseID());
        assertEquals("Software Quality", c.getCourseName());
        assertSame(teachers, c.getTeachers());
        assertSame(projects, c.getProjects());
    }

    @Test
    void findCourseTeacherById_nullList_returnsNull() {
        Course c = new Course();
        assertNull(c.findCourseTeacherById(1));
    }

    @Test
    void findCourseTeacherByName_caseInsensitive_trim() {
        Course c = new Course();
        Teacher t1 = new Teacher();
        t1.setName("  John DOE  ");
        CourseTeacher ct1 = new CourseTeacher(c, t1, true);

        Teacher t2 = new Teacher();
        t2.setName("Alice");
        CourseTeacher ct2 = new CourseTeacher(c, t2, false);

        List<CourseTeacher> list = new ArrayList<>();
        list.add(ct1);
        list.add(ct2);
        c.setTeachers(list);

        assertSame(ct1, c.findCourseTeacherByName("john doe"));
        assertSame(ct1, c.findCourseTeacherByName("  JOHN DOE"));
        assertNull(c.findCourseTeacherByName("Bob"));
        assertNull(c.findCourseTeacherByName(null));
    }

    @Test
    void findProjectById_null_found_notFound() {
        Course c = new Course();
        assertNull(c.findProjectById(1)); // null list guard

        Project p1 = new Project(); p1.setProjectId(10);
        Project p2 = new Project(); p2.setProjectId(11);
        List<Project> projects = new ArrayList<>();
        projects.add(p1);
        projects.add(p2);
        c.setProjects(projects);

        assertSame(p1, c.findProjectById(10));
        assertSame(p2, c.findProjectById(11));
        assertNull(c.findProjectById(12));
    }

    @Test
    void findProjectByName_caseInsensitive_trim_and_notFound() {
        Course c = new Course();
        Project p1 = new Project(); p1.setProjectName("  Alpha PROJECT  ");
        Project p2 = new Project(); p2.setProjectName("Beta");
        List<Project> projects = new ArrayList<>();
        projects.add(p1);
        projects.add(p2);
        c.setProjects(projects);

        assertSame(p1, c.findProjectByName("alpha project"));
        assertSame(p1, c.findProjectByName("  ALPHA PROJECT"));
        assertNull(c.findProjectByName("Gamma"));
        assertNull(c.findProjectByName(null));
    }

    @Test
    void findProject_object_byId_and_byReference_and_notFound() {
        Course c = new Course();
        Project storedById = new Project(); storedById.setProjectId(21);
        Project storedByRef = new Project(); storedByRef.setProjectId(0); // id <= 0 to force reference path

        List<Project> projects = new ArrayList<>();
        projects.add(storedById);
        projects.add(storedByRef);
        c.setProjects(projects);

        Project probeSameId = new Project(); probeSameId.setProjectId(21);
        assertSame(storedById, c.findProject(probeSameId));

        assertSame(storedByRef, c.findProject(storedByRef));

        Project notPresent = new Project(); notPresent.setProjectId(99);
        assertNull(c.findProject(notPresent));
        assertNull(c.findProject(null));
    }
}

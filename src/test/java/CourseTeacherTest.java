import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import UPT_SQ.EduScrumAwards.model.*;

public class CourseTeacherTest {

    @Test
    void defaultConstructor_and_setters_getters() {
        CourseTeacher ct = new CourseTeacher();
        Course course = new Course("QA");
        Teacher teacher = new Teacher();
        teacher.setName("Jane");

        ct.setCourse(course);
        ct.setTeacher(teacher);
        ct.setIsResponsible(true);

        assertSame(course, ct.getCourse());
        assertSame(teacher, ct.getTeacher());
        assertTrue(ct.isResponsible());

        ct.setIsResponsible(false);
        assertFalse(ct.isResponsible());
    }

    @Test
    void allArgsConstructor_setsFields() {
        Course course = new Course("SE");
        Teacher teacher = new Teacher();
        teacher.setName("John");

        CourseTeacher ct = new CourseTeacher(42, course, teacher, true);

        assertEquals(42, ct.getCourseTeacherID());
        assertSame(course, ct.getCourse());
        assertSame(teacher, ct.getTeacher());
        assertTrue(ct.isResponsible());

        // mutate and verify again
        ct.setIsResponsible(false);
        assertFalse(ct.isResponsible());
    }
}

package UPT_SQ.EduScrumAwards.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

  private Course course;

  @BeforeEach
  void setUp() {
    course = new Course("Test Course");
    course.setCourseID(1);
  }

  @Test
  void testGettersAndSetters() {
    assertEquals(1, course.getCourseID());
    assertEquals("Test Course", course.getCourseName());

    course.setCourseName("Updated Name");
    assertEquals("Updated Name", course.getCourseName());
  }

  @Test
  void testCourseTeacherMethods() {
    // Setup Teachers
    Teacher t1 = new Teacher();
    t1.setName("Alice");
    // t1.setUserId(10L);

    Teacher t2 = new Teacher();
    t2.setName("Bob");
    // t2.setUserId(20L);

    CourseTeacher ct1 = new CourseTeacher(course, t1, true);
    CourseTeacher ct2 = new CourseTeacher(course, t2, false);

    List<CourseTeacher> teachers = new ArrayList<>();
    teachers.add(ct1);
    teachers.add(ct2);
    course.setCourseTeachers(teachers);

    // Test findCourseTeacherByName
    assertEquals(ct1, course.findCourseTeacherByName("Alice"));
    assertEquals(ct2, course.findCourseTeacherByName("bob")); // Case insensitive
    assertNull(course.findCourseTeacherByName("Charlie"));

    // Test findCourseTeacher (by Object)
    assertEquals(ct1, course.findCourseTeacher(ct1));
    assertNull(course.findCourseTeacher(new CourseTeacher()));

    // Test isCourseTeacher
    assertTrue(course.isCourseTeacher(t1));
    assertFalse(course.isCourseTeacher(new Teacher()));
  }

  @Test
  void testProjectMethods() {
    // Setup Projects
    Project p1 = new Project();
    p1.setProjectName("Alpha");
    p1.setProjectId(10); // Project usually has setter as seen in previous tests
    p1.setCourse(course);

    Project p2 = new Project();
    p2.setProjectName("Beta");
    p2.setProjectId(20);
    p2.setCourse(course);

    List<Project> projects = new ArrayList<>();
    projects.add(p1);
    projects.add(p2);
    course.setProjects(projects);

    // Test findProjectById
    assertEquals(p1, course.findProjectById(10));
    assertEquals(p2, course.findProjectById(20));
    assertNull(course.findProjectById(99));

    // Test findProjectByName
    assertEquals(p1, course.findProjectByName("Alpha"));
    assertEquals(p2, course.findProjectByName("beta")); // Case insensitive
    assertNull(course.findProjectByName("Gamma"));

    // Test findProject (by Object)
    assertEquals(p1, course.findProject(p1));

    // Test retrieve list
    assertEquals(2, course.getProjects().size());
  }

  @Test
  void testIsCourseTeacher() {
    Course course = new Course();

    Teacher teacher1 = new Teacher();
    teacher1.setUserId(101L);
    Teacher teacher2 = new Teacher();
    teacher2.setUserId(102L);

    CourseTeacher ct1 = new CourseTeacher();
    ct1.setTeacher(teacher1);
    course.getCourseTeachers().add(ct1);

    assertTrue(course.isCourseTeacher(101L), "Teacher with ID 101 should be a course teacher");

    assertFalse(course.isCourseTeacher(102L), "Teacher with ID 102 should not be a course teacher");
  }

  @Test
  void testSearchCourse() {
    Global global = new Global();
    Course c1 = new Course("Math");
    c1.setCourseID(1);
    global.getCourses().add(c1);

    assertEquals(c1, global.searchCourse(1));
    assertNull(global.searchCourse(99));
  }

  @Test
  void testSearchCourseTeacher() throws Exception {
    Teacher t = new Teacher();
    t.setName("Teacher 1");
    CourseTeacher ct = new CourseTeacher(course, t, true);

    // Use reflection to set private ID since there is no setter
    java.lang.reflect.Field field = ct.getClass().getDeclaredField("courseTeacherID");
    field.setAccessible(true);
    field.setInt(ct, 10);

    course.getCourseTeachers().add(ct);

    assertEquals(ct, course.searchCourseTeacher(10));
    assertNull(course.searchCourseTeacher(99));
  }

  @Test
  void testFindCourseTeacherById() throws Exception {
    Teacher t = new Teacher();
    t.setName("Teacher 2");
    CourseTeacher ct = new CourseTeacher(course, t, false);

    // Use reflection to set private ID
    java.lang.reflect.Field field = ct.getClass().getDeclaredField("courseTeacherID");
    field.setAccessible(true);
    field.setInt(ct, 20);

    course.getCourseTeachers().add(ct);

    assertEquals(ct, course.findCourseTeacherById(20));
    assertNull(course.findCourseTeacherById(99));
  }

  @Test
  void testGetCourseTeachers() {
    // Initially empty (initialized in constructor)
    assertNotNull(course.getCourseTeachers());
    assertTrue(course.getCourseTeachers().isEmpty());

    Teacher t1 = new Teacher();
    CourseTeacher ct1 = new CourseTeacher(course, t1, true);
    course.getCourseTeachers().add(ct1);

    assertEquals(1, course.getCourseTeachers().size());
    assertEquals(ct1, course.getCourseTeachers().get(0));

    course.setCourseTeachers(null);
    assertTrue(course.getCourseTeachers().isEmpty());
  }

  @Test
  void testSearchMethodsWithNullScenarios() {
    // Test with existing (but empty/non-null) list passing null arguments where
    // applicable
    assertNull(course.findCourseTeacherByName(null));
    assertNull(course.findCourseTeacher(null));

    // Test with null list
    course.setCourseTeachers(null);
    assertNull(course.findCourseTeacherById(10));
    assertNull(course.findCourseTeacherByName("Alice"));
    assertNull(course.findCourseTeacher(new CourseTeacher()));
  }

  @Test
  void testFindCourseTeacher_WithIdStrategy() throws Exception {
    Teacher t1 = new Teacher();
    t1.setName("Teacher Stored");
    CourseTeacher ctStored = new CourseTeacher(course, t1, true);

    // Set ID=100 for the stored teacher
    java.lang.reflect.Field field = ctStored.getClass().getDeclaredField("courseTeacherID");
    field.setAccessible(true);
    field.setInt(ctStored, 100);

    course.getCourseTeachers().add(ctStored);

    // Create a different instance with the same ID logic
    CourseTeacher ctLookup = new CourseTeacher();
    field.setInt(ctLookup, 100);

    // Should find ctStored because IDs match, even if objects are different refs
    assertEquals(ctStored, course.findCourseTeacher(ctLookup));

    // Test with ID that doesn't exist
    CourseTeacher ctNotFound = new CourseTeacher();
    field.setInt(ctNotFound, 999);
    assertNull(course.findCourseTeacher(ctNotFound));
  }
}

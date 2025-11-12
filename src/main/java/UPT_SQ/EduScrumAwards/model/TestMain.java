package UPT_SQ.EduScrumAwards.model;

import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;

public class TestMain {
    public static void main(String[] args) {
        //just to test
        // we will remove this class later
        long ts = System.currentTimeMillis();
        Award award = new Award(
                "Top Performer " + ts,
                "Awarded for outstanding performance",
                100,
                AwardType.AUTOMATIC,
                AssignMode.TEAM
        );

        Team team = new Team(
                "IronMan-" + ts
        );

        Student student = new Student();
        student.setName("Alice " + ts);
        student.setLogin("alice_" + ts);
        student.setPassword("pass123");

        TeamMember mem = new TeamMember("Alice Wonderland", TeamMember.Role.PRODUCT_OWNER);
        mem.setTeam(team);
        mem.setStudent(student);

        Teacher teacher = new Teacher();
        teacher.setName("Prof. Smith " + ts);
        teacher.setLogin("prof_smith_" + ts);
        teacher.setPassword("pass123");

        Course course = new Course("Test Course " + ts);
        CourseTeacher courseTeacher = new CourseTeacher(course, teacher, true);

        Project project = new Project();
        project.setProjectName("AI Development " + ts);
        project.setTeam(team);
        project.setCourse(course);

        Sprint sprint1 = new Sprint();
        sprint1.setStartDate(new Date()); // today
        sprint1.setEndDate(new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000)); // +7 days
        sprint1.setProject(project);


        Goal goal1 = new Goal("Implement login feature", 10, false);
        goal1.setSprint(sprint1);

        AwardRule awardRule = new AwardRule();
        awardRule.setAward(award);
        awardRule.setProject(project);
        awardRule.setTeacher(teacher);
        awardRule.setAllGoalsCompleted(false);
        awardRule.setCompletionPercent(75.0);

        StudentAward studentAward = new StudentAward();
        studentAward.setAward(award);
        studentAward.setStudent(student);
        studentAward.setTeacher(teacher);
        studentAward.setProject(project);
        studentAward.setTeam(team);
        studentAward.setDate(new Date());
        studentAward.setPoints(100);

        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();
        session.beginTransaction();

        session.persist(award);
        session.persist(team);
        session.persist(student);
        session.persist(mem);
        session.persist(teacher);
        session.persist(course);
        session.persist(courseTeacher);
        session.persist(project);
        session.persist(sprint1);
        session.persist(goal1);

        session.persist(awardRule);
        session.persist(studentAward);


        session.flush();
        session.getTransaction().commit();
        session.close();
        DatabaseHelper.exit();

//        ////        TEST Global
//        Global g = new Global();
//        g.createCourse("###### TEST COURSE 2");
//        g.readAllCourseWithJplq();
//        for (Course courses : g.getCourses()) {
//            System.out.println("###### " + courses.getCourseName());
//        }
//        System.out.println("###### Searched course: " + g.searchCourse(1).getCourseName());
//        g.updateCourse(1, "NEW NAME");
//        System.out.println("###### Searched course: " + g.searchCourse(1).getCourseName());
//
//
//        course.createCourseTeacher(teacher, false);
//        course.readAllCourseTeacherWithJplq();
//        for (CourseTeacher ct : course.getTeachers()) {
//            System.out.println("###### " + ct.getTeacher().getName());
//        }
//        int teacherId = course.getTeachers().get(0).getCourseTeacherID();
//        System.out.println("###### Searched teacher: " + course.searchCourseTeacher(teacherId).getTeacher().getName() + ". Is responsible Teacher: " + (course.searchCourseTeacher(teacherId).isResponsible() ? "Yes" : "No"));
//        course.updateCourseTeacher(teacherId, true);
//        System.out.println("###### Searched teacher: " + course.searchCourseTeacher(teacherId).getTeacher().getName() + ". Is responsible Teacher: " + (course.searchCourseTeacher(teacherId).isResponsible() ? "Yes" : "No"));
//        course.readAllCourseTeacherWithJplq();
//        System.out.println(("###### Size of ct's: " + course.getTeachers().size()));
//        course.deleteCourseTeacher(teacherId);
//        course.readAllCourseTeacherWithJplq();
//        System.out.println(("###### Size of ct's: " + course.getTeachers().size()));
//
//


////        g.createAward("Top Performer",
////                "Awarded for outstanding performance",
////                100,
////                "AUTOMATIC",
////                 "TEAM");
//        g.readAllAwardWithJplq();
//        for(int i = 0; i < g.getAwards().size(); i++)
//        {
//            System.out.println("Award ID: " + g.getAwards().get(i));
//        }
//        System.out.println(g.updateAward(1,"Dragon Rider",
//                "Awarded for fearlessness and strategy",
//                100));
//        g.readAllStudentAwardWithJplq();
//        for(StudentAward sa : g.getStudentsAwards()) {
//            System.out.println(sa);
//        }


        // Test create/update award
//        Global global = new Global();
//
//        global.getUsers().add(student);
//        global.getUsers().add(teacher);
//        global.getTeams().add(team);
//        ArrayList<AwardRule> awardRules =new  ArrayList<>();
//        awardRules.add(awardRule);
//        award.setAwardRules(awardRules);
//        global.getAwards().add(award);
//        global.getStudentsAwards().add(studentAward);
//        ArrayList<Project> projects = new ArrayList<>();
//        projects.add(project);
//        ArrayList<CourseTeacher> courseTeachers = new ArrayList<>();
//        courseTeachers.add(courseTeacher);
//        course.setCourseTeachers(courseTeachers);
//        course.setProjects(projects);
//        global.getCourses().add(course);
//        global.getCourseTeachers().add(courseTeacher);
//
//
//        System.out.println(project.getProjectId());
//
//        System.out.println(global.createAwardRule(30,true,2,1,1));
//        System.out.println(global.updateAwardRule(1,25,true,1));
//
//
//


    }
}

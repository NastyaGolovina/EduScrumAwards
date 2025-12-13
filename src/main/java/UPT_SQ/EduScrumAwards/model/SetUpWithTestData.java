package UPT_SQ.EduScrumAwards.model;

import org.hibernate.Session;

import java.util.Date;

public class SetUpWithTestData {
    public static void main(String[] args) {
        Award award = new Award(
                "Top Performer ",
                "Awarded for outstanding performance",
                100,
                AwardType.AUTOMATIC,
                AssignMode.TEAM
        );

        Team team = new Team(
                "Avengers"
        );

        Student student = new Student();
        student.setName("Alice");
        student.setLogin("alice");
        student.setPassword("pass123");

        TeamMember mem = new TeamMember("Alice Wonderland", TeamMember.Role.PRODUCT_OWNER);
        mem.setTeam(team);
        mem.setStudent(student);

        Teacher teacher = new Teacher();
        teacher.setName("Prof. Smith");
        teacher.setLogin("prof_smith");
        teacher.setPassword("pass123");

        Course course = new Course("Test Course");
        CourseTeacher courseTeacher = new CourseTeacher(course, teacher, true);

        Project project = new Project();
        project.setProjectName("AI Development");
        project.setTeam(team);
        project.setCourse(course);

        Sprint sprint1 = new Sprint();
        sprint1.setStartDate(new Date());
        sprint1.setEndDate(new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000));
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

        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = databaseHelper.getSessionFactory().openSession();
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


    }
}

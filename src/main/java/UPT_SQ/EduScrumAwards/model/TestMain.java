package UPT_SQ.EduScrumAwards.model;

import org.hibernate.Session;

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

        int courseId = (int)((ts % 1000000000L) + 1); // ensure positive, avoids PK clash on reruns
        Course course = new Course(courseId, "Test Course " + ts);
        CourseTeacher courseTeacher = new CourseTeacher(0, course, teacher, true);

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


        session.getTransaction().commit();
        session.close();
        DatabaseHelper.exit();


////        test Global
//        Global g = new Global();
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




    }
}

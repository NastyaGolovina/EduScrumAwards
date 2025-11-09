package UPT_SQ.EduScrumAwards.model;

import org.hibernate.Session;

import java.util.Date;

public class TestMain {
    public static void main(String[] args) {
        //just to test
        // we will remove this class later
        Award award = new Award(
                "Top Performer",
                "Awarded for outstanding performance",
                100,
                AwardType.AUTOMATIC
        );

        Team team = new Team(
                "IronMan"
        );

        Student student = new Student();
        student.setName("Alice");
        student.setLogin("alice2");
        student.setPassword("pass123");

        TeamMember mem = new TeamMember("Alice Wonderland", TeamMember.Role.PRODUCT_OWNER);
        mem.setTeam(team);
        mem.setStudent(student);

        Course course = new Course();
        course.setCourseName("AI Course");

        Project project = new Project();
        project.setProjectName("AI Development");
        project.setTeam(team);
        project.setCourse(course);


        Sprint sprint1 = new Sprint();
        sprint1.setStartDate(new Date()); // today
        sprint1.setEndDate(new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000)); // +7 days
        sprint1.setProject(project);


        Goal goal1 = new Goal("Implement login feature", 10, false);
        goal1.setSprint(sprint1);

        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();
        session.beginTransaction();

        session.persist(award);
        session.persist(team);
        session.persist(student);
        session.persist(mem);
        session.persist(course);
        session.persist(project);
        session.persist(sprint1);
        session.persist(goal1);


        session.getTransaction().commit();
        session.close();
        DatabaseHelper.exit();



//        //test Global
//        Global g = new Global();
////        g.createAward("Top Performer",
////                "Awarded for outstanding performance",
////                100,
////                "AUTOMATIC");
//        g.readAllAwardWithJplq();
//        for(int i = 0; i < g.getAwards().size(); i++)
//        {
//            System.out.println("Award ID: " + g.getAwards().get(i));
//        }
//        System.out.println(g.updateAward(1,"Dragon Rider",
//                "Awarded for fearlessness and strategy",
//                100,
//                "AUTOMATIC"));

        
        

    }
}

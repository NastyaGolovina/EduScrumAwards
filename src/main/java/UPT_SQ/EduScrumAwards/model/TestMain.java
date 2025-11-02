package UPT_SQ.EduScrumAwards.model;

import org.hibernate.Session;

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
        student.setLogin("alice");
        student.setPassword("pass123");

        TeamMember mem = new TeamMember("Alice Wonderland", TeamMember.Role.PRODUCT_OWNER);
        mem.setTeam(team);
        mem.setStudent(student);


        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();
        session.beginTransaction();

        session.persist(award);
        session.persist(team);
        session.persist(student);
        session.persist(mem);

        session.getTransaction().commit();
        session.close();
        DatabaseHelper.exit();

//        Global g = new Global();
//        g.createAward("Top Performer",
//                "Awarded for outstanding performance",
//                100,
//                "AUTOMATIC");

    }
}

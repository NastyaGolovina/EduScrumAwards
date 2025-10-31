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

        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();
        session.beginTransaction();

        session.persist(award);

        session.getTransaction().commit();
        session.close();
        DatabaseHelper.exit();
//
//        Global g = new Global();
//        g.createAward("Top Performer",
//                "Awarded for outstanding performance",
//                100,
//                "AUTOMATIC");

    }
}

package UPT_SQ.EduScrumAwards.model;

import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

//@Component
public class Global {
    private ArrayList<User> users;
    private  ArrayList <Course> courses;
    private  ArrayList<Team> teams;
    private ArrayList<Award> awards;
    private ArrayList<StudentAward> studentsAwards;

    public Global() {
        users = new ArrayList<>();
        courses = new ArrayList<>();
        teams = new ArrayList<>();
        awards = new ArrayList<>();
        studentsAwards = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public ArrayList<Award> getAwards() {
        return awards;
    }

    public void setAwards(ArrayList<Award> awards) {
        this.awards = awards;
    }

    public ArrayList<StudentAward> getStudentsAwards() {
        return studentsAwards;
    }

    public void setStudentsAwards(ArrayList<StudentAward> studentsAwards) {
        this.studentsAwards = studentsAwards;
    }

//    public void readFromDB() {
//
//    }
//

    /**
     * Creates a new Award object and saves it to the database.
     *
     * @param awardName        The name of the award. Must not be empty and no longer than 100 characters.
     * @param awardDescription The description of the award. Must not be empty and no longer than 500 characters.
     * @param pointsValue      The number of points for the award. Must be between 0 and 1000.
     * @param assignType       The type of assignment for the award (will be converted to AwardType).
     * @return "Success" if the award was successfully created and saved, or an error message if any validation fails.
     */
    public String createAward(String awardName, String awardDescription, int pointsValue, String assignType) {
        if(!awardName.isEmpty() && !awardDescription.isEmpty() && pointsValue >= 0 && !assignType.isEmpty()) {
            if(awardName.length() <= 100) {
                if(awardDescription.length() <= 500) {
                    if(pointsValue <= 1000) {
                        Award newAward = new Award(awardName,awardDescription,pointsValue, AwardType.valueOf(assignType.toUpperCase()));
                        awards.add(newAward);
                        DatabaseHelper DatabaseHelper = new DatabaseHelper();
                        DatabaseHelper.setup();
                        Session session = DatabaseHelper.getSessionFactory().openSession();
                        session.beginTransaction();

                        session.persist(newAward);

                        session.getTransaction().commit();
                        session.close();
                        DatabaseHelper.exit();
                        return "Success";
                    } else {
                        return  "ERROR: Too many points!";
                    }
                } else {
                    return "ERROR: Description is too long!";
                }
            } else {
                return "ERROR: Name is too long!";
            }
        }
        return "ERROR: One of the field is empty";
    }

    /**
     * Loads all Award objects from the database and stores them in the local awards list.
     * Uses JPQL to query the database.
     */
    public void readAllAwardWithJplq() {
        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();

        List<Award> awardList = session.createQuery("SELECT a FROM Award a", Award.class).getResultList();

        awards = (ArrayList<Award>)awardList;
        session.close();
        DatabaseHelper.exit();
    }

    /**
     * Searches for an award in the local awards list by its unique ID.
     *
     * @param awardId The ID of the award to search for.
     * @return The Award object with the given ID if found; otherwise, returns null.
     */
    public Award searchAward(int awardId) {
        int i = 0;
        while (i < awards.size() && awards.get(i).getAwardID() != awardId) {
            i++;
        }
        if (i != awards.size()) {
            return awards.get(i);
        }
        return null;
    }

    /**
     * Updates an existing award with the specified ID.
     *
     * @param id               The ID of the award to update.
     * @param awardName        The new name of the award. Must not be empty and no longer than 100 characters.
     * @param awardDescription The new description of the award. Must not be empty and no longer than 500 characters.
     * @param pointsValue      The new points value for the award. Must be between 0 and 1000.
     * @param assignType       The new assignment type for the award (will be converted to AwardType).
     * @return "Success" if the award was successfully updated, or an error message if validation fails or the award does not exist.
     */
    // assignType  ???????
    public String updateAward(int id,String awardName, String awardDescription, int pointsValue, String assignType) {
        Award award = searchAward(id);
        if(award != null) {
            if(!awardName.isEmpty() && !awardDescription.isEmpty() && pointsValue >= 0 && !assignType.isEmpty()) {
                if(awardName.length() <= 100) {
                    if(awardDescription.length() <= 500) {
                        if(pointsValue <= 1000) {
                            award.setAwardName(awardName);
                            award.setAwardDescription(awardDescription);
                            award.setPointsValue(pointsValue);
                            award.setAssignType(AwardType.valueOf(assignType.toUpperCase()));

                            DatabaseHelper DatabaseHelper = new DatabaseHelper();
                            DatabaseHelper.setup();
                            Session session = DatabaseHelper.getSessionFactory().openSession();
                            session.beginTransaction();

                            session.merge(award);

                            session.getTransaction().commit();
                            session.close();
                            DatabaseHelper.exit();

                            return "Success";
                        } else {
                            return  "ERROR: Too many points!";
                        }
                    } else {
                        return "ERROR: Description is too long!";
                    }
                } else {
                    return "ERROR: Name is too long!";
                }
            }
            return "ERROR: One of the field is empty";
        }
        return "ERROR: Award with this id does not exist";
    }

}

package UPT_SQ.EduScrumAwards.model;

import org.hibernate.Session;

import java.util.ArrayList;

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


    public String createAward(String awardName, String awardDescription, int pointsValue, String assignType) {
        if(!awardName.isEmpty() && !awardDescription.isEmpty() && pointsValue >= 0 && !assignType.isEmpty()) {
            if(awardName.length() <= 100) {
                if(awardDescription.length() <= 500) {
                    if(pointsValue <= 100) {
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
}

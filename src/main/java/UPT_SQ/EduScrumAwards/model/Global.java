package UPT_SQ.EduScrumAwards.model;

import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
/**
 * The {@code Global} class serves as a centralized data container for managing
 * users, courses, teams, awards, and student awards across the system.
 *
 * This class initializes and maintains lists for each of these entities,
 * providing getter and setter methods for accessing and updating them.
 * It is typically used to store and share global application data
 * throughout the program.
 */
//@Component
public class Global {
    private ArrayList<User> users;
    private  ArrayList <Course> courses;
    private  ArrayList<Team> teams;
    private ArrayList<Award> awards;
    private ArrayList<StudentAward> studentsAwards;
    private ArrayList<CourseTeacher> courseTeachers;

    /**
     * Constructs a new {@code Global} instance and initializes all lists
     * (users, courses, teams, awards, and student awards) as empty {@link ArrayList}s.
     */
    public Global() {
        users = new ArrayList<>();
        courses = new ArrayList<>();
        teams = new ArrayList<>();
        awards = new ArrayList<>();
        studentsAwards = new ArrayList<>();
        courseTeachers = new ArrayList<>();
    }

    /**
     * Returns the list of all users.
     *
     * @return a list of {@link User} objects
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Sets the list of users.
     *
     * @param users a list of {@link User} objects to set
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * Returns the list of all courses.
     *
     * @return a list of {@link Course} objects
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * Sets the list of courses.
     *
     * @param courses a list of {@link Course} objects to set
     */
    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    /**
     * Returns the list of all teams.
     *
     * @return a list of {@link Team} objects
     */
    public ArrayList<Team> getTeams() {
        return teams;
    }

    /**
     * Sets the list of teams.
     *
     * @param teams a list of {@link Team} objects to set
     */
    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    /**
     * Returns the list of all awards.
     *
     * @return a list of {@link Award} objects
     */
    public ArrayList<Award> getAwards() {
        return awards;
    }

    /**
     * Sets the list of awards.
     *
     * @param awards a list of {@link Award} objects to set
     */
    public void setAwards(ArrayList<Award> awards) {
        this.awards = awards;
    }

    /**
     * Returns the list of all student awards.
     *
     * @return a list of {@link StudentAward} objects
     */
    public ArrayList<StudentAward> getStudentsAwards() {
        return studentsAwards;
    }

    /**
     * Sets the list of student awards.
     *
     * @param studentsAwards a list of {@link StudentAward} objects to set
     */
    public void setStudentsAwards(ArrayList<StudentAward> studentsAwards) {
        this.studentsAwards = studentsAwards;
    }

    /**
     * Returns the list of all CourseTeacher entries.
     * @return list of CourseTeacher
     */
    public ArrayList<CourseTeacher> getCourseTeachers() {
        return courseTeachers;
    }

    /**
     * Sets the list of CourseTeacher entries.
     * @param courseTeachers list to set
     */
    public void setCourseTeachers(ArrayList<CourseTeacher> courseTeachers) {
        this.courseTeachers = courseTeachers;
    }

//    public void readFromDB() {

//    }


    /**
     * Creates a new Award object and saves it to the database.
     *
     * @param awardName        The name of the award. Must not be empty and no longer than 100 characters.
     * @param awardDescription The description of the award. Must not be empty and no longer than 500 characters.
     * @param pointsValue      The number of points for the award. Must be between 0 and 1000.
     * @param assignType       The type of assignment for the award (will be converted to AwardType).
     * @return "Success" if the award was successfully created and saved, or an error message if any validation fails.
     */
    public String createAward(String awardName, String awardDescription, int pointsValue, String assignType,
                              String assignMode) {
        if(!awardName.isEmpty() && !awardDescription.isEmpty() && pointsValue >= 0 && !assignType.isEmpty() && !assignMode.isEmpty()) {
            if(awardName.length() <= 100) {
                if(awardDescription.length() <= 500) {
                    if(pointsValue <= 1000) {
                        Award newAward = new Award(awardName,
                                awardDescription,
                                pointsValue,
                                AwardType.valueOf(assignType.toUpperCase()),
                                AssignMode.valueOf(assignMode.toUpperCase()));
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
     * @return "Success" if the award was successfully updated, or an error message if validation fails or the award does not exist.
     */
    public String updateAward(int id,String awardName, String awardDescription, int pointsValue) {
        Award award = searchAward(id);
        if(award != null) {
            if(!awardName.isEmpty() && !awardDescription.isEmpty() && pointsValue >= 0) {
                if(awardName.length() <= 100) {
                    if(awardDescription.length() <= 500) {
                        if(pointsValue <= 1000) {
                            award.setAwardName(awardName);
                            award.setAwardDescription(awardDescription);
                            award.setPointsValue(pointsValue);

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

    /**
     * Searches for a {@link StudentAward} in the list of student awards by its ID.
     *
     * This method iterates through the {@code studentsAwards} list to find
     * a {@link StudentAward} with the specified {@code studentAwardId}.
     * Returns the matching object if found, otherwise returns {@code null}.
     *
     * @param studentAwardId the unique identifier of the {@link StudentAward} to search for
     * @return the {@link StudentAward} with the given ID, or {@code null} if not found
     */
    public StudentAward searchStudentAward(int studentAwardId) {
        int i = 0;
        while (i < studentsAwards.size() && studentsAwards.get(i).getStudentAwardId() != studentAwardId) {
            i++;
        }
        if (i != studentsAwards.size()) {
            return studentsAwards.get(i);
        }
        return null;
    }

    /**
     * Creates a new Course and saves it to the database.
     *
     * @param courseId   The ID of the course. Must be a positive integer.
     * @param courseName The name of the course. Must not be empty and no longer than 100 characters.
     * @return "Success" if the course was created and saved; otherwise, an error message describing the issue.
     */
    public String createCourse(int courseId, String courseName) {
        if (courseId <= 0) {
            return "ERROR: Invalid course ID!";
        }
        if (courseName == null || courseName.isEmpty()) {
            return "ERROR: Name is empty!";
        }
        if (courseName.length() > 100) {
            return "ERROR: Name is too long!";
        }

        Course newCourse = new Course(courseId, courseName);
        courses.add(newCourse);

        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();
        session.beginTransaction();

        session.persist(newCourse);

        session.getTransaction().commit();
        session.close();
        DatabaseHelper.exit();
        return "Success";
    }

    /**
     * Loads all Course objects from the database and stores them in the local courses list.
     * Uses JPQL to query the database.
     */
    public void readAllCourseWithJplq() {
        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();

        List<Course> courseList = session.createQuery("SELECT c FROM Course c", Course.class).getResultList();

        courses = (ArrayList<Course>) courseList;
        session.close();
        DatabaseHelper.exit();
    }

    /**
     * Searches for a course in the local courses list by its unique ID.
     *
     * @param courseId The ID of the course to search for.
     * @return The Course object with the given ID if found; otherwise, returns null.
     */
    public Course searchCourse(int courseId) {
        int i = 0;
        while (i < courses.size() && courses.get(i).getCourseID() != courseId) {
            i++;
        }
        if (i != courses.size()) {
            return courses.get(i);
        }
        return null;
    }

    /**
     * Updates an existing course with the specified ID.
     *
     * @param id         The ID of the course to update.
     * @param courseName The new name of the course. Must not be empty and no longer than 100 characters.
     * @return "Success" if the course was successfully updated, or an error message if validation fails or the course does not exist.
     */
    public String updateCourse(int id, String courseName) {
        Course course = searchCourse(id);
        if (course != null) {
            if (courseName != null && !courseName.isEmpty()) {
                if (courseName.length() <= 100) {
                    course.setCourseName(courseName);

                    DatabaseHelper DatabaseHelper = new DatabaseHelper();
                    DatabaseHelper.setup();
                    Session session = DatabaseHelper.getSessionFactory().openSession();
                    session.beginTransaction();

                    session.merge(course);

                    session.getTransaction().commit();
                    session.close();
                    DatabaseHelper.exit();

                    return "Success";
                } else {
                    return "ERROR: Name is too long!";
                }
            }
            return "ERROR: Name is empty!";
        }
        return "ERROR: Course with this id does not exist";
    }

    /**
     * Creates a new CourseTeacher and saves it to the database.
     *
     * @param course the associated Course (must not be null)
     * @param teacher the associated Teacher (must not be null)
     * @param isResponsible whether the teacher is responsible for the course
     * @return "Success" if created and saved; error message otherwise
     */
    public String createCourseTeacher(Course course, Teacher teacher, boolean isResponsible) {
        if (course == null) {
            return "ERROR: Course is null!";
        }
        if (teacher == null) {
            return "ERROR: Teacher is null!";
        }
        CourseTeacher newCt = new CourseTeacher(0, course, teacher, isResponsible);
        courseTeachers.add(newCt);

        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();
        session.beginTransaction();

        session.persist(newCt);

        session.getTransaction().commit();
        session.close();
        DatabaseHelper.exit();
        return "Success";
    }

    /**
     * Loads all CourseTeacher objects from the database and stores them locally.
     */
    public void readAllCourseTeacherWithJplq() {
        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();

        List<CourseTeacher> list = session.createQuery("SELECT ct FROM CourseTeacher ct", CourseTeacher.class).getResultList();
        courseTeachers = (ArrayList<CourseTeacher>) list;

        session.close();
        DatabaseHelper.exit();
    }

    /**
     * Searches the local list of CourseTeacher by ID.
     * @param id CourseTeacher ID
     * @return the CourseTeacher if found, otherwise null
     */
    public CourseTeacher searchCourseTeacher(int id) {
        int i = 0;
        while (i < courseTeachers.size() && courseTeachers.get(i).getCourseTeacherID() != id) {
            i++;
        }
        if (i != courseTeachers.size()) {
            return courseTeachers.get(i);
        }
        return null;
    }

    /**
     * Updates the isResponsible flag of a CourseTeacher and merges it in the database.
     * @param id the CourseTeacher ID
     * @param isResponsible new value for responsibility flag
     * @return "Success" if updated; error message otherwise
     */
    public String updateCourseTeacher(int id, boolean isResponsible) {
        CourseTeacher ct = searchCourseTeacher(id);
        if (ct != null) {
            ct.setIsResponsible(isResponsible);

            DatabaseHelper DatabaseHelper = new DatabaseHelper();
            DatabaseHelper.setup();
            Session session = DatabaseHelper.getSessionFactory().openSession();
            session.beginTransaction();

            session.merge(ct);

            session.getTransaction().commit();
            session.close();
            DatabaseHelper.exit();
            return "Success";
        }
        return "ERROR: CourseTeacher with this id does not exist";
    }

}

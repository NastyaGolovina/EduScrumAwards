import UPT_SQ.EduScrumAwards.model.Goal;
import UPT_SQ.EduScrumAwards.model.Sprint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GoalTest {

    @Test
    public void testConstructorWithoutId() {
        Goal g = new Goal("Discuss project", 10, true);

        assertEquals("Discuss project", g.getDescription());
        assertEquals(10, g.getScore());
        assertTrue(g.isCompleted());
    }

    @Test
    public void testConstructorWithId() {
        Goal g = new Goal(2, "Distribute work", 5, true);

        assertEquals(2, g.getGoalId());
        assertEquals("Distribute work", g.getDescription());
        assertEquals(5, g.getScore());
        assertTrue(g.isCompleted());
    }

    @Test
    public void testSettersAndGetters() {
        Goal g = new Goal();

        g.setGoalId(3);
        g.setDescription("Sprint 1");
        g.setScore(20);
        g.setCompleted(true);

        assertEquals(3, g.getGoalId());
        assertEquals("Sprint 1", g.getDescription());
        assertEquals(20, g.getScore());
        assertTrue(g.isCompleted());
    }

    @Test
    public void testSprintSetterGetter() {
        Goal g = new Goal();
        Sprint sprint = new Sprint();

        g.setSprint(sprint);

        assertEquals(sprint, g.getSprint());
    }

    @Test
    public void testCompleteGoal() {
        Goal g = new Goal("Finish UI", 20, false);

        g.completeGoal();

        assertTrue(g.isCompleted());
    }

    @Test
    public void testToString() {
        Goal g = new Goal(7, "Review code", 15, false);

        String text = g.toString();

        assertTrue(text.contains("goalId=7"));
        assertTrue(text.contains("Review code"));
        assertTrue(text.contains("score=15"));
        assertTrue(text.contains("isCompleted=false"));
    }
}

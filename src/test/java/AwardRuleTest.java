import UPT_SQ.EduScrumAwards.model.Award;
import UPT_SQ.EduScrumAwards.model.AwardRule;
import UPT_SQ.EduScrumAwards.model.Project;
import UPT_SQ.EduScrumAwards.model.Teacher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AwardRuleTest {
    private AwardRule r1;
    private AwardRule r2;
    private AwardRule r4;

    @BeforeAll
    public static void onceExecutedBeforeAll() {
        System.out.println("Test AwardRule Class");
    }

    @BeforeEach
    public void setup() {
        r1 = new AwardRule();

        Teacher teacher = new Teacher();
        Project project = new Project();
        Award award = new Award();

        r2 = new AwardRule(
                80.5,
                true,
                teacher,
                project,
                award
        );

    }

    @Test
    public void testCreateAwardRule() {
        assertNull(r4);
        assertNotNull(r1);
        assertNotNull(r2);
    }

    @Test
    public void testAwardRuleFields() {
        assertEquals(80.5, r2.getCompletionPercent());
        assertTrue(r2.isAllGoalsCompleted());
        assertNotNull(r2.getTeacher());
        assertNotNull(r2.getProject());
        assertNotNull(r2.getAward());
    }

    @Test
    public void testAwardRuleSets() {
        r1.setRuleId(1);
        r1.setCompletionPercent(95.0);
        r1.setAllGoalsCompleted(true);

        Teacher t = new Teacher();
        Project p = new Project();
        Award a = new Award();

        r1.setTeacher(t);
        r1.setProject(p);
        r1.setAward(a);

        assertEquals(1, r1.getRuleId());
        assertEquals(95.0, r1.getCompletionPercent());
        assertTrue(r1.isAllGoalsCompleted());
        assertEquals(t, r1.getTeacher());
        assertEquals(p, r1.getProject());
        assertEquals(a, r1.getAward());
    }
}

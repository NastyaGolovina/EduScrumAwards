import UPT_SQ.EduScrumAwards.model.Award;
import UPT_SQ.EduScrumAwards.model.AwardRule;
import UPT_SQ.EduScrumAwards.model.AwardType;
import UPT_SQ.EduScrumAwards.model.AssignMode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AwardTest {

    private Award a1;
    private Award a2;
    private Award a3;
    private Award a4;

    @BeforeAll
    public static void onceExecutedBeforeAll() {
        System.out.println("Test Award Class");
    }

    @BeforeEach
    public void setup() {
        a1 = new Award();
        a2 = new Award(
                "Participation Award",
                "Awarded for participating in the event",
                10,
                AwardType.MANUAL,
                AssignMode.INDIVIDUAL
        );

        ArrayList<AwardRule> rules = new ArrayList<>();
        rules.add(new AwardRule());

        a3  = new Award(
                2,
                "Achievement Award",
                "Awarded for achieving goals",
                50,
                AwardType.AUTOMATIC,
                AssignMode.TEAM,
                rules
        );
    }

    @Test
    public void testCreateAward() {
        assertNull(a4);
        assertNotNull(a1);
        assertNotNull(a2);
        assertNotNull(a3);
    }

    @Test
    public void testAwardFields() {
        assertEquals(2, a3.getAwardID());
        assertEquals("Achievement Award", a3.getAwardName());
        assertEquals("Awarded for achieving goals", a3.getAwardDescription());
        assertEquals(50, a3.getPointsValue());
        assertEquals(AwardType.AUTOMATIC, a3.getAssignType());
        assertEquals(AssignMode.TEAM, a3.getAssignMode());
        assertEquals(1, a3.getAwardRules().size());
    }

    @Test
    public void testAwardSets() {
        a1.setAwardID(1);
        a1.setAwardName("Excellence Award");
        a1.setAwardDescription("Awarded for outstanding performance");
        a1.setPointsValue(100);
        a1.setAssignType(AwardType.MANUAL);
        a1.setAssignMode(AssignMode.TEAM);
        a1.setAwardRules(new ArrayList<>());

        assertEquals(1, a1.getAwardID());
        assertEquals("Excellence Award", a1.getAwardName());
        assertEquals("Awarded for outstanding performance", a1.getAwardDescription());
        assertEquals(100, a1.getPointsValue());
        assertEquals(AwardType.MANUAL, a1.getAssignType());
        assertEquals(AssignMode.TEAM, a1.getAssignMode());
        assertNotNull(a1.getAwardRules());
        assertEquals(0, a1.getAwardRules().size());
    }
}

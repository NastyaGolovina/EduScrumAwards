package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/awards-rule")
public class AwardRuleController {

    private final Global global;

    @Autowired
    public AwardRuleController(Global global) {
        this.global = global;
    }

    @GetMapping("/all/{awardId}")
    public List<AwardRule> getAllAwardRules(
            @PathVariable int awardId)
    {
        Award award = global.searchAward(awardId);
        if(award != null) {
            return award.getAwardRules();
        }
        return new ArrayList<AwardRule>();
    }


    @PostMapping("/create")
    public String createAwardRule(
            @RequestParam double completionPercent,
            @RequestParam boolean isAllGoalsCompleted,
            @RequestParam long teacherId,
            @RequestParam int projectId,
            @RequestParam int awardId) {

        User user = global.searchUser(teacherId);
        Award award = global.searchAward(awardId);
        Project project =  global.findProject(projectId);
        if (award != null) {
            if (user != null) {
                if (project != null) {
                    return award.createAwardRule(completionPercent, isAllGoalsCompleted, user, project);
                }
            }
        }

        return "ERROR: One of the field is empty or null";
    }

    @PostMapping("/update")
    public String createAwardRule(
            @RequestParam int ruleId,
            @RequestParam double completionPercent,
            @RequestParam boolean isAllGoalsCompleted,
            @RequestParam int awardId) {

        Award award = global.searchAward(awardId);

        if(award != null) {
            return award.updateAwardRule(ruleId,completionPercent,isAllGoalsCompleted);
        }

        return "ERROR: One of the field is empty or null";
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudentAward(
            @RequestParam int ruleId,
            @RequestParam int awardId) {
        String result = "ERROR: One of the field is empty or null";
        Award award = global.searchAward(awardId);

        if(award != null) {
            result = award.deleteAwardRule(ruleId);
        }

        if (result.startsWith("Record successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }


}
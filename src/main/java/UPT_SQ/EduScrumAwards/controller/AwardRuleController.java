package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST controller responsible for managing Award Rules.
 * Award rules define conditions under which an award can be granted.
 * This controller handles listing, creating, updating, and deleting award rules.
 */
@RestController
@RequestMapping("/awards-rule")
public class AwardRuleController {

    private final Global global;


    /**
     * Constructor for dependency injection.
     *
     * @param global the global in-memory datastore used for fetching users, awards, and projects
     */
    @Autowired
    public AwardRuleController(Global global) {
        this.global = global;
    }


    /**
     * Retrieves all award rules associated with a specific award.
     *
     * @param awardId the ID of the award whose rules should be returned
     * @return a list of award rules; empty list if award does not exist
     */
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


    /**
     * Creates a new award rule and associates it with an award.
     *
     * @param completionPercent   required completion percentage for the award
     * @param isAllGoalsCompleted whether all goals must be completed
     * @param teacherId           ID of the teacher who created the rule
     * @param projectId           ID of the project associated with the rule
     * @param awardId             ID of the award to which the rule belongs
     * @return a message indicating success or an error description
     */
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


    /**
     * Updates an existing award rule.
     *
     * @param ruleId              ID of the rule to update
     * @param completionPercent   updated completion percentage
     * @param isAllGoalsCompleted updated goals completion requirement
     * @param awardId             ID of the award containing the rule
     * @return a message indicating success or an error description
     */
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


    /**
     * Deletes an award rule from an award.
     *
     * @param ruleId  ID of the rule to delete
     * @param awardId ID of the award containing the rule
     * @return HTTP 200 OK if deleted successfully; otherwise 404 NOT FOUND
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudentAward(
            @RequestParam int ruleId,
            @RequestParam int awardId) {
        String result = "ERROR: One of the field is empty or null";
        Award award = global.searchAward(awardId);

        if(award != null) {
            result = award.deleteAwardRule(ruleId,global.getStudentsAwards());
        }

        if (result.startsWith("Record successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }


}
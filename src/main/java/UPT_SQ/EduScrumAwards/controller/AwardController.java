package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.model.Award;
import UPT_SQ.EduScrumAwards.model.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/awards")
public class AwardController {

    private final Global global;

    @Autowired
    public AwardController(Global global) {
        this.global = global;
    }

    @GetMapping("/all")
    public List<Award> getAllAwards() {
        return global.getAwards();
    }


    @PostMapping("/create")
    public String createAward(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam int points,
            @RequestParam String assignType,
            @RequestParam String assignMode) {

        return global.createAward(name, description, points, assignType, assignMode);
    }


    @PostMapping("/update")
    public String updateAward(
            @RequestParam int id,
            @RequestParam String awardName,
            @RequestParam String awardDescription) {

        return global.updateAward(id,awardName,awardDescription);
    }
}

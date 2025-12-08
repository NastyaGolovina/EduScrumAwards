package UPT_SQ.EduScrumAwards.DTO;

import UPT_SQ.EduScrumAwards.model.AssignMode;
import UPT_SQ.EduScrumAwards.model.AwardRule;
import UPT_SQ.EduScrumAwards.model.AwardType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;

public class AwardDTO {
    private int awardID;
    private String awardName;
    private int pointsValue;

    public AwardDTO(int awardID, String awardName, int pointsValue) {
        this.awardID = awardID;
        this.awardName = awardName;
        this.pointsValue = pointsValue;
    }

    public int getAwardID() {
        return awardID;
    }

    public void setAwardID(int awardID) {
        this.awardID = awardID;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public int getPointsValue() {
        return pointsValue;
    }

    public void setPointsValue(int pointsValue) {
        this.pointsValue = pointsValue;
    }
}

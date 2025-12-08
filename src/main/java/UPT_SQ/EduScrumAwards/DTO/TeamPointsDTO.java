package UPT_SQ.EduScrumAwards.DTO;

public class TeamPointsDTO {
    private Integer id;
    private String name;
    private Integer points;

    public TeamPointsDTO(Integer id, String name, Integer points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public Integer getPoints() { return points; }
}

package fantasy;

/**
 * Created by trevor on 10/10/15.
 */
public class DeciderMetaData {
    private EspnRanking espnRanking;
    private FanDuelPlayer fanDuelPlayer;
    private String name;
    private String position;
    private Integer salary;

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public EspnRanking getEspnRanking() {
        return espnRanking;
    }

    public void setEspnRanking(EspnRanking espnRanking) {
        this.espnRanking = espnRanking;
    }

    public FanDuelPlayer getFanDuelPlayer() {
        return fanDuelPlayer;
    }

    public void setFanDuelPlayer(FanDuelPlayer fanDuelPlayer) {
        this.fanDuelPlayer = fanDuelPlayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DeciderMetaData{" +
                "espnRanking=" + espnRanking +
                ", fanDuelPlayer=" + fanDuelPlayer +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

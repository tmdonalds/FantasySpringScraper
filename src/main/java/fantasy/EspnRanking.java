package fantasy;

/**
 * Created by trevor on 10/10/15.
 */
public class EspnRanking {
    private String playerName;
    private Integer playerRanking;
    private String positionRanking;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getPlayerRanking() {
        return playerRanking;
    }

    public void setPlayerRanking(int playerRanking) {
        this.playerRanking = playerRanking;
    }

    public String getPositionRanking() {
        return positionRanking;
    }

    public void setPositionRanking(String positionRanking) {
        this.positionRanking = positionRanking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EspnRanking that = (EspnRanking) o;

        if (playerRanking != that.playerRanking) return false;
        if (playerName != null ? !playerName.equals(that.playerName) : that.playerName != null) return false;
        if (positionRanking != null ? !positionRanking.equals(that.positionRanking) : that.positionRanking != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playerName != null ? playerName.hashCode() : 0;
        result = 31 * result + playerRanking;
        result = 31 * result + (positionRanking != null ? positionRanking.hashCode() : 0);
        return result;
    }
}

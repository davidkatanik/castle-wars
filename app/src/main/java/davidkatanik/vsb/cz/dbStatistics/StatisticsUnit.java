package davidkatanik.vsb.cz.dbStatistics;

/**
 * Created by David on 12.12.2015.
 */
public class StatisticsUnit {
    Integer id;
    String homePlayer;
    String awayPlayer;
    String date;
    String homeScore;
    String awayScore;
    Integer rounds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHomePlayer() {
        return homePlayer;
    }

    public void setHomePlayer(String homePlayer) {
        this.homePlayer = homePlayer;
    }

    public String getAwayPlayer() {
        return awayPlayer;
    }

    public void setAwayPlayer(String awayPlayer) {
        this.awayPlayer = awayPlayer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(String homeScore) {
        this.homeScore = homeScore;
    }

    public String getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(String awayScore) {
        this.awayScore = awayScore;
    }

    public Integer getRounds() {
        return rounds;
    }

    public void setRounds(Integer rounds) {
        this.rounds = rounds;
    }
}

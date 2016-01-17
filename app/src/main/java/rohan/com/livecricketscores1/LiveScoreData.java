package rohan.com.livecricketscores1;

import java.util.ArrayList;

/**
 * Created by Rohan Pansare on 1/15/2016.
 */
public class LiveScoreData {
    private String team1Name, team2Name, score;

    LiveScoreData(String team1Name, String team2Name, String score) {
        this.team1Name = team1Name;
        this.team2Name = team2Name;
        this.score = score;

    }
    public LiveScoreData() {


    }

    public String getTeam1Name() {
        return team1Name;
    }

    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = "Click to view: "+team2Name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }


}

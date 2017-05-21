package org.sewas.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by sebastian on 21/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Goal {

    private int GoalID;
    private boolean IsOvertime;
    private boolean IsOwnGoal;
    private boolean IsPenalty;
    private int ScoreTeam1;
    private int ScoreTeam2;

    public int getGoalID() {
        return GoalID;
    }

    public void setGoalID(int goalID) {
        GoalID = goalID;
    }

    public boolean isOvertime() {
        return IsOvertime;
    }

    public void setOvertime(boolean overtime) {
        IsOvertime = overtime;
    }

    public boolean isOwnGoal() {
        return IsOwnGoal;
    }

    public void setOwnGoal(boolean ownGoal) {
        IsOwnGoal = ownGoal;
    }

    public boolean isPenalty() {
        return IsPenalty;
    }

    public void setPenalty(boolean penalty) {
        IsPenalty = penalty;
    }

    public int getScoreTeam1() {
        return ScoreTeam1;
    }

    public void setScoreTeam1(int scoreTeam1) {
        ScoreTeam1 = scoreTeam1;
    }

    public int getScoreTeam2() {
        return ScoreTeam2;
    }

    public void setScoreTeam2(int scoreTeam2) {
        ScoreTeam2 = scoreTeam2;
    }
}

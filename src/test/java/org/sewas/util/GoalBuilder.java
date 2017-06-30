package org.sewas.util;

import org.sewas.domain.model.Goal;

/**
 * Created by sebastian on 25/05/17.
 */
public class GoalBuilder {
    private int GoalID;
    private boolean IsOvertime;
    private boolean IsOwnGoal;
    private boolean IsPenalty;
    private int ScoreTeam1;
    private int ScoreTeam2;

    public GoalBuilder()
    {}

    public GoalBuilder withGoalID(int GoalID)
    {
        this.GoalID = GoalID;
        return this;
    }

    public GoalBuilder isOverTime(boolean isOvertime)
    {
        this.IsOvertime = isOvertime;
        return this;
    }

    public GoalBuilder isOwnGoal(boolean isOwnGoal)
    {
        this.IsOwnGoal = isOwnGoal;
        return this;
    }

    public GoalBuilder isPenalty(boolean isPenalty)
    {
        this.IsPenalty = isPenalty;
        return this;
    }

    public GoalBuilder withScoreTeam1(int score)
    {
        this.ScoreTeam1 = score;
        return this;
    }

    public GoalBuilder withScoreTeam2(int score)
    {
        this.ScoreTeam2 = score;
        return this;
    }

    public Goal build()
    {
        Goal g = new Goal();
        g.setGoalID(this.GoalID);
        g.setScoreTeam1(this.ScoreTeam1);
        g.setScoreTeam2(this.ScoreTeam2);
        g.setPenalty(this.IsPenalty);
        g.setOwnGoal(this.IsOwnGoal);
        g.setOvertime(this.IsOvertime);

        return g;
    }
}

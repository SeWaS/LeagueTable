package org.sewas.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.sewas.domain.model.model.Goal;
import org.sewas.domain.model.model.Group;
import org.sewas.domain.model.model.Match;
import org.sewas.domain.model.model.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastian on 25/05/17.
 */
public class MatchBuilder {
    private int MatchID;
    private List<Goal> Goal = new ArrayList<Goal>();
    private Team Team1;
    private Team Team2;
    private int NumberOfViewers;
    private Group Group;

    public MatchBuilder()
    {}

    public MatchBuilder withMatchID(int MatchID)
    {
        this.MatchID = MatchID;
        return this;
    }

    public MatchBuilder withNumberOfViewers(int NumberOfViewers)
    {
        this.NumberOfViewers = NumberOfViewers;
        return this;
    }

    public MatchBuilder withTeam1(Team team)
    {
        this.Team1 = team;
        return this;
    }

    public MatchBuilder withTeam2(Team team)
    {
        this.Team2 = team;
        return this;
    }

    public MatchBuilder withGroup(Group group)
    {
        this.Group = group;
        return this;
    }

    public MatchBuilder withGoal(Goal goal)
    {
        this.Goal.add(goal);
        return this;
    }

    public Match build()
    {
        Match m = new Match();
        m.Goal = this.Goal;
        m.Team2 = this.Team2;
        m.Team1 = this.Team1;
        m.NumberOfViewers = this.NumberOfViewers;
        m.MatchID = this.MatchID;
        m.Group = this.Group;

        return m;
    }
}

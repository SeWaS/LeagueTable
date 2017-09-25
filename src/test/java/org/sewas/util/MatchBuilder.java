package org.sewas.util;

import org.sewas.domain.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastian on 25/05/17.
 */
public class MatchBuilder {
    private int MatchID;
    private List<org.sewas.domain.model.Goal> Goal = new ArrayList<Goal>();
    private Team Team1;
    private Team Team2;
    private int NumberOfViewers;
    private org.sewas.domain.model.Group Group;
    private List<MatchResult> matchResults = new ArrayList<MatchResult>();
    private Boolean isMatchFinished;

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

    public MatchBuilder withResult(MatchResult matchResult)
    {
        this.matchResults.add(matchResult);
        return this;
    }

    public MatchBuilder isFinished(Boolean isMatchFinished) {
        this.isMatchFinished = isMatchFinished;
        return this;
    }

    public Match build()
    {
        Match m = new Match();
        m.setGoal(this.Goal);
        m.setTeam1(this.Team1);
        m.setTeam2(this.Team2);
        m.setNumberOfViewers(this.NumberOfViewers);
        m.setMatchID(this.MatchID);
        m.setGroup(this.Group);
        m.setMatchResults(this.matchResults);
        m.setMatchIsFinished(this.isMatchFinished);

        return m;
    }
}

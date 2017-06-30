package org.sewas.util;


import org.sewas.domain.model.MatchResult;

/**
 * Created by sebastian on 26/05/17.
 */
public class MatchResultBuilder {

    private int PointsTeam1;
    private int PointsTeam2;
    private String ResultDescription;
    private int ResultID;
    private String ResultName;
    private int ResultOrderID;
    private int ResultTypeID;

    public MatchResultBuilder()
    {
    }

    public MatchResultBuilder withPointsForTeam1(int pointsTeam1)
    {
        this.PointsTeam1 = pointsTeam1;
        return this;
    }

    public MatchResultBuilder withPointsForTeam2(int pointsTeam2)
    {
        this.PointsTeam2 = pointsTeam2;
        return this;
    }

    public MatchResultBuilder withResultDescription(String desc)
    {
        this.ResultDescription = desc;
        return this;
    }

    public MatchResultBuilder withResultID(int resultID)
    {
        this.ResultID = resultID;
        return this;
    }

    public MatchResultBuilder withResultName(String resultName)
    {
        this.ResultName = resultName;
        return this;
    }

    public MatchResultBuilder withResultOrderID(int resultOrderID)
    {
        this.ResultOrderID = resultOrderID;
        return this;
    }

    public MatchResultBuilder withResultTypeID(int resultTypeID)
    {
        this.ResultTypeID = resultTypeID;
        return this;
    }

    public MatchResult build()
    {
        MatchResult mr = new MatchResult();
        mr.setPointsTeam1(this.PointsTeam1);
        mr.setPointsTeam2(this.PointsTeam2);
        mr.setResultDescription(this.ResultDescription);
        mr.setResultID(this.ResultID);
        mr.setResultName(this.ResultName);
        mr.setResultOrderID(this.ResultOrderID);
        mr.setResultTypeID(this.ResultTypeID);

        return mr;
    }
}

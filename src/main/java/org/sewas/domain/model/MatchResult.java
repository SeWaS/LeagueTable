package org.sewas.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by sebastian on 21/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchResult {

    private int PointsTeam1;
    private int PointsTeam2;
    private String ResultDescription;
    private int ResultID;
    private String ResultName;
    private int ResultOrderID;
    private int ResultTypeID;

    public int getPointsTeam1() {
        return PointsTeam1;
    }

    public void setPointsTeam1(int pointsTeam1) {
        PointsTeam1 = pointsTeam1;
    }

    public int getPointsTeam2() {
        return PointsTeam2;
    }

    public void setPointsTeam2(int pointsTeam2) {
        PointsTeam2 = pointsTeam2;
    }

    public String getResultDescription() {
        return ResultDescription;
    }

    public void setResultDescription(String resultDescription) {
        ResultDescription = resultDescription;
    }

    public int getResultID() {
        return ResultID;
    }

    public void setResultID(int resultID) {
        ResultID = resultID;
    }

    public String getResultName() {
        return ResultName;
    }

    public void setResultName(String resultName) {
        ResultName = resultName;
    }

    public int getResultOrderID() {
        return ResultOrderID;
    }

    public void setResultOrderID(int resultOrderID) {
        ResultOrderID = resultOrderID;
    }

    public int getResultTypeID() {
        return ResultTypeID;
    }

    public void setResultTypeID(int resultTypeID) {
        ResultTypeID = resultTypeID;
    }
}

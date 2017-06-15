package org.sewas.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by sebastian on 21/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Goal {

    @JsonProperty("GoalID")
    public int GoalID;

    @JsonProperty("IsOvertime")
    public boolean IsOvertime;

    @JsonProperty("IsOwnGoal")
    public boolean IsOwnGoal;

    @JsonProperty("IsPenalty")
    public boolean IsPenalty;

    @JsonProperty("ScoreTeam1")
    public int ScoreTeam1;

    @JsonProperty("ScoreTeam2")
    public int ScoreTeam2;

}

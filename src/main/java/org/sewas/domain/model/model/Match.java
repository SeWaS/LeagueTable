package org.sewas.domain.model.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by sebastian on 21/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match {

    @JsonProperty("MatchID")
    public int MatchID;

    @JsonProperty("Goals")
    public List<Goal> Goal;

    @JsonProperty("Team1")
    public Team Team1;

    @JsonProperty("Team2")
    public Team Team2;

    @JsonProperty("NumberOfViewers")
    public int NumberOfViewers;

    @JsonProperty("Group")
    public Group Group;

    @JsonProperty("MatchResults")
    public List<MatchResult> matchResults;
}

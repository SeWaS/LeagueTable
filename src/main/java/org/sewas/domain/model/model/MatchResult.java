package org.sewas.domain.model.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by sebastian on 21/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchResult {

    @JsonProperty("PointsTeam1")
    public int PointsTeam1;

    @JsonProperty("PointsTeam2")
    public int PointsTeam2;

    @JsonProperty("ResultDescription")
    public String ResultDescription;

    @JsonProperty("ResultID")
    public int ResultID;

    @JsonProperty("ResultName")
    public String ResultName;

    @JsonProperty("ResultOrderID")
    public int ResultOrderID;

    @JsonProperty("ResultTypeID")
    public int ResultTypeID;
}

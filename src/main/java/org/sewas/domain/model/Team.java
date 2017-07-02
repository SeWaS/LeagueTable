package org.sewas.domain.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by sebastian on 21/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE,
        setterVisibility=JsonAutoDetect.Visibility.NONE, creatorVisibility=JsonAutoDetect.Visibility.NONE)
public class Team {

    @JsonProperty("TeamIconUrl")
    private String TeamIconUrl;

    @JsonProperty("TeamId")
    private int TeamId;

    @JsonProperty("TeamName")
    private String TeamName;

    public String getTeamIconUrl() {
        return TeamIconUrl;
    }

    public void setTeamIconUrl(String teamIconUrl) {
        TeamIconUrl = teamIconUrl;
    }

    public int getTeamId() {
        return TeamId;
    }

    public void setTeamId(int teamId) {
        TeamId = teamId;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }
}

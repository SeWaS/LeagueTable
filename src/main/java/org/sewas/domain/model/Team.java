package org.sewas.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by sebastian on 21/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {

    private String TeamIconUrl;
    private int TeamId;
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

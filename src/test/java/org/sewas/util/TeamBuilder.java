package org.sewas.util;

import org.sewas.domain.model.Team;

/**
 * Created by sebastian on 25/05/17.
 */
public class TeamBuilder {
    private String TeamIconUrl;
    private int TeamId;
    private String TeamName;

    public TeamBuilder()
    {}

    public TeamBuilder withTeamName(String Teamname)
    {
        this.TeamName = Teamname;
        return this;
    }

    public TeamBuilder withTeamId(int TeamId)
    {
        this.TeamId = TeamId;
        return this;
    }

    public TeamBuilder withTeamIcon(String url)
    {
        this.TeamIconUrl = url;
        return this;
    }

    public Team build(){
        Team t = new Team();
        t.setTeamName(this.TeamName);
        t.setTeamIconUrl(this.TeamIconUrl);
        t.setTeamId(this.TeamId);

        return t;
    }

}

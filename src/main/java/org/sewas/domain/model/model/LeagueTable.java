package org.sewas.domain.model.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastian on 21/05/17.
 */
public class LeagueTable {

    private String leagueID;
    private List<TeamPosition> table = new ArrayList<TeamPosition>();

    public List<TeamPosition> getTable() {
        return table;
    }

    public String getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(String leagueID) {
        this.leagueID = leagueID;
    }

    public void addTeam(Team t) {
        TeamPosition tp = new TeamPosition();
        tp.setPoints(0);
        tp.setTeam(t);
        this.table.add(tp);
    }
}

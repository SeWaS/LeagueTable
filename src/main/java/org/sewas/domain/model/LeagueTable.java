package org.sewas.domain.model;

import org.sewas.util.TeamPositionComparator;

import java.util.ArrayList;
import java.util.Collections;
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

    public void addTeamPosition(TeamPosition tp)
    {
        this.table.add(tp);
    }

    public void sortTableByPoints()
    {
        Collections.sort(this.table, new TeamPositionComparator());

        for(TeamPosition t : this.table)
        {
            int index = this.table.indexOf(t);

            t.setPosition(index+1);

            if(index > 0 && (this.table.get(index).getPoints() == this.table.get(index-1).getPoints()))
            {
                t.setPosition(index);
            }
        }
    }
}

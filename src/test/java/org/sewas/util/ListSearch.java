package org.sewas.util;

import org.sewas.domain.model.model.LeagueTable;
import org.sewas.domain.model.model.TeamPosition;

import java.util.List;

/**
 * Created by sebastian on 28/05/17.
 */
public class ListSearch {

    public TeamPosition findTeamPositionByTeamname(String teamname, LeagueTable leagueTable) {
        for(TeamPosition t : leagueTable.getTable()) {
            if(t.getTeam().TeamName.equals(teamname)) {
                return t;
            }
        }
        return null;
    }

}
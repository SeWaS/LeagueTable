package org.sewas.util;

import org.sewas.domain.model.model.TeamPosition;

import java.util.Comparator;

public class TeamPositionComparator implements Comparator<TeamPosition>
{
    @Override
    public int compare(TeamPosition t1, TeamPosition t2) {
        if (t1.getPoints() > t2.getPoints())
            return -1;
        if (t1.getPoints() < t2.getPoints())
            return 1;
        return 0;
    }
}
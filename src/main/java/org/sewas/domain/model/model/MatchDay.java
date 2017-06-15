package org.sewas.domain.model.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastian on 15/06/17.
 */
public class MatchDay {

    private int matchDay;
    private List<Match> matches = new ArrayList<Match>();

    public int getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(int matchDay) {
        this.matchDay = matchDay;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void addMatch(Match m) {
        this.matches.add(m);
    }
}

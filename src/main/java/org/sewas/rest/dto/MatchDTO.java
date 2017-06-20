package org.sewas.rest.dto;

import org.sewas.domain.model.Match;

import java.util.List;

/**
 * Created by sebastian on 25/05/17.
 */
public class MatchDTO {
    private int statusCode;
    private List<Match> matches;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}

package org.sewas.rest.dto;

import org.sewas.domain.model.Match;

/**
 * Created by sebastian on 25/05/17.
 */
public class MatchDTO {
    private int statusCode;
    private Match[] matches;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Match[] getMatches() {
        return matches;
    }

    public void setMatches(Match[] matches) {
        this.matches = matches;
    }
}

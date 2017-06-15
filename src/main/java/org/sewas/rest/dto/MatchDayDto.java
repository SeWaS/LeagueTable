package org.sewas.rest.dto;

import org.sewas.domain.model.model.MatchDay;

import java.util.List;

/**
 * Created by sebastian on 15/06/17.
 */
public class MatchDayDto {
    private int status;
    private List<MatchDay> matchDays;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<MatchDay> getMatchDays() {
        return matchDays;
    }

    public void setMatchDays(List<MatchDay> matchDays) {
        this.matchDays = matchDays;
    }
}

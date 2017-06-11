package org.sewas.domain.model.model;

/**
 * Created by sebastian on 22/05/17.
 */
public class TeamPosition {
    private Team team;
    private int points;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getPoints() {
        return points;
    }

    public void addVictory() {
        this.points = this.points + 3;
    }

    public void addTie() {
        this.points = this.points + 1;
    }
}

package org.sewas.domain.model;

/**
 * Created by sebastian on 22/05/17.
 */
public class TeamPosition {
    private Team team;
    private int points;
    private int position;
    private int numberOfMatchDays;
    private int numberOfVictories;
    private int numberOfTies;
    private int numberOfLoss;
    private int goalsFor;
    private int goalsAgainst;

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
        this.numberOfMatchDays = this.numberOfMatchDays +1;
        this.numberOfVictories = this.numberOfVictories +1;
    }

    public void addTie() {
        this.points = this.points + 1;
        this.numberOfMatchDays = this.numberOfMatchDays +1;
        this.numberOfTies = this.numberOfTies +1;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getNumberOfMatchDays() {
        return numberOfMatchDays;
    }

    public int getNumberOfVictories() {
        return numberOfVictories;
    }

    public int getNumberOfTies() {
        return numberOfTies;
    }

    public int getNumberOfLoss() {
        return numberOfLoss;
    }

    public void addLoss() {
        this.numberOfMatchDays = this.numberOfMatchDays +1;
        this.numberOfLoss = this.numberOfLoss +1;
    }

    public void addGoalsFor(int goals) {
        this.goalsFor = this.goalsFor + goals;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void addGoalsAgainst(int goals) {
        this.goalsAgainst = this.goalsAgainst + goals;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }
}

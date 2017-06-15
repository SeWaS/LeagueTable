package org.sewas.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.sewas.client.OpenLigaDBClient;
import org.sewas.rest.dto.MatchDTO;
import org.sewas.domain.model.model.*;
import org.sewas.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

/**
 * Created by sebastian on 21/05/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class LeagueTableServiceTest {

    @InjectMocks
    private LeagueTableService underTest;

    @Mock
    private OpenLigaDBClient mockClient;

    MatchDTO validMockDTO;
    ListSearch listSearch;

    @Before
    public void setUp(){
        this.listSearch = new ListSearch();

        this.validMockDTO = new MatchDTO();
        this.validMockDTO.setStatusCode(200);

        Match match1 = new MatchBuilder()
                .withMatchID(123)
                .withNumberOfViewers(10001)
                .withTeam1(new TeamBuilder()
                    .withTeamId(1)
                    .withTeamName("HomeTeam")
                    .withTeamIcon("path/to/hometeamicon.png")
                    .build())
                .withTeam2(new TeamBuilder()
                    .withTeamId(2)
                    .withTeamName("GuestTeam")
                    .withTeamIcon("path/to/guestteamicon.jpeg")
                    .build())
                .withGroup(new GroupBuilder()
                    .withGroupID(99)
                    .withGroupName("MyGroupName")
                    .withGroupOrderID(1)
                    .build())
                .withGoal(new GoalBuilder()
                    .withScoreTeam1(0)
                    .withScoreTeam2(1)
                    .build())
                .withGoal(new GoalBuilder()
                    .withScoreTeam1(0)
                    .withScoreTeam2(2)
                    .isOwnGoal(true)
                    .build())
                .withResult(new MatchResultBuilder()
                        .withResultID(1)
                        .withPointsForTeam1(0)
                        .withPointsForTeam2(1)
                        .build())
                .withResult(new MatchResultBuilder()
                        .withResultID(2)
                        .withPointsForTeam1(0)
                        .withPointsForTeam2(2)
                        .build())
                .build();

        Match match2 = new MatchBuilder()
                .withMatchID(124)
                .withNumberOfViewers(19000)
                .withTeam1(new TeamBuilder()
                        .withTeamId(3)
                        .withTeamName("AnotherHomeTeam")
                        .withTeamIcon("path/to/hometeamicon2.png")
                        .build())
                .withTeam2(new TeamBuilder()
                        .withTeamId(4)
                        .withTeamName("AnotherGuestTeam")
                        .withTeamIcon("path/to/guestteamicon2.jpeg")
                        .build())
                .withGroup(new GroupBuilder()
                        .withGroupID(99)
                        .withGroupName("NyGroupName")
                        .withGroupOrderID(1)
                        .build())
                .withGoal(new GoalBuilder()
                        .withScoreTeam1(1)
                        .withScoreTeam2(0)
                        .isPenalty(true)
                        .build())
                .withGoal(new GoalBuilder()
                        .withScoreTeam1(1)
                        .withScoreTeam2(1)
                        .build())
                .withResult(new MatchResultBuilder()
                    .withResultID(1)
                    .withPointsForTeam1(0)
                    .withPointsForTeam2(0)
                    .build())
                .withResult(new MatchResultBuilder()
                    .withResultID(2)
                    .withPointsForTeam1(1)
                    .withPointsForTeam2(1)
                    .build())
                .build();

        Match match3 = new MatchBuilder()
                .withMatchID(125)
                .withNumberOfViewers(99000)
                .withTeam1(new TeamBuilder()
                        .withTeamId(5)
                        .withTeamName("WinningHomeTeam")
                        .withTeamIcon("Icon.gif")
                        .build())
                .withTeam2(new TeamBuilder()
                        .withTeamId(6)
                        .withTeamName("LosingGuestTeam")
                        .withTeamIcon("logingGuestTeamIcon.png")
                        .build())
                .withGroup(new GroupBuilder()
                        .withGroupID(99)
                        .withGroupName("NyGroupName")
                        .withGroupOrderID(1)
                        .build())
                .withGoal(new GoalBuilder()
                        .withScoreTeam1(1)
                        .withScoreTeam2(0)
                        .isPenalty(true)
                        .build())
                .withGoal(new GoalBuilder()
                        .withScoreTeam1(1)
                        .withScoreTeam2(1)
                        .build())
                .withGoal(new GoalBuilder()
                        .withScoreTeam1(2)
                        .withScoreTeam2(1)
                        .build())
                .withResult(new MatchResultBuilder()
                        .withResultID(1)
                        .withPointsForTeam1(0)
                        .withPointsForTeam2(0)
                        .build())
                .withResult(new MatchResultBuilder()
                        .withResultID(2)
                        .withPointsForTeam1(2)
                        .withPointsForTeam2(1)
                        .build())
                .build();
        Match match3 = new MatchBuilder()
                .withMatchID(125)
                .withNumberOfViewers(99000)
                .withTeam1(new TeamBuilder()
                        .withTeamId(5)
                        .withTeamName("WinningHomeTeam")
                        .withTeamIcon("Icon.gif")
                        .build())
                .withTeam2(new TeamBuilder()
                        .withTeamId(6)
                        .withTeamName("LosingGuestTeam")
                        .withTeamIcon("logingGuestTeamIcon.png")
                        .build())
                .withGroup(new GroupBuilder()
                        .withGroupID(99)
                        .withGroupName("NyGroupName")
                        .withGroupOrderID(1)
                        .build())
                .withGoal(new GoalBuilder()
                        .withScoreTeam1(1)
                        .withScoreTeam2(0)
                        .isPenalty(true)
                        .build())
                .withGoal(new GoalBuilder()
                        .withScoreTeam1(1)
                        .withScoreTeam2(1)
                        .build())
                .withGoal(new GoalBuilder()
                        .withScoreTeam1(2)
                        .withScoreTeam2(1)
                        .build())
                .withResult(new MatchResultBuilder()
                        .withResultID(1)
                        .withPointsForTeam1(0)
                        .withPointsForTeam2(0)
                        .build())
                .withResult(new MatchResultBuilder()
                        .withResultID(2)
                        .withPointsForTeam1(2)
                        .withPointsForTeam2(1)
                        .build())
                .build();

        Match[] mockMatches = new Match[]{match1, match2, match3};

        this.validMockDTO.setMatches(mockMatches);

        given(this.mockClient.getMatchesForLeague(any(String.class))).willReturn(this.validMockDTO);
    }

    @Test
    public void leagueTableService_must_return_not_empty_LeagueTable_for_valid_client_call()
    {
        LeagueTable receivedLeagueTable = this.underTest.returnCurrentLeagueTable("anyLeagueID");

        assertThat(receivedLeagueTable.getLeagueID()).isEqualTo("anyLeagueID");
        assertThat(receivedLeagueTable.getTable().size()).isEqualTo(6);
    }

    @Test
    public void leagueTableService_must_calculate_correct_points()
    {
        LeagueTable receivedLeagueTable = this.underTest.returnCurrentLeagueTable("anyLeagueID");

        TeamPosition tp1 = this.listSearch.findTeamPositionByTeamname("HomeTeam", receivedLeagueTable);
        TeamPosition tp2 = this.listSearch.findTeamPositionByTeamname("GuestTeam", receivedLeagueTable);
        TeamPosition tp3 = this.listSearch.findTeamPositionByTeamname("AnotherHomeTeam", receivedLeagueTable);
        TeamPosition tp4 = this.listSearch.findTeamPositionByTeamname("AnotherGuestTeam", receivedLeagueTable);
        TeamPosition tp5 = this.listSearch.findTeamPositionByTeamname("WinningHomeTeam", receivedLeagueTable);
        TeamPosition tp6 = this.listSearch.findTeamPositionByTeamname("LosingGuestTeam", receivedLeagueTable);

        assertThat(tp1.getPoints()).isEqualTo(0);
        assertThat(tp2.getPoints()).isEqualTo(3);
        assertThat(tp3.getPoints()).isEqualTo(1);
        assertThat(tp4.getPoints()).isEqualTo(1);
        assertThat(tp5.getPoints()).isEqualTo(3);
        assertThat(tp6.getPoints()).isEqualTo(0);
    }

    @Test
    public void leagueTableService_must_calculate_correct_position()
    {
        LeagueTable receivedLeagueTable = this.underTest.returnCurrentLeagueTable("anyLeagueID");

        TeamPosition tp1 = this.listSearch.findTeamPositionByTeamname("HomeTeam", receivedLeagueTable);
        TeamPosition tp2 = this.listSearch.findTeamPositionByTeamname("GuestTeam", receivedLeagueTable);
        TeamPosition tp3 = this.listSearch.findTeamPositionByTeamname("AnotherHomeTeam", receivedLeagueTable);
        TeamPosition tp4 = this.listSearch.findTeamPositionByTeamname("AnotherGuestTeam", receivedLeagueTable);
        TeamPosition tp5 = this.listSearch.findTeamPositionByTeamname("WinningHomeTeam", receivedLeagueTable);
        TeamPosition tp6 = this.listSearch.findTeamPositionByTeamname("LosingGuestTeam", receivedLeagueTable);

        assertThat(tp1.getPosition()).isEqualTo(5);
        assertThat(tp2.getPosition()).isEqualTo(1);
        assertThat(tp3.getPosition()).isEqualTo(3);
        assertThat(tp4.getPosition()).isEqualTo(3);
        assertThat(tp5.getPosition()).isEqualTo(1);
        assertThat(tp6.getPosition()).isEqualTo(5);
    }
}

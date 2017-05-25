package org.sewas.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.sewas.client.OpenLigaDBClient;
import org.sewas.domain.model.dto.MatchDTO;
import org.sewas.domain.model.model.*;
import org.sewas.util.*;
import service.LeagueTableService;

import java.util.ArrayList;
import java.util.Arrays;

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

    @Before
    public void setUp(){
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
                    .withGroupName("NyGroupName")
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
                        .withPointsForTeam1(1)
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

        Match[] mockMatches = new Match[]{match1, match2};

        this.validMockDTO.setMatches(mockMatches);
    }

    @Test
    public void leagueTableService_must_return_not_empty_LeagueTable_for_valid_client_call(){

        given(this.mockClient.getMatchesForLeague(any(String.class))).willReturn(this.validMockDTO);

        LeagueTable receivedLeagueTable = this.underTest.returnCurrentLeagueTable("anyLeagueID");

        assertThat(receivedLeagueTable.getLeagueID()).isEqualTo("anyLeagueID");
        assertThat(receivedLeagueTable.getTable().size()).isEqualTo(4);
    }

}

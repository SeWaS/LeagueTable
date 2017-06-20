package org.sewas.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.sewas.client.OpenLigaDBClient;
import org.sewas.domain.model.LeagueTable;
import org.sewas.domain.model.Match;
import org.sewas.domain.model.TeamPosition;
import org.sewas.exception.MatchdayNotAvailableException;
import org.sewas.exception.OpenLigaDbNotOkException;
import org.sewas.exception.SeasonNotAvailableException;
import org.sewas.rest.dto.MatchDTO;
import org.sewas.util.ListSearch;
import org.sewas.util.MatchBuilder;
import org.sewas.util.MatchResultBuilder;
import org.sewas.util.TeamBuilder;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

/**
 * Created by sebastian on 19/06/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class LeagueTableServiceMatchdayTest {

    @InjectMocks
    private LeagueTableService underTest;

    @Mock
    private OpenLigaDBClient mockClient;

    TeamPosition tp1;
    TeamPosition tp2;
    TeamPosition tp3;
    TeamPosition tp4;

    @Before
    public void setUp() throws SeasonNotAvailableException, OpenLigaDbNotOkException, MatchdayNotAvailableException {

        // given
        Match match1 = new MatchBuilder()
                .withTeam1(new TeamBuilder()
                        .withTeamName("Team1")
                        .build())
                .withTeam2(new TeamBuilder()
                        .withTeamName("Team2")
                        .build())
                .withResult(new MatchResultBuilder()
                        .withPointsForTeam1(0)
                        .withPointsForTeam2(0)
                        .build())
                .withResult(new MatchResultBuilder()
                        .withPointsForTeam1(1)
                        .withPointsForTeam2(1)
                        .build())
                .build();

        Match match2 = new MatchBuilder()
                .withTeam1(new TeamBuilder()
                        .withTeamName("Team3")
                        .build())
                .withTeam2(new TeamBuilder()
                        .withTeamName("Team4")
                        .build())
                .withResult(new MatchResultBuilder()
                        .withPointsForTeam1(0)
                        .withPointsForTeam2(0)
                        .build())
                .withResult(new MatchResultBuilder()
                        .withPointsForTeam1(2)
                        .withPointsForTeam2(1)
                        .build())
                .build();

        MatchDTO mockMatchDtoDay1 = new MatchDTO();
        mockMatchDtoDay1.setMatches(Arrays.asList(match1, match2));

        Match match3 = new MatchBuilder()
                .withTeam1(new TeamBuilder()
                        .withTeamName("Team1")
                        .build())
                .withTeam2(new TeamBuilder()
                        .withTeamName("Team4")
                        .build())
                .withResult(new MatchResultBuilder()
                        .withPointsForTeam1(0)
                        .withPointsForTeam2(0)
                        .build())
                .withResult(new MatchResultBuilder()
                        .withPointsForTeam1(3)
                        .withPointsForTeam2(1)
                        .build())
                .build();

        Match match4 = new MatchBuilder()
                .withTeam1(new TeamBuilder()
                        .withTeamName("Team2")
                        .build())
                .withTeam2(new TeamBuilder()
                        .withTeamName("Team3")
                        .build())
                .withResult(new MatchResultBuilder()
                        .withPointsForTeam1(0)
                        .withPointsForTeam2(0)
                        .build())
                .withResult(new MatchResultBuilder()
                        .withPointsForTeam1(0)
                        .withPointsForTeam2(1)
                        .build())
                .build();

        MatchDTO mockMatchDtoDay2 = new MatchDTO();
        mockMatchDtoDay2.setMatches(Arrays.asList(match3, match4));

        Match match5 = new MatchBuilder()
                .withTeam1(new TeamBuilder()
                        .withTeamName("Team2")
                        .build())
                .withTeam2(new TeamBuilder()
                        .withTeamName("Team4")
                        .build())
                .withResult(new MatchResultBuilder()
                        .withPointsForTeam1(0)
                        .withPointsForTeam2(0)
                        .build())
                .withResult(new MatchResultBuilder()
                        .withPointsForTeam1(1)
                        .withPointsForTeam2(2)
                        .build())
                .build();

        Match match6 = new MatchBuilder()
                .withTeam1(new TeamBuilder()
                        .withTeamName("Team3")
                        .build())
                .withTeam2(new TeamBuilder()
                        .withTeamName("Team1")
                        .build())
                .withResult(new MatchResultBuilder()
                        .withPointsForTeam1(0)
                        .withPointsForTeam2(0)
                        .build())
                .withResult(new MatchResultBuilder()
                        .withPointsForTeam1(2)
                        .withPointsForTeam2(2)
                        .build())
                .build();

        MatchDTO mockMatchDtoDay3 = new MatchDTO();
        mockMatchDtoDay3.setMatches(Arrays.asList(match5, match6));

        given(this.mockClient.getMatchesForLeagueMatchday(anyString(), anyString(), anyString()))
                .willReturn(mockMatchDtoDay1)
                .willReturn(mockMatchDtoDay2)
                .willReturn(mockMatchDtoDay3);

        // when
        LeagueTable receivedLeagueTable = this.underTest.returnMatchdayLeagueTable("anyLeagueID", "anyYear", "3");

        ListSearch listSearch = new ListSearch();
        this.tp1 = listSearch.findTeamPositionByTeamname("Team1", receivedLeagueTable);
        this.tp2 = listSearch.findTeamPositionByTeamname("Team2", receivedLeagueTable);
        this.tp3 = listSearch.findTeamPositionByTeamname("Team3", receivedLeagueTable);
        this.tp4 = listSearch.findTeamPositionByTeamname("Team4", receivedLeagueTable);
    }

    @Test
    public void shouldCalculatePoints() {
        // then
        assertThat(this.tp1.getPoints()).isEqualTo(5);
        assertThat(this.tp2.getPoints()).isEqualTo(1);
        assertThat(this.tp3.getPoints()).isEqualTo(7);
        assertThat(this.tp4.getPoints()).isEqualTo(3);
    }

    @Test
    public void shouldCalculatePositions() {
        // then
        assertThat(this.tp1.getPosition()).isEqualTo(2);
        assertThat(this.tp2.getPosition()).isEqualTo(4);
        assertThat(this.tp3.getPosition()).isEqualTo(1);
        assertThat(this.tp4.getPosition()).isEqualTo(3);
    }

    @Test
    public void shouldCalculateVictoryTieLoss() {
        // then
        assertThat(tp1.getNumberOfLoss()).isEqualTo(0);
        assertThat(tp1.getNumberOfTies()).isEqualTo(2);
        assertThat(tp1.getNumberOfVictories()).isEqualTo(1);
        assertThat(tp1.getNumberOfMatchDays()).isEqualTo(3);

        assertThat(tp2.getNumberOfLoss()).isEqualTo(2);
        assertThat(tp2.getNumberOfTies()).isEqualTo(1);
        assertThat(tp2.getNumberOfVictories()).isEqualTo(0);
        assertThat(tp2.getNumberOfMatchDays()).isEqualTo(3);

        assertThat(tp3.getNumberOfLoss()).isEqualTo(0);
        assertThat(tp3.getNumberOfTies()).isEqualTo(1);
        assertThat(tp3.getNumberOfVictories()).isEqualTo(2);
        assertThat(tp3.getNumberOfMatchDays()).isEqualTo(3);

        assertThat(tp4.getNumberOfLoss()).isEqualTo(2);
        assertThat(tp4.getNumberOfTies()).isEqualTo(0);
        assertThat(tp4.getNumberOfVictories()).isEqualTo(1);
        assertThat(tp4.getNumberOfMatchDays()).isEqualTo(3);
    }
}

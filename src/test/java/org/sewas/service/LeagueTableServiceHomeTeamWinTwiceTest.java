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
 * Created by sebastian on 16/06/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class LeagueTableServiceHomeTeamWinTwiceTest {

    @InjectMocks
    private LeagueTableService underTest;

    @Mock
    private OpenLigaDBClient mockClient;

    TeamPosition tp1;
    TeamPosition tp2;

    @Before
    public void setUp() throws SeasonNotAvailableException, OpenLigaDbNotOkException {
        ListSearch listSearch = new ListSearch();

        // given
        Match mockMatch1 = new MatchBuilder()
                .withTeam1(new TeamBuilder()
                        .withTeamName("HomeTeam")
                        .build())
                .withTeam2(new TeamBuilder()
                        .withTeamName("GuestTeam")
                        .build())
                .withResult(new MatchResultBuilder()
                        .withPointsForTeam1(0)
                        .withPointsForTeam2(0)
                        .build())
                .withResult(new MatchResultBuilder()
                        .withPointsForTeam1(1)
                        .withPointsForTeam2(0)
                        .build())
                .isFinished(true)
                .build();

        Match mockMatch2 = new MatchBuilder()
                .withTeam1(new TeamBuilder()
                        .withTeamName("HomeTeam")
                        .build())
                .withTeam2(new TeamBuilder()
                        .withTeamName("GuestTeam")
                        .build())
                .withResult(new MatchResultBuilder()
                        .withPointsForTeam1(0)
                        .withPointsForTeam2(0)
                        .build())
                .withResult(new MatchResultBuilder()
                        .withPointsForTeam1(1)
                        .withPointsForTeam2(0)
                        .build())
                .isFinished(true)
                .build();

        MatchDTO mockMatchDto = new MatchDTO();
        mockMatchDto.setMatches(Arrays.asList(mockMatch1, mockMatch2));

        given(this.mockClient.getMatchesForLeagueMatchday(anyString(), anyString())).willReturn(mockMatchDto);

        // when
        LeagueTable receivedLeagueTable = this.underTest.returnCurrentLeagueTable("anyLeagueID", "anyYear");

        this.tp1 = listSearch.findTeamPositionByTeamname("HomeTeam", receivedLeagueTable);
        this.tp2 = listSearch.findTeamPositionByTeamname("GuestTeam", receivedLeagueTable);
    }

    @Test
    public void shouldCalculatePoints() {
        // then
        assertThat(this.tp1.getPoints()).isEqualTo(6);
        assertThat(this.tp2.getPoints()).isEqualTo(0);
    }

    @Test
    public void shouldCalculatePositions() {
        // then
        assertThat(this.tp1.getPosition()).isEqualTo(1);
        assertThat(this.tp2.getPosition()).isEqualTo(2);
    }

    @Test
    public void shouldCalculateVictoryTieLoss() {
        // then
        assertThat(tp1.getNumberOfLoss()).isEqualTo(0);
        assertThat(tp1.getNumberOfTies()).isEqualTo(0);
        assertThat(tp1.getNumberOfVictories()).isEqualTo(2);
        assertThat(tp1.getNumberOfMatchDays()).isEqualTo(2);

        assertThat(tp2.getNumberOfLoss()).isEqualTo(2);
        assertThat(tp2.getNumberOfTies()).isEqualTo(0);
        assertThat(tp2.getNumberOfVictories()).isEqualTo(0);
        assertThat(tp2.getNumberOfMatchDays()).isEqualTo(2);
    }

    @Test
    public void shouldCalculateGoalDifference() {
        // then
        assertThat(tp1.getGoalsFor()).isEqualTo(2);
        assertThat(tp1.getGoalsAgainst()).isEqualTo(0);

        assertThat(tp2.getGoalsFor()).isEqualTo(0);
        assertThat(tp2.getGoalsAgainst()).isEqualTo(2);
    }
}

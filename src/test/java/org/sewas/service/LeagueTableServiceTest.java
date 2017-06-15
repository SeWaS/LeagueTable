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
import org.sewas.exception.SeasonNotAvailableException;
import org.sewas.rest.dto.MatchDTO;
import org.sewas.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

/**
 * Created by sebastian on 21/05/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class LeagueTableServiceTest {

    @InjectMocks
    private LeagueTableService underTest;

    @Mock
    private OpenLigaDBClient mockClient;

    ListSearch listSearch;

    @Before
    public void setUp() {
        this.listSearch = new ListSearch();
    }

    @Test
    public void shouldAddThreePointsToHomeTeamAtFirstGame() throws SeasonNotAvailableException {

        // given
        Match mockMatch = new MatchBuilder()
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
                .build();

        MatchDTO mockMatchDto = new MatchDTO();
        mockMatchDto.setMatches(new Match[] {mockMatch});

        given(this.mockClient.getMatchesForLeague(anyString(), anyString())).willReturn(mockMatchDto);

        // when
        LeagueTable receivedLeagueTable = this.underTest.returnCurrentLeagueTable("anyLeagueID", "anyYear");

        TeamPosition tp1 = this.listSearch.findTeamPositionByTeamname("HomeTeam", receivedLeagueTable);
        TeamPosition tp2 = this.listSearch.findTeamPositionByTeamname("GuestTeam", receivedLeagueTable);

        // then
        assertThat(tp1.getPoints()).isEqualTo(3);
        assertThat(tp2.getPoints()).isEqualTo(0);
        assertThat(tp1.getPosition()).isEqualTo(1);
        assertThat(tp2.getPosition()).isEqualTo(2);

    }

    @Test
    public void shouldAddThreePointsToGuestTeamAtFirstGame() throws SeasonNotAvailableException {

        // given
        Match mockMatch = new MatchBuilder()
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
                        .withPointsForTeam1(0)
                        .withPointsForTeam2(1)
                        .build())
                .build();

        MatchDTO mockMatchDto = new MatchDTO();
        mockMatchDto.setMatches(new Match[] {mockMatch});

        given(this.mockClient.getMatchesForLeague(anyString(), anyString())).willReturn(mockMatchDto);

        // when
        LeagueTable receivedLeagueTable = this.underTest.returnCurrentLeagueTable("anyLeagueID", "anyYear");

        TeamPosition tp1 = this.listSearch.findTeamPositionByTeamname("HomeTeam", receivedLeagueTable);
        TeamPosition tp2 = this.listSearch.findTeamPositionByTeamname("GuestTeam", receivedLeagueTable);

        // then
        assertThat(tp1.getPoints()).isEqualTo(0);
        assertThat(tp2.getPoints()).isEqualTo(3);
        assertThat(tp1.getPosition()).isEqualTo(2);
        assertThat(tp2.getPosition()).isEqualTo(1);

    }

    @Test
    public void shouldAddOnePointToBothTeamsAtFirstGame() throws SeasonNotAvailableException {

        // given
        Match mockMatch = new MatchBuilder()
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
                        .withPointsForTeam2(1)
                        .build())
                .build();

        MatchDTO mockMatchDto = new MatchDTO();
        mockMatchDto.setMatches(new Match[] {mockMatch});

        given(this.mockClient.getMatchesForLeague(anyString(), anyString())).willReturn(mockMatchDto);

        // when
        LeagueTable receivedLeagueTable = this.underTest.returnCurrentLeagueTable("anyLeagueID", "anyYear");

        TeamPosition tp1 = this.listSearch.findTeamPositionByTeamname("HomeTeam", receivedLeagueTable);
        TeamPosition tp2 = this.listSearch.findTeamPositionByTeamname("GuestTeam", receivedLeagueTable);

        // then
        assertThat(tp1.getPoints()).isEqualTo(1);
        assertThat(tp2.getPoints()).isEqualTo(1);
        assertThat(tp1.getPosition()).isEqualTo(1);
        assertThat(tp2.getPosition()).isEqualTo(1);

    }

    @Test
    public void shouldAddSixPointsToHomeTeamAtTwoGames() throws SeasonNotAvailableException {

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
                .build();

        MatchDTO mockMatchDto = new MatchDTO();
        mockMatchDto.setMatches(new Match[] {mockMatch1, mockMatch2});

        given(this.mockClient.getMatchesForLeague(anyString(), anyString())).willReturn(mockMatchDto);

        // when
        LeagueTable receivedLeagueTable = this.underTest.returnCurrentLeagueTable("anyLeagueID", "anyYear");

        TeamPosition tp1 = this.listSearch.findTeamPositionByTeamname("HomeTeam", receivedLeagueTable);
        TeamPosition tp2 = this.listSearch.findTeamPositionByTeamname("GuestTeam", receivedLeagueTable);

        // then
        assertThat(tp1.getPoints()).isEqualTo(6);
        assertThat(tp2.getPoints()).isEqualTo(0);
        assertThat(tp1.getPosition()).isEqualTo(1);
        assertThat(tp2.getPosition()).isEqualTo(2);

    }
}

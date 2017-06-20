package org.sewas.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.sewas.client.OpenLigaDBClient;
import org.sewas.domain.model.Match;
import org.sewas.exception.MatchdayNotAvailableException;
import org.sewas.exception.OpenLigaDbNotOkException;
import org.sewas.rest.dto.MatchDTO;
import org.sewas.util.MatchBuilder;
import org.sewas.util.MatchResultBuilder;
import org.sewas.util.TeamBuilder;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

/**
 * Created by sebastian on 20/06/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class LeagueTableServiceMatchdayNotAvailableTest {

    @InjectMocks
    private LeagueTableService underTest;

    @Mock
    private OpenLigaDBClient mockClient;

    @Test(expected = MatchdayNotAvailableException.class)
    public void shouldThrowExceptionIfMatchdayIsNotAnInt() throws MatchdayNotAvailableException, OpenLigaDbNotOkException {
        this.underTest.returnMatchdayLeagueTable("anyLeagueID", "anySeason", "invalidMatchday");
    }

    @Test(expected = MatchdayNotAvailableException.class)
    public void shouldThrowExceptionIfMatchdayIsNotAvailable() throws OpenLigaDbNotOkException, MatchdayNotAvailableException {

        // given
        given(this.mockClient.getMatchesForLeagueMatchday(anyString(), anyString(), anyString())).willThrow(MatchdayNotAvailableException.class);

        // when
        this.underTest.returnMatchdayLeagueTable("anyLeague", "anySeason", "3");
    }

}

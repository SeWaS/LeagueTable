package org.sewas.client;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.sewas.domain.model.dto.MatchDTO;
import org.sewas.domain.model.model.Match;
import org.sewas.util.testmarker.IntegrationTest;
import org.springframework.http.*;
import org.springframework.web.client.RestOperations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

/**
 * Created by sebastian on 28/05/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class OpenLigaDBClientTest {

    @InjectMocks
    private OpenLigaDBClient underTest;

    @Mock
    private RestOperations restTemplate;

    @Test
    public void openLigaDBClient_must_return_MatchDTO_with_status_of_response()
    {
        ResponseEntity<Match[]> validMockMatches = new ResponseEntity<Match[]>(new Match[0] ,HttpStatus.OK);

        given(this.restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), eq(Match[].class)))
                .willReturn(validMockMatches);

        MatchDTO result = this.underTest.getMatchesForLeague("myLeagueID");

        assertThat(result.getStatusCode()).isEqualTo(200);
        assertThat(result.getMatches().length).isEqualTo(0);
    }
}

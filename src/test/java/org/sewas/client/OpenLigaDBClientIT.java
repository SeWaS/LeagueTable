package org.sewas.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.sewas.config.ClientTestConfig;
import org.sewas.domain.model.Match;
import org.sewas.exception.SeasonNotAvailableException;
import org.sewas.rest.dto.MatchDTO;
import org.sewas.util.testmarker.IntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by sebastian on 11/06/17.
 */
@Category(IntegrationTest.class)
@RunWith(SpringRunner.class)
@RestClientTest(OpenLigaDBClient.class)
@ContextConfiguration(classes = {ClientTestConfig.class})
public class OpenLigaDBClientIT {

    @Autowired
    private OpenLigaDBClient openLigaDBClient;

    @Autowired
    private MockRestServiceServer server;

    ObjectMapper o;

    @Before
    public void setUp() {
        this.o = new ObjectMapper();
    }

    @Test
    public void shouldReturnMatchDTOForExistingLeague() throws JsonProcessingException, SeasonNotAvailableException {

        Match[] responseMatches = {new Match(), new Match(), new Match()};

        this.server.expect(requestTo("http://localhost:1234/api/getmatchdata/myLeague/anySeason"))
                .andRespond(withSuccess(o.writeValueAsString(responseMatches), MediaType.APPLICATION_JSON));

        MatchDTO result = this.openLigaDBClient.getMatchesForLeague("myLeague", "anySeason");

        assertThat(result.getStatusCode()).isEqualTo(200);
        assertThat(result.getMatches()).hasSize(3);
    }

    @Test(expected = SeasonNotAvailableException.class)
    public void shouldThrowExceptionIfNoMatchesForSeasonFound() throws JsonProcessingException, SeasonNotAvailableException {

        Match[] responseMatches = {};

        this.server.expect(requestTo("http://localhost:1234/api/getmatchdata/nonExistingLeague/anyYear"))
                .andRespond(withSuccess(o.writeValueAsString(responseMatches), MediaType.APPLICATION_JSON));

        MatchDTO result = this.openLigaDBClient.getMatchesForLeague("nonExistingLeague", "anyYear");
    }
}

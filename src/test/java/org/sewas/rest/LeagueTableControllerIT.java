package org.sewas.rest;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.sewas.domain.model.LeagueTable;
import org.sewas.exception.OpenLigaDbNotOkException;
import org.sewas.exception.SeasonNotAvailableException;
import org.sewas.service.LeagueTableService;
import org.sewas.util.testmarker.IntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by sebastian on 10/06/17.
 */
@Category(IntegrationTest.class)
@RunWith(SpringRunner.class)
@WebMvcTest(LeagueTableController.class)
public class LeagueTableControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LeagueTableService leagueTableService;

    @Test // 200
    public void shouldReturnOkIfLeagueIdWasGiven() throws Exception {
        given(this.leagueTableService.returnCurrentLeagueTable(anyString(), anyString())).willReturn(new LeagueTable());

        this.mockMvc.perform(get("/api/leagueTable/1234/2016").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(this.leagueTableService, times(1)).returnCurrentLeagueTable("1234", "2016");
    }

    @Test // 404
    public void shouldReturnNotFoundStatusIfNoMatchesReturn() throws Exception {
        given(this.leagueTableService.returnCurrentLeagueTable(anyString(), anyString())).willThrow(SeasonNotAvailableException.class);

        this.mockMvc.perform(get("/api/leagueTable/1234/2016").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(this.leagueTableService, times(1)).returnCurrentLeagueTable("1234", "2016");
    }

    @Test // 418
    public void shouldReturnTeapotStatusIfOpenLigaNotOkExceptionIsThrown() throws Exception {
        given(this.leagueTableService.returnCurrentLeagueTable(anyString(), anyString())).willThrow(OpenLigaDbNotOkException.class);

        this.mockMvc.perform(get("/api/leagueTable/1234/2016").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isIAmATeapot());

        verify(this.leagueTableService, times(1)).returnCurrentLeagueTable("1234", "2016");
    }
}

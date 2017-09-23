package org.sewas.rest;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.sewas.domain.model.LeagueTable;
import org.sewas.exception.MatchdayNotAvailableException;
import org.sewas.service.LeagueTableService;
import org.sewas.util.testmarker.IntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by sebastian on 18/06/17.
 */
@Category(IntegrationTest.class)
@RunWith(SpringRunner.class)
@WebMvcTest(LeagueTableController.class)
public class LeagueTableControllerGetMatchdayTableIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LeagueTableService leagueTableService;

    @Test //200
    public void shouldReturnOkIfValidMatchdayWasGiven() throws Exception {
        given(this.leagueTableService.returnMatchdayLeagueTable(anyString(), anyString(), anyString())).willReturn(new LeagueTable());

        this.mockMvc.perform(get("/api/leagueTable/1234/2016/18").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(view().name("currentMatchday"))
                .andExpect(model().attributeExists("leagueID", "season", "leagueTable"))
        ;

        verify(this.leagueTableService, times(1)).returnMatchdayLeagueTable("1234", "2016", "18");
    }

    @Test //404
    public void shouldReturn404IfNoValidMatchdayWasGiven() throws Exception {
        given(this.leagueTableService.returnMatchdayLeagueTable(anyString(), anyString(), anyString())).willThrow(MatchdayNotAvailableException.class);

        this.mockMvc.perform(get("/api/leagueTable/1234/2016/18").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(this.leagueTableService, times(1)).returnMatchdayLeagueTable("1234", "2016", "18");
    }
}

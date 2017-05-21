package service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.sewas.client.OpenLigaDBClient;
import org.sewas.service.LeagueTableService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

/**
 * Created by sebastian on 21/05/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class LeagueTableServiceTest {

    @InjectMocks
    LeagueTableService underTest;

    @Mock
    private OpenLigaDBClient openLigaDBClient;

    @Before
    public void setUp(){
        given(this.openLigaDBClient.getMatchesForLeague(any(String.class))).willReturn(true);
    }

    @Test
    public void LeagueTableService_must_return_LeagueTable(){
        assertThat(this.underTest.returnCurrentLeagueTable("bl1")).isNotNull();
    }

}

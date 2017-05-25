package org.sewas.config;

import org.sewas.rest.LeagueTableController;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import service.LeagueTableService;

/**
 * Created by sebastian on 22/05/17.
 */
@TestConfiguration
public class ScenarioConfig {

    @Bean
    public LeagueTableController leagueTableController()
    {
        return new LeagueTableController();
    }

    @Bean
    public LeagueTableService leagueTableService()
    {
        return new LeagueTableService();
    }
}

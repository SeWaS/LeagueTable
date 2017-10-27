package org.sewas.features;

import org.sewas.OpenLigaDBConfig;
import org.sewas.client.OpenLigaDBClient;
import org.sewas.rest.LeagueTableController;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.sewas.service.LeagueTableService;

import java.util.Arrays;

/**
 * Created by sebastian on 22/05/17.
 */
@TestConfiguration
public class ScenarioConfig {

    @Bean
    public LeagueTableController leagueTableController(LeagueTableService leagueTableService)
    {
        return new LeagueTableController(leagueTableService);
    }

    @Bean
    public LeagueTableService leagueTableService(OpenLigaDBClient openLigaDBClient)
    {
        return new LeagueTableService(openLigaDBClient);
    }

    @Bean
    public OpenLigaDBClient openLigaDBClient(RestTemplate restTemplate, OpenLigaDBConfig openLigaDBConfig) {
        return new OpenLigaDBClient(restTemplate, openLigaDBConfig);
    }

    @Bean
    public OpenLigaDBConfig openLigaDBConfig() {
        return new OpenLigaDBConfig();
    }
}

package org.sewas.config;

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
    public LeagueTableController leagueTableController()
    {
        return new LeagueTableController();
    }

    @Bean
    public LeagueTableService leagueTableService()
    {
        return new LeagueTableService();
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        converter.setSupportedMediaTypes(
                Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM}));

        restTemplate.setMessageConverters(Arrays.asList(converter, new FormHttpMessageConverter()));
        return restTemplate;
    }
}

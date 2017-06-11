package org.sewas.config;

import org.sewas.client.OpenLigaDBClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sebastian on 28/05/17.
 */
@TestConfiguration
@ComponentScan(basePackageClasses = OpenLigaDBClient.class)
public class ClientTestConfig {
    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }

    @Bean
    public MockRestServiceServer server(RestTemplate restTemplate)
    {
        return MockRestServiceServer.createServer(restTemplate);
    }
}

package org.sewas;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by sebastian on 15/06/17.
 */
@Component
@ConfigurationProperties(prefix = "openliga.api")
public class OpenLigaDBConfig {

    private String getmachdata;

    public String getGetmachdata() {
        return getmachdata;
    }

    public void setGetmachdata(String getmachdata) {
        this.getmachdata = getmachdata;
    }
}

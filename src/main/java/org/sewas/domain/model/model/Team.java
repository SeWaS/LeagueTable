package org.sewas.domain.model.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by sebastian on 21/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {

    @JsonProperty("TeamIconUrl")
    public String TeamIconUrl;

    @JsonProperty("TeamId")
    public int TeamId;

    @JsonProperty("TeamName")
    public String TeamName;

}

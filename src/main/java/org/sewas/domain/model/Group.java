package org.sewas.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by sebastian on 21/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Group {

    @JsonProperty("GroupID")
    public int GroupID;

    @JsonProperty("GroupName")
    public String GroupName;

    @JsonProperty("GroupOrderID")
    public int GroupOrderID;

}

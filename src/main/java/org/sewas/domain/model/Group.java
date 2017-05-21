package org.sewas.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by sebastian on 21/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Group {

    private int GroupID;
    private String GroupName;
    private int GroupOrderID;

    public int getGroupID() {
        return GroupID;
    }

    public void setGroupID(int groupID) {
        GroupID = groupID;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public int getGroupOrderID() {
        return GroupOrderID;
    }

    public void setGroupOrderID(int groupOrderID) {
        GroupOrderID = groupOrderID;
    }
}

package org.sewas.util;

import org.sewas.domain.model.Group;

/**
 * Created by sebastian on 25/05/17.
 */
public class GroupBuilder {
    private int GroupID;
    private String GroupName;
    private int GroupOrderID;

    public GroupBuilder()
    {}

    public GroupBuilder withGroupID(int GroupID)
    {
        this.GroupID = GroupID;
        return this;
    }

    public GroupBuilder withGroupName(String GroupName)
    {
        this.GroupName = GroupName;
        return this;
    }

    public GroupBuilder withGroupOrderID(int GroupOrderID)
    {
        this.GroupOrderID = GroupOrderID;
        return this;
    }

    public Group build()
    {
        Group p = new Group();
        p.setGroupOrderID(this.GroupOrderID);
        p.setGroupName(this.GroupName);
        p.setGroupID(this.GroupID);

        return p;
    }
}

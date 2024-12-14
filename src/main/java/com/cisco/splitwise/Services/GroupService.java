package com.cisco.splitwise.Services;

import java.util.ArrayList;
import java.util.List;

import com.cisco.splitwise.Models.Group;

public class GroupService {
    private List<Group> groups;

    public GroupService() {
        this.groups = new ArrayList<>();
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }

    public Group getGroupById(String groupId) {
        for (Group group : groups) {
            if (group.getGroupId().equals(groupId)) {
                return group;
            }
        }
        return null;
    }

}

package com.system559.cms.model;

import java.util.List;

public interface TeamMember {
    List<TeamMember> getMembers();
    String getName();
    String getType();
}

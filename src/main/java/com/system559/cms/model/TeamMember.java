package com.system559.cms.model;

import java.io.Serializable;
import java.util.List;

public interface TeamMember extends IdentifiableObject {
    List<TeamMember> getMembers();
    String getAvatar();
}

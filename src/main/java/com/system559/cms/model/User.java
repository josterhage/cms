package com.system559.cms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements TeamMember{
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;

    @Override
    public List<TeamMember> getMembers() {
        return List.of(this);
    }

    @Override
    public String getName() {
        return String.format("%s %s %s",firstName,middleName,lastName);
    }

    @Override
    public String getType() {
        return "user";
    }
}

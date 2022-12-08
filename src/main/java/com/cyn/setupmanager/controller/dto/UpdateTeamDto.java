package com.cyn.setupmanager.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTeamDto
{
    private String name;
    private String description;
    private List<Integer> memberIds;
    private Integer ownerId;
    private Integer id;
}

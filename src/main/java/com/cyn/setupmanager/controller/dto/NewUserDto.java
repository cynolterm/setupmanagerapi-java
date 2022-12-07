package com.cyn.setupmanager.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDto
{
    private String name;
    private String username;
    private String description;
    private String nationality;

    private String password;
}

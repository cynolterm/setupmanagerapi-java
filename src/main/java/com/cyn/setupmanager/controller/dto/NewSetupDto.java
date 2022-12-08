package com.cyn.setupmanager.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewSetupDto
{
    private Integer carId;
    private Integer trackId;
    private String carSetup;
}

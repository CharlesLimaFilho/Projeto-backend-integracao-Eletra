package org.project.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Setter
@Getter
public class LineDto {

    @NotBlank
    private String lineName;

}

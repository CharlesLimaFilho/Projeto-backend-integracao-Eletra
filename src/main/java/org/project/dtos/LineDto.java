package org.project.dtos;

import javax.validation.constraints.NotBlank;

public class LineDto {

    private int id_line;

    @NotBlank
    private String lineName;

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public int getLineId() {
        return id_line;
    }

    public void setLineId(int id_line) {
        this.id_line = id_line;
    }
}

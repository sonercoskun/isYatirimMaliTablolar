package com.sonercoskun.isyatirimmalitablolar.models.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TableParametersDTO implements Serializable {
    private String share;
    public String itemDescEng;
    public Long value;
    private String year;
    private Integer period;
}

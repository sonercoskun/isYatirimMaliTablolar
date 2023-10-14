package com.sonercoskun.isyatirimmalitablolar.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class DailyPricesEmbeddedId implements Serializable {
    private String id;
    private String optionName;
}

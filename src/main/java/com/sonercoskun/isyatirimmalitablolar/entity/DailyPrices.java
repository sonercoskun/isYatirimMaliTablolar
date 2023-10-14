package com.sonercoskun.isyatirimmalitablolar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "dailyprices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DailyPrices {
    @EmbeddedId
    private DailyPricesEmbeddedId dailyPricesEmbeddedId;
    @Column(name = "price")
    private Double price;
}

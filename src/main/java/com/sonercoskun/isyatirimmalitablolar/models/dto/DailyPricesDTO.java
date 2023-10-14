package com.sonercoskun.isyatirimmalitablolar.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyPricesDTO {
    public ArrayList<ArrayList<String>> data;
    public Date timestamp;
    public List<PricesDTO> getData() {
        List<PricesDTO> pricesDTOS = new ArrayList<>();
        for(ArrayList<String> prices : this.data){
            PricesDTO pricesDTO = new PricesDTO();
            pricesDTO.setTimestamp(prices.get(0));
            pricesDTO.setPrice(Double.valueOf(prices.get(1)));
            pricesDTOS.add(pricesDTO);
        }
        return pricesDTOS;
    }
}

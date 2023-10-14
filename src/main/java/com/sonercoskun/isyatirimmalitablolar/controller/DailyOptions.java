package com.sonercoskun.isyatirimmalitablolar.controller;

import com.sonercoskun.isyatirimmalitablolar.entity.DailyPrices;
import com.sonercoskun.isyatirimmalitablolar.entity.DailyPricesEmbeddedId;
import com.sonercoskun.isyatirimmalitablolar.entity.Hisse;
import com.sonercoskun.isyatirimmalitablolar.models.dto.DailyPricesDTO;
import com.sonercoskun.isyatirimmalitablolar.models.dto.PricesDTO;
import com.sonercoskun.isyatirimmalitablolar.repository.DailyPricesRepository;
import com.sonercoskun.isyatirimmalitablolar.service.HisseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class DailyOptions {
    private RestTemplate restTemplate;
    private DailyPricesRepository dailyPricesRepository;
    private HisseService hisseService;

    public DailyOptions(DailyPricesRepository dailyPricesRepository, HisseService hisseService) {
        this.restTemplate = new RestTemplate();
        this.dailyPricesRepository = dailyPricesRepository;
        this.hisseService = hisseService;
    }

    @GetMapping("/dailyOptions")
    public ResponseEntity<?> getAllDailyOptions() {
        List<Hisse> hisses = hisseService.fetchAllShares();
        int yearFrom = 1990;
        int yearTo = 1995;
        for (Hisse hisse : hisses) {
            while (yearFrom != 2026) {
                ResponseEntity<DailyPricesDTO> responseEntity = restTemplate.getForEntity
                        ("https://www.isyatirim.com.tr/_Layouts/15/IsYatirim.Website/Common/ChartData.aspx/IndexHistoricalAll" +
                                "?period=1440&from=" + yearFrom + "0101000000&to=" + yearTo + "1231235959&endeks=" +
                                hisse.getHisse() + ".E.BIST", DailyPricesDTO.class);
                if (responseEntity.getBody() == null || responseEntity.getBody().getData() == null) {
                    return new ResponseEntity<>(null, HttpStatus.OK);
                }
                List<DailyPrices> dailyPricesList = getDailyPrices(responseEntity,hisse.getHisse());
                dailyPricesRepository.saveAll(dailyPricesList);
                yearFrom = yearFrom + 6;
                yearTo = yearTo + 6;
            }
            yearFrom = 1990;
            yearTo = 1995;
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private static List<DailyPrices> getDailyPrices(ResponseEntity<DailyPricesDTO> responseEntity, String hisse) {
        List<DailyPrices> dailyPricesList = new ArrayList<>();
        for (PricesDTO pricesDTO : responseEntity.getBody().getData()) {
            DailyPrices dailyPrices = new DailyPrices();
            DailyPricesEmbeddedId dailyPricesEmbeddedId = new DailyPricesEmbeddedId();
            dailyPricesEmbeddedId.setOptionName(hisse);
            dailyPricesEmbeddedId.setId(pricesDTO.getTimestamp());
            dailyPrices.setDailyPricesEmbeddedId(dailyPricesEmbeddedId);
            dailyPrices.setPrice(pricesDTO.getPrice());
            dailyPricesList.add(dailyPrices);
        }
        return dailyPricesList;
    }
}

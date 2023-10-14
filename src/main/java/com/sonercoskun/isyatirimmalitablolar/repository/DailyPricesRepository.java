package com.sonercoskun.isyatirimmalitablolar.repository;

import com.sonercoskun.isyatirimmalitablolar.entity.DailyPrices;
import com.sonercoskun.isyatirimmalitablolar.entity.DailyPricesEmbeddedId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyPricesRepository extends JpaRepository<DailyPrices, DailyPricesEmbeddedId> {
}

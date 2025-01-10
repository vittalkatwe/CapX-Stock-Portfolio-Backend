
// StockRepository.java
package com.stockportfolio.repository;

import com.stockportfolio.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
    List<Stock> findAllByOrderByLastUpdatedDesc();
}

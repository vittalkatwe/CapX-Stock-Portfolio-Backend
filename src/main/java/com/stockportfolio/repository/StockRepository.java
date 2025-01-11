
// StockRepository.java
package com.stockportfolio.repository;

import com.stockportfolio.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("stockMongoRepository")
public interface StockRepository extends MongoRepository<Stock, String> {
    List<Stock> findAllByOrderByLastUpdatedDesc();
}


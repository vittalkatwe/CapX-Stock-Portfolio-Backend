package com.stockportfolio.service;

import com.stockportfolio.dto.StockDTO;
import com.stockportfolio.model.Stock;
import com.stockportfolio.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class StockService {

    @Autowired
    @Qualifier("stockMongoRepository")
    private final StockRepository stockRepository;
    private static final Logger logger = LoggerFactory.getLogger(StockService.class);

    
    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public class StockNotFoundException extends RuntimeException {
        public StockNotFoundException(String message) {
            super(message);
        }
    }
    
    
    public List<StockDTO> getAllStocks() {
        return stockRepository.findAll()
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    public StockDTO getStock(String id) {
        logger.info("Fetching stock with ID: {}", id);
        return stockRepository.findById(id)
            .map(this::convertToDTO)
            .orElseThrow(() -> new StockNotFoundException("Stock not found with ID: " + id));
    }

    
    
    @Transactional
    public StockDTO createStock(StockDTO stockDTO) {
        Stock stock = convertToEntity(stockDTO);
        Stock savedStock = stockRepository.save(stock);
        return convertToDTO(savedStock);
    }

    
    
    @Transactional
    public StockDTO updateStock(String id, StockDTO stockDTO) {
        Stock existingStock = stockRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Stock not found"));
        
        updateStockFields(existingStock, stockDTO);
        Stock updatedStock = stockRepository.save(existingStock);
        return convertToDTO(updatedStock);
    }
    
    @Transactional
    public void deleteStock(String id) {
        stockRepository.deleteById(id);
    }
    
    private StockDTO convertToDTO(Stock stock) {
        StockDTO dto = new StockDTO();
        dto.setId(stock.getId());
        dto.setName(stock.getName());
        dto.setTicker(stock.getTicker());
        dto.setQuantity(stock.getQuantity());
        dto.setBuyPrice(stock.getBuyPrice());
        dto.setCurrentPrice(stock.getCurrentPrice());
        return dto;
    }
    
    private Stock convertToEntity(StockDTO dto) {
        Stock stock = new Stock();
        stock.setName(dto.getName());
        stock.setTicker(dto.getTicker());
        stock.setQuantity(dto.getQuantity());
        stock.setBuyPrice(dto.getBuyPrice());
        stock.setCurrentPrice(dto.getCurrentPrice());
        return stock;
    }
    
    private void updateStockFields(Stock stock, StockDTO dto) {
        stock.setName(dto.getName());
        stock.setTicker(dto.getTicker());
        stock.setQuantity(dto.getQuantity());
        stock.setBuyPrice(dto.getBuyPrice());
        stock.setCurrentPrice(dto.getCurrentPrice());
    }
}

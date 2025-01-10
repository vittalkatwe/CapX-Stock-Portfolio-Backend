package com.stockportfolio.dto;

import java.math.BigDecimal;

public class StockDTO {
    private String id;
    private String name;
    private String ticker;
    private Integer quantity;
    private BigDecimal buyPrice;
    private BigDecimal currentPrice;
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getTicker() { return ticker; }
    public Integer getQuantity() { return quantity; }
    public BigDecimal getBuyPrice() { return buyPrice; }
    public BigDecimal getCurrentPrice() { return currentPrice; }
    
    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setTicker(String ticker) { this.ticker = ticker; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setBuyPrice(BigDecimal buyPrice) { this.buyPrice = buyPrice; }
    public void setCurrentPrice(BigDecimal currentPrice) { this.currentPrice = currentPrice; } 
}

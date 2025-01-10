package com.stockportfolio.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String ticker;
    
    @Column(nullable = false)
    private Integer quantity;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal buyPrice;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal currentPrice;
    
    @Column(nullable = false)
    private LocalDateTime lastUpdated;
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getTicker() { return ticker; }
    public Integer getQuantity() { return quantity; }
    public BigDecimal getBuyPrice() { return buyPrice; }
    public BigDecimal getCurrentPrice() { return currentPrice; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    
    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setTicker(String ticker) { this.ticker = ticker; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setBuyPrice(BigDecimal buyPrice) { this.buyPrice = buyPrice; }
    public void setCurrentPrice(BigDecimal currentPrice) { this.currentPrice = currentPrice; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
    
    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }
}

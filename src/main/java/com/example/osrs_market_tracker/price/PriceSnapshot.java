/*
Uses ManyToOne:
- OOP: each PriceSnapshot object holds a reference to one Item object
- DB: price_snapshot stores item_id as a foreign key to item.id

Fetching:
- LAZY = related Item is loaded only when accessed
- EAGER = related Item is loaded immediately
*/

package com.example.osrs_market_tracker.price;

import com.example.osrs_market_tracker.item.Item;
import jakarta.persistence.*;

import java.time.Instant;



@Entity
@Table(name = "price_snapshot")
public class PriceSnapshot {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Long highPrice;

    @Column(nullable = false)
    private Long lowPrice;

    @Column(nullable = false)
    private Instant timestamp;

    public PriceSnapshot() {
    }

    public PriceSnapshot(Item item, Long highPrice, Long lowPrice, Instant timestamp) {
        this.item = item;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Long highPrice) {
        this.highPrice = highPrice;
    }

    public Long getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Long lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
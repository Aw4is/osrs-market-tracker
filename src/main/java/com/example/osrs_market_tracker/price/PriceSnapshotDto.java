/*
Purpose -> Entity has Private Item item
Thus a request would require us to define an entire Item so it can be deserialised
correctly.
Hence, here, we only expect Long ItemId.
 */

package com.example.osrs_market_tracker.price;


import lombok.NoArgsConstructor;

import java.time.Instant;

public class PriceSnapshotDto {


    private Long itemId;

    private Long highPrice;

    private Long lowPrice;

    private Instant timestamp;

    // Spring (Jackson) can easily create STO when reading JSON request Body
        // So no need for arguments here
    public PriceSnapshotDto(){}



    public Long getItemId() {
        return itemId;
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


    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

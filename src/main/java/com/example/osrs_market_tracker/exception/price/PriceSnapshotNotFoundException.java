package com.example.osrs_market_tracker.exception.price;

public class PriceSnapshotNotFoundException extends RuntimeException{

    private final String message;

    public PriceSnapshotNotFoundException(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}

package com.example.osrs_market_tracker.exception.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemNotFoundException extends RuntimeException {

    private String message;

    public ItemNotFoundException(String message){
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String errorMessage){
        this.message = errorMessage;
    }


}

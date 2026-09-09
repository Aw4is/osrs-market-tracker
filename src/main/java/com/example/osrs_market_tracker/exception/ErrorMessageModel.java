package com.example.osrs_market_tracker.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessageModel {

    private final int status;
    private final String message;

    public ErrorMessageModel( int status, String message){
        this.status = status;
        this.message = message;
    }

    public String getMessage(){return this.message;
    }

    public int getStatus(){
        return this.status;
    }




}

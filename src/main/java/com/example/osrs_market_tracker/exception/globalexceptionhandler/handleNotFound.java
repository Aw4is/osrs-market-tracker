package com.example.osrs_market_tracker.exception.globalexceptionhandler;


import com.example.osrs_market_tracker.exception.ErrorMessageModel;
import com.example.osrs_market_tracker.exception.item.ItemNotFoundException;
import com.example.osrs_market_tracker.exception.price.PriceSnapshotNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class handleNotFound {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorMessageModel> handleNotFoundtem(
            ItemNotFoundException ex
    ){
        ErrorMessageModel errorMessageModel = new ErrorMessageModel(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );

        return new ResponseEntity<>(errorMessageModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PriceSnapshotNotFoundException.class)
    public ResponseEntity<ErrorMessageModel> hanndleNotFoundPrice(
            PriceSnapshotNotFoundException ex
    ){
        ErrorMessageModel errorMessageModel = new ErrorMessageModel(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
                );

        return new ResponseEntity<>(errorMessageModel, HttpStatus.NOT_FOUND);
    }
}

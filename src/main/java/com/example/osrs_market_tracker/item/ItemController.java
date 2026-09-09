/*
Handles HTTP requests for Item resources.
Maps endpoints to service-layer operations and returns HTTP responses/status codes.

Uses ResponseEntity:
- Represents the full HTTP response sent to the client
- Can contain status, headers and a response body
- ResponseEntity<T> means the response body contains type T
*/


package com.example.osrs_market_tracker.item;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // ---- Get Mappings ---- //

    @GetMapping
    public ResponseEntity<List<Item>> getAll() {
        return new ResponseEntity<>(itemService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        return new ResponseEntity<>(itemService.getById(id), HttpStatus.OK);
    }

    // ---- Create Mappings ---- //

    @PostMapping
    public ResponseEntity<Item> itemCreate(@RequestBody Item itemToCreate) {
        return new ResponseEntity<>(
                itemService.createItem(itemToCreate),
                HttpStatus.CREATED
        );
    }

    // ---- Update Mappings ---- //

    @PutMapping("/{id}")
    public ResponseEntity<Item> itemUpdate(
            @RequestBody Item itemUpdate,
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(
                itemService.updateItem(id, itemUpdate),
                HttpStatus.OK
        );
    }

    // ---- Delete Mappings ---- //

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

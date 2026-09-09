package com.example.osrs_market_tracker.price;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
We skip PUT/Update because historical price snapshots
should be immutable once recorded.
*/

@RestController
@RequestMapping("/price-snapshots")
public class PriceSnapshotController {

    private final PriceSnapshotService priceSnapshotService;

    public PriceSnapshotController(PriceSnapshotService priceSnapshotService) {
        this.priceSnapshotService = priceSnapshotService;
    }

    @GetMapping
    public ResponseEntity<List<PriceSnapshot>> getAll() {
        return new ResponseEntity<>(
                priceSnapshotService.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceSnapshot> getById(@PathVariable Long id) {
        return new ResponseEntity<>(
                priceSnapshotService.getById(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<PriceSnapshot> create(
            // Without DTO -> Upon deserialize request body requires
              // entire Item too due to private Item item relation
              // Hence DTO is suited better here
            @RequestBody PriceSnapshotDto snapshot
    ) {
        return new ResponseEntity<>(
                priceSnapshotService.create(snapshot),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        priceSnapshotService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
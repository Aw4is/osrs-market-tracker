package com.example.osrs_market_tracker.price;

import com.example.osrs_market_tracker.exception.price.PriceSnapshotNotFoundException;
import com.example.osrs_market_tracker.item.Item;
import com.example.osrs_market_tracker.item.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceSnapshotService {

    private final PriceSnapshotRepository priceSnapshotRepository;
    private final ItemService itemService;

    public PriceSnapshotService(
            PriceSnapshotRepository priceSnapshotRepository,
            ItemService itemService
    ) {
        this.priceSnapshotRepository = priceSnapshotRepository;
        this.itemService = itemService;
    }

    public List<PriceSnapshot> getAll() {
        return priceSnapshotRepository.findAll();
    }

    public PriceSnapshot getById(Long id) {
        return priceSnapshotRepository.findById(id)
                .orElseThrow(() ->
                        new PriceSnapshotNotFoundException(
                                "Price snapshot with id " + id + " was not found"
                        )
                );
    }

    public PriceSnapshot create(PriceSnapshotDto snapshot) {
        Long itemId = snapshot.getItemId();

        Item currentItem = itemService.getById(itemId);

        PriceSnapshot snapshotToCreate = new PriceSnapshot(
                currentItem,
                snapshot.getHighPrice(),
                snapshot.getLowPrice(),
                snapshot.getTimestamp()
        );

        return priceSnapshotRepository.save(snapshotToCreate);
    }

    public void delete(Long id) {
        priceSnapshotRepository.deleteById(id);
    }
}
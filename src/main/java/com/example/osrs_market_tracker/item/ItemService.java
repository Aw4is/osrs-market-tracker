package com.example.osrs_market_tracker.item;


import com.example.osrs_market_tracker.exception.item.ItemNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // ---- Get ---- //

    public Item getById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() ->
                        new ItemNotFoundException("Item with id " + id + " was not found")
                );
    }

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    // ---- Create ---- //

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    // ---- Update ---- //

    public Item updateItem(Long id, Item item) {
        Item existingItem = itemRepository.findById(id)
                .orElseThrow(() ->
                        new ItemNotFoundException("Item with id " + id + " was not found")
                );

        existingItem.setBuyLimit(item.getBuyLimit());
        existingItem.setHighAlch(item.getHighAlch());
        existingItem.setLowAlch(item.getLowAlch());
        existingItem.setMembers(item.getMembers());
        existingItem.setName(item.getName());

        return itemRepository.save(existingItem);
    }

    // ---- Delete ---- //

    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new ItemNotFoundException("Item with id " + id + " was not found");
        }

        itemRepository.deleteById(id);
    }
}

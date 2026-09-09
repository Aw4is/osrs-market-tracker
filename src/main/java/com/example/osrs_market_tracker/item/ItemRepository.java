/*
Interface as Spring Data JPA dynamically provides the implementation.
We declare the required data-access contract rather than implementing standard persistance
operations ourselves.

Uses EntityManager for CRUD operations.
*/


package com.example.osrs_market_tracker.item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}

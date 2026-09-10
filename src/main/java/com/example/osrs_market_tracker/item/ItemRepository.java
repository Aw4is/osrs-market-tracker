/*
Interface as Spring Data JPA dynamically provides the implementation.
We declare the required data-access contract rather than implementing standard persistance
operations ourselves.

Uses EntityManager for CRUD operations.
*/


package com.example.osrs_market_tracker.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    // Spring Data JPA parses name findByExternalId and sees item has it as a field
    // Then generates query/implementation at runtime
    Optional<Item> findByExternalId(Long externalId);

}

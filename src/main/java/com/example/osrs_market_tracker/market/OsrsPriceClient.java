/*
Calls OSRS Wiki API and deserialises/returns external price data
Focuses on hourly averages.

Uses Webclient -> Allows to connect to external services:
- Supports non-blocking and asynchronous requests, giving app better scalability
- Thread safe so can use the same one across different parts of application
Sets up chain of instructions first but doesnt send anything until
program subscribes to the chain.

Uses Mono:
- Part of project reactor -> Library for building reactive apps in Java
- Represents a single element (or no element) that may be available
asynchronously
- Key characteristics -> Single value or empty, non-blocking (ops do not block thread),
chaining operations (provides ops like map,flatmap...) to transform/process data
- Benefits: Asynchronous Workloads (handing I/o heavy ops like db calls),
improves scalability (no thread blocking, concurrent requests), fits microservice
architecture
 */

package com.example.osrs_market_tracker.market;

import com.example.osrs_market_tracker.item.Item;
import com.example.osrs_market_tracker.item.ItemRepository;
import com.example.osrs_market_tracker.market.dto.OsrsItemMappingDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

// Define it as a bean so Spring can manage it
@Component
public class OsrsPriceClient {

    // Corresponds to the API Endpoint for OSRS Wiki to retrieve prices
    private static final String API_ENDPOINT =
            "https://prices.runescape.wiki/api/v2/osrs";
    // Create a reusable client object that knows where to send requests
        // and what to include with them
        // Base client -> Tells to treat that address as a starting point
        // Can also add in headers, jwt tokens etc -> WebClient stacks them up
    private final WebClient webClient;
    private final ItemRepository itemRepository;



    public OsrsPriceClient(ItemRepository itemRepository){
        this.webClient =  WebClient.builder()
                .baseUrl(API_ENDPOINT)
                // Increase memory buffer size
                    // Codecs ->Components responsible for converting HTTP Data into Java objects
                    // API responds -> Sends bytes back representing JSON, not Java Object
                    // Spring uses Jackson-based JSON codec to convert
                .codecs( configurer ->
                        configurer.defaultCodecs().maxInMemorySize(5 * 1024 * 1024))
                .build();
        this.itemRepository = itemRepository;
    }

    public void fetchHourlyPrices(){

        // When someone subscribes, make a get Request to uri, grab response
            // body and treat it like a String
            // Focus on 1 hour price averages
            // Note: After retrieve can chain for operators to handle errors etc
        Mono<List<OsrsItemMappingDto>> response = this.webClient.get()
                .uri("/mapping")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<OsrsItemMappingDto>>() {});

        response.subscribe(
                list -> {
                    for (OsrsItemMappingDto dto : list) {
                        Optional<Item> existingItem =
                                itemRepository.findByExternalId(dto.getId());

                        if (existingItem.isPresent()) {
                            Item item = existingItem.get();

                            item.setName(dto.getName());
                            item.setMembers(dto.getMembers());
                            item.setHighAlch(dto.getHighalch());
                            item.setLowAlch(dto.getLowalch());
                            item.setBuyLimit(dto.getLimit());

                            itemRepository.save(item);

                        } else {
                            Item item = new Item(
                                    dto.getId(),
                                    dto.getName(),
                                    dto.getMembers(),
                                    dto.getHighalch(),
                                    dto.getLowalch(),
                                    dto.getLimit()
                            );

                            itemRepository.save(item);
                        }
                    }

                    System.out.println("Finished importing " + list.size() + " items.");
                },
                error -> {
                    error.printStackTrace();
                }
        );
    }}


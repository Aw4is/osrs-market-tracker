package com.example.osrs_market_tracker;

import com.example.osrs_market_tracker.market.OsrsPriceClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OsrsMarketTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OsrsMarketTrackerApplication.class, args);
	}

	@Bean
    CommandLineRunner testOsrsApi(OsrsPriceClient osrsPriceClient) {
		return args -> {
			osrsPriceClient.fetchHourlyPrices();
		};
	}
}

package com.example.modulex;

import java.util.concurrent.CompletableFuture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.modulith.events.EventExternalizationConfiguration;
import org.springframework.modulith.events.support.DelegatingEventExternalizer;

@SpringBootApplication
public class ModulexApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModulexApplication.class, args);
	}

    @Bean
    DelegatingEventExternalizer modulexEventExternalizer(EventExternalizationConfiguration conf) {
        return new DelegatingEventExternalizer(conf, (target,payload) -> {
            // Custom logic can be added here if needed
            System.out.println("Externalizing event to target: " + target + " with payload: " + payload);
			return CompletableFuture.failedFuture(null);
            //return CompletableFuture.completedFuture(null);
        });
    }

}

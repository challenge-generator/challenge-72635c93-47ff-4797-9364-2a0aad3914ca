package com.fintech.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class LoansApplication {
    private final Environment environment;

    public LoansApplication(Environment environment) {
        this.environment = environment;
        validateActiveProfiles();
    }

    public static void main(String[] args) {
        SpringApplication.run(LoansApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        System.out.println("Application started with profiles: " + Arrays.toString(environment.getActiveProfiles()));
    }

    private void validateActiveProfiles() {
        String[] activeProfiles = environment.getActiveProfiles();
        if (activeProfiles.length == 0) {
            throw new IllegalStateException("No active Spring profiles set. Please configure at least one profile (dev, prod, test)");
        }

        boolean hasDevOrProd = Arrays.stream(activeProfiles)
                .anyMatch(profile -> profile.equals("dev") || profile.equals("prod"));

        if (!hasDevOrProd) {
            throw new IllegalStateException("At least one of 'dev' or 'prod' profile must be active");
        }
    }
}
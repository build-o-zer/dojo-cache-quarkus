package org.buildozers;

import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BusinessService {

    private static final Logger LOGGER = Logger.getLogger(BusinessService.class.getName());
    private final Random random = new Random();

    public String performBusinessLogic() {
        try {
            // Simulate heavy load with random sleep between 2-5 seconds
            int sleepTime = 2000 + random.nextInt(3001); // 2000ms + 0-3000ms = 2000-5000ms
            Thread.sleep(sleepTime);
            LOGGER.info(() -> "Business logic processing took: " + sleepTime + "ms");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Business logic processing was interrupted", e);
        }
        
        // Simulate some business logic processing
        return "Business logic result from Quarkus Service " + UUID.randomUUID();
    }

}

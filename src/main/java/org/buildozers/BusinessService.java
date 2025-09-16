package org.buildozers;

import java.util.Random;
import java.util.UUID;

import io.quarkus.cache.CacheResult;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BusinessService {

    private final Random random = new Random();

    // TODO : CODING DOJO tout seul, intecepteur CDI qui simule un long traitement à la de le coder dans la méthode 
    // @SimulateLongProcessing(initialDelay = 2000, maxDelay = 5000)
    @CacheResult(cacheName = "business-cache")
    public String performBusinessLogic(String chaineA, String chaineB, int entierA, int entierB) {

        int sleepTime = getSleepTime();
        try {
            // Simulate heavy load with random sleep between 2-5 seconds
            Thread.sleep(sleepTime);
            Log.infof("Business logic processing took: %dms", sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            String message = "Thread was interrupted during sleep simulation (expected duration: " + sleepTime + "ms)";
            Log.error(message, e);
            throw new BusinessLogicInterruptedException(message, e);
        }
        
        // Simulate some business logic processing
        return String.format("Business logic result %s, %s, %d, %d (%s)", chaineA, chaineB, entierA, entierB, UUID.randomUUID());
    }

    private int getSleepTime() {
        return 2000 + random.nextInt(3001);
    }

}

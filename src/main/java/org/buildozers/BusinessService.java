package org.buildozers;

import java.util.Random;
import java.util.UUID;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BusinessService {

    private final Random random = new Random();

    public String performBusinessLogic() {

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
        return "Business logic result from Quarkus Service " + UUID.randomUUID();
    }

    private int getSleepTime() {
        return 2000 + random.nextInt(3001);
    }

}

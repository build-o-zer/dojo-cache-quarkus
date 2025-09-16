package org.buildozers;

import org.apache.commons.lang3.time.StopWatch;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/call-me")
public class MainResource {

    private final BusinessService businessService;

    public MainResource(BusinessService businessService) {
        this.businessService = businessService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject callMe() {
        StopWatch stopWatch = StopWatch.createStarted();
        
        // Method execution code here
        String result = businessService.performBusinessLogic();

        stopWatch.stop();
        
        System.out.println("Method execution time: " + stopWatch.getTime() + " ms");
        
        return Json.createObjectBuilder()
                .add("businessResult", result)
                .add("executionTimeMs", stopWatch.getTime())
                .build();
    }
}

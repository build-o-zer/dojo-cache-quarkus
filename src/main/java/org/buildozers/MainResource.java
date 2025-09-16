package org.buildozers;

import java.util.Set;

import org.apache.commons.lang3.time.StopWatch;

import io.quarkus.cache.Cache;
import io.quarkus.cache.CacheName;
import io.quarkus.cache.CaffeineCache;
import io.quarkus.logging.Log;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class MainResource {

    private final BusinessService businessService;
    private final Cache cache;

    public MainResource(BusinessService businessService, @CacheName("business-cache") Cache cache) {
        this.businessService = businessService;
        this.cache = cache; 
    }

    @Path("/call-me")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public JsonObject callMe(JsonObject input) {

        String chaineA = input.getString("chaineA");
        String chaineB = input.getString("chaineB");
        int entierA = input.getInt("entierA");
        int entierB = input.getInt("entierB");

        Log.infof("Received input: chaineA=%s, chaineB=%s, entierA=%d, entierB=%d", chaineA, chaineB, entierA, entierB);

        StopWatch stopWatch = StopWatch.createStarted();
        String result = businessService.performBusinessLogic(chaineA, chaineB, entierA, entierB);
        stopWatch.stop();

        return Json.createObjectBuilder()
                .add("businessResult", result)
                .add("executionTimeMs", stopWatch.getTime())
                .build();
    }

    @GET
    @Path("/cache-entries")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject cacheEntries() {

        Set<Object> keys = cache.as(CaffeineCache.class).keySet();
        return Json.createObjectBuilder()
            .add("cacheKeys", Json.createArrayBuilder(keys.stream()
                .map(Object::toString)
                .toList()))
            .add("cacheSize", keys.size())
            .build();
     
    }
}

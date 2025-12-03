package com.sample.ServerSent;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 *  Run the spring boot application and access the Server-sent event endpoint at “http://localhost:8080/events” 
 *  You can see the events for every 1 second. 
 */
@RestController
public class SseEventController {
    
    @GetMapping("/events")
    public SseEmitter streamEvents() {
        SseEmitter emitter = new SseEmitter();

		/**
		 * Schedules a background task that runs every 5 seconds. Each time it runs, it
		 * pushes a new status message to the client via SSE. If sending fails, the
		 * emitter is closed with an error.
		 */
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            try {
                String status = "Updated status: " + System.currentTimeMillis();
                emitter.send(SseEmitter.event().name("status-update").data(status));
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        }, 0, 1, TimeUnit.SECONDS);

        return emitter;
    }

}
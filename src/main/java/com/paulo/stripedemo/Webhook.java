package com.paulo.stripedemo;

import com.stripe.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/webhook")
@Slf4j
public class Webhook {

    @PostMapping("/test")
    public ResponseEntity charge(@RequestBody Event event) {
        log.info("Webhook event: {}\nObject: {}", event, event.getObject());
        return ResponseEntity.ok().build();
    }
}

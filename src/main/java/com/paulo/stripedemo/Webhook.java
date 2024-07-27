package com.paulo.stripedemo;

import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/webhook")
@Slf4j
public class Webhook {

    @PostMapping("/test")
    public ResponseEntity charge(@RequestBody Event event) {
        log.info("Webhook event: {}", event);

        if ("checkout.session.completed".equals(event.getType()) || "checkout.session.async_payment_succeeded".equals(event.getType())) {
            log.info("this event is a checkout session");
            Optional<StripeObject> stripeObject = event.getDataObjectDeserializer().getObject();

            if (stripeObject.isPresent()) {
                Session session = (Session) stripeObject.get();

                log.info("Session: {}", session);


            }
//            fulfillCheckout(sessionEvent.getId());
        }

//        EventDataObjectDeserializer deserializer = event.getDataObjectDeserializer();
//
//        Optional<StripeObject> stripeObject = deserializer.getObject();

//        stripeObject.ifPresent(stripeObject1 -> log.info("Stripe Object: {}", stripeObject1));

        return ResponseEntity.ok().build();
    }
}

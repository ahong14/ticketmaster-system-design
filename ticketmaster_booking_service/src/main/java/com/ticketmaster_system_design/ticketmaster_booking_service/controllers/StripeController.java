package com.ticketmaster_system_design.ticketmaster_booking_service.controllers;

import com.stripe.exception.StripeException;
import com.ticketmaster_system_design.ticketmaster_booking_service.models.requests.ChargeRequest;
import com.ticketmaster_system_design.ticketmaster_booking_service.models.requests.ConfirmChargeRequest;
import com.ticketmaster_system_design.ticketmaster_booking_service.services.StripeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stripe")
public class StripeController {
    private final StripeServiceImpl stripeService;

    @Autowired
    public StripeController(StripeServiceImpl stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping(path = "/confirm")
    public ResponseEntity<String> confirmPayment(@RequestBody ConfirmChargeRequest confirmChargeRequest) throws StripeException {
        String confirmedPaymentIntent = this.stripeService.confirmPayment(confirmChargeRequest.getPaymentIntentId());
        return ResponseEntity.ok(confirmedPaymentIntent);
    }

    @PostMapping
    public ResponseEntity<String> createPayment(@RequestBody ChargeRequest chargeRequest) throws StripeException {
        String paymentIntent = this.stripeService.charge(chargeRequest);
        return ResponseEntity.ok(paymentIntent);
    }

    @GetMapping(path = "/{paymentId}")
    public ResponseEntity<String> getPayment(@PathVariable String paymentId) throws StripeException {
        String payment = this.stripeService.getPayment(paymentId);
        return ResponseEntity.ok(payment);
    }
}

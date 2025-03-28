package com.ticketmaster_system_design.ticketmaster_booking_service.services;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.ticketmaster_system_design.ticketmaster_booking_service.models.requests.ChargeRequest;

import java.util.UUID;

public interface StripeService {
    String createPayment(ChargeRequest chargeRequest) throws StripeException;
    String confirmPayment(String setupPaymentId) throws StripeException;
    String getPayment(String paymentId) throws StripeException;
    PaymentIntent findPayment(String paymentId) throws StripeException;
}

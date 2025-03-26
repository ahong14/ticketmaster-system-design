package com.ticketmaster_system_design.ticketmaster_booking_service.models.requests;

public class ConfirmChargeRequest {
    private String paymentIntentId;

    public ConfirmChargeRequest(String paymentIntentId) {
        this.paymentIntentId = paymentIntentId;
    }

    public String getPaymentIntentId() {
        return paymentIntentId;
    }

    public void setPaymentIntentId(String paymentIntentId) {
        this.paymentIntentId = paymentIntentId;
    }
}

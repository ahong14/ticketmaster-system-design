package com.ticketmaster_system_design.ticketmaster_booking_service.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.ticketmaster_system_design.ticketmaster_booking_service.models.Booking;
import com.ticketmaster_system_design.ticketmaster_booking_service.models.requests.ChargeRequest;
import com.ticketmaster_system_design.ticketmaster_booking_service.repositories.BookingRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeServiceImpl implements StripeService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BookingRepository bookingRepository;

    @Autowired
    public StripeServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }


    /**
     *
     * @param chargeRequest - object containing payment information
     * @return - JSON string representing created payment
     * @throws StripeException
     */
    @Override
    public String createPayment(ChargeRequest chargeRequest) throws StripeException {
        // stripe api only accepts currency in cents
        PaymentIntentCreateParams createParams = PaymentIntentCreateParams.builder()
                .setAmount((long) chargeRequest.getAmount() * 100)
                .setCurrency("usd")
                .setDescription(chargeRequest.getDescription())
                // accept payment method enabled via dashboard
                .setAutomaticPaymentMethods(
                    PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                            .setEnabled(true)
                            .setAllowRedirects(PaymentIntentCreateParams.AutomaticPaymentMethods.AllowRedirects.NEVER)
                            .build()
                )
                // map booking id to confirmed payment
                .putMetadata("bookingId", chargeRequest.getBookingId().toString())
                .build();

        // create payment intent
        PaymentIntent paymentIntent = PaymentIntent.create(createParams);

        // update booking with payment id
        Booking paymentBooking = this.bookingRepository.getReferenceById(chargeRequest.getBookingId());
        paymentBooking.setPaymentId(paymentIntent.getId());
        this.bookingRepository.save(paymentBooking);

        // return payment intent json string
        return paymentIntent.toJson();
    }

    /**
     *
     * @param paymentIntentId - payment intent id to confirm payment for
     * @return - payment confirmation json string
     * @throws StripeException
     */
    @Override
    public String confirmPayment(String paymentIntentId) throws StripeException {
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);

        // confirm payment for provided payment intent id, will charge using provided payment method
        PaymentIntentConfirmParams params =
                PaymentIntentConfirmParams
                        .builder()
                        .setPaymentMethod("pm_card_visa")
                        .build();

        return paymentIntent.confirm(params).toJson();
    }

    @Override
    public String getPayment(String paymentId) throws StripeException {
        return PaymentIntent.retrieve(paymentId).toJson();
    }

    @Override
    public PaymentIntent findPayment(String paymentId) throws StripeException {
        return PaymentIntent.retrieve(paymentId);
    }
}

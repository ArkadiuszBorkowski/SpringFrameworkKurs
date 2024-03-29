package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public class ConsoleLoggerPaymentServiceProxy implements PaymentService{

    private static final String LOG_FORMAT = "A new payment of %s has been initiated";
    private final PaymentService paymentService;


    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var payment = paymentService.process(paymentRequest);
        log.info(createLogEntry(payment));
        return payment;
    }

    private String createLogEntry(Payment payment) {
        return String.format(LOG_FORMAT.formatted(payment.getValue()));
    }
}

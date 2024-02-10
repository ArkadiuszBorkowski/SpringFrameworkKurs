package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Log
@RequiredArgsConstructor
@Component
//public class ConsolePaymentLogger implements PaymentService{
public class ConsolePaymentLogger {

    private static final String LOG_FORMAT = "A new payment of %s has been initiated";
   // private final PaymentService paymentService;

    @AfterReturning(value = "bean(paymentProcessor)", returning = "payment")
    public void log(Payment payment) {
        log.info(createLogEntry(payment));
    }

//    public Payment process(PaymentRequest paymentRequest) {
//        var payment = paymentService.process(paymentRequest);
//        log.info(createLogEntry(payment));
//        return payment;
//    }

    private String createLogEntry(Payment payment) {
        return String.format(LOG_FORMAT.formatted(payment.getValue()));
    }
}

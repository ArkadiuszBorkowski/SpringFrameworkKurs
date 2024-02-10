package pl.training.shop;

import lombok.extern.java.Log;
import org.javamoney.moneta.Money;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.training.shop.payments.*;
import pl.training.shop.time.SystemTimeProvider;

@Log
public class Application {

    private static final String DEFAULT_CURRENCY_CODE = "PLN";

//    public static PaymentService paymentService() {
//        var paymentIdGenerator = new UuidPaymentIdGenerator();
//        var paymentFeeCalculator = new PercentagePaymentFeeCalculator(0.01);
//        var paymentRepository = new InMemoryPaymentRepository();
//        var timeProvider = new SystemTimeProvider();
//        var paymentProcessor = new PaymentProcessor(paymentIdGenerator, paymentFeeCalculator, paymentRepository, timeProvider);
//        return new ConsolePaymentLogger(paymentProcessor);
//    }



    public static void main(String[] args) {
        try (var container = new AnnotationConfigApplicationContext(ShopConfiguration.class)) {
            //var paymentService = paymentService();  ==> zamien na bean
            var paymentService = container.getBean(PaymentService.class);
            var paymentRequest = new PaymentRequest(1L, Money.of(1_000, DEFAULT_CURRENCY_CODE));
            var payment = paymentService.process(paymentRequest);
            log.info(payment.toString());
        }

    }

}
